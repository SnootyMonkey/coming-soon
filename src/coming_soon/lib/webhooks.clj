(ns coming-soon.lib.webhooks
  (:require [clojure.core.async :as a :refer [chan sliding-buffer put! go-loop <!]]
            [coming-soon.config :as config]
            [coming-soon.webhooks.webhook :as webhook]))

(def ^:private webhook-chan (chan (sliding-buffer 1000))) ; buffered channel with up to 1,000 entries without loosing any

(defn call
  "Send an async message for each configured webhook."
  [email referrer]
  (let [webhooks config/webhooks]
    (doseq [webhook (keys webhooks)]
      (put! webhook-chan {:webhook webhook :email email :referrer referrer :config (webhooks webhook)}))))

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
    (webhook/invoke (<! webhook-chan))
    (recur)))