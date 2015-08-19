(ns coming-soon.lib.webhooks
  (:require [clojure.core.async :as a :refer [<!! >!! chan thread go-loop <!]]
            [coming-soon.config :as config]
            [coming-soon.webhooks.webhook :as webhook]))

(def ^:private webhook-chan (chan 1000)) ; buffered channel with up to 1,000 entries without blocking

(defn call
  "Send an async message for each configured webhook."
  [email referrer]
  (let [webhooks config/webhooks]
    (doseq [webhook (keys webhooks)]
      (>!! webhook-chan {:webhook webhook :email email :referrer referrer :config (webhooks webhook)}))))

(defn- call-webhook
  [args]
  (webhook/invoke args))

;; Dynamically require all configured webhook's namespaces
(let [webhooks config/webhooks]
  (doseq [webhook (keys webhooks)]
    (if-let [handler (:handler (webhooks webhook))]
      (try
        (require (symbol handler))
        (catch Exception e 
          (println "Warning: could not load handler for" handler)
          (println e))))))

;; Consume the webhook channel with 100 workers
(doseq [n (range 100)]
  (go-loop []
    (call-webhook (<! webhook-chan))
    (recur)))