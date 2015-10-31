(ns coming-soon.lib.webhooks
  (:require [clojure.core.async :as a :refer [chan sliding-buffer put! go-loop <!]]
            [coming-soon.config :as config]
            [coming-soon.webhooks.webhook :as webhook]))

(def ^:private webhook-chan (chan (sliding-buffer 1000))) ; buffered channel with up to 1,000 entries without loosing any

(defn call
  "Send an async message for each configured webhook."
  [email referrer]
  (doseq [webhook (keys config/webhooks)]
    (put! webhook-chan {:webhook webhook :email email :referrer referrer :config (get config/webhooks webhook)})))

;; Dynamically require each configured webhook's handler's namespace
(doseq [webhook (keys config/webhooks)]
  (if-let [handler (:handler (get config/webhooks webhook))]
    (try
      (require (symbol handler))
      (catch Exception e 
        (println "Warning: could not load handler for" handler)
        (println e)))))

;; Consume the webhook channel with 100 workers
(dorun 100 (repeatedly
  #(go-loop []
    (webhook/invoke (<! webhook-chan))
    (recur))))