(ns coming-soon.lib.redis
  (:require [taoensso.carmine :as car]
            [coming-soon.config :as config]
            [clojure.string :refer (blank?)]))

;; Redis config
(defn- conn-spec
  "Determine if we should connect with a Redis URL, and if so, which one"
  []
  (let [url (config/redis :redis-connect-URL)
        env (config/redis :redis-env-variable)]
    (cond
      (not (blank? url)) {:uri url}
      (not (blank? env)) {:uri (get (System/getenv) env)}
      :else {})))

;; A Redis connection with Carmine
(def redis-conn {:pool {} :spec (conn-spec)})
(defmacro with-car [& body] `(car/wcar redis-conn ~@body))

;; Instance specific namespace
(def prefix (config/coming-soon :instance-prefix))