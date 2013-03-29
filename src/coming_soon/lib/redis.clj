(ns coming-soon.lib.redis
  (:require [taoensso.carmine :as car]
            [coming-soon.config :as config])
  (:use [clojure.string :only (blank?)]))

(defn conn-spec
  "Determine if we should connect with a Redis URI, and if so, which one"
  []
  (let [
    uri (config/redis :redis-connect-URI)
    env (config/redis :redis-env-variable)]
    (if-not (blank? uri)
      (car/make-conn-spec :uri uri)
      (if-not (blank? env)
        (car/make-conn-spec :uri (get (System/getenv) env))
        (car/make-conn-spec)))))

;; A Redis connection with Carmine
(def redis-pool (car/make-conn-pool))
(def redis-server-spec (conn-spec))
(defmacro with-car [& body] `(car/with-conn redis-pool redis-server-spec ~@body))

;; Instance specific namespace
(def prefix (config/coming-soon :instance-prefix))