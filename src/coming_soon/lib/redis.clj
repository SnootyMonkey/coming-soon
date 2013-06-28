(ns coming-soon.lib.redis
  (:require [taoensso.carmine :as car]
            [coming-soon.config :as config]
            [clojure.string :refer (blank?)]))

(defn conn-spec
  "Determine if we should connect with a Redis URL, and if so, which one"
  []
  (let [
    url (config/redis :redis-connect-URL)
    env (config/redis :redis-env-variable)]
    (if-not (blank? url)
      (car/make-conn-spec :uri url)
      (if-not (blank? env)
        (car/make-conn-spec :uri (get (System/getenv) env))
        (car/make-conn-spec)))))

;; A Redis connection with Carmine
(defonce redis-pool (car/make-conn-pool))
(def redis-server-spec (conn-spec))
(defmacro with-car [& body] `(car/with-conn redis-pool redis-server-spec ~@body))

;; Instance specific namespace
(def prefix (config/coming-soon :instance-prefix))