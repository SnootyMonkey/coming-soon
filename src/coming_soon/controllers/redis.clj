(ns coming-soon.controllers.redis
  (:use [compojure.core :only (defroutes GET)])
  (:require [taoensso.carmine :as car]))

(def redis-ok {:status 200 :body "<html><body>Connection to Redis is OK.</body></html>"})
(def redis-borked {:status 500 :body "<html><body>Connection to Redis is borked.</body></html>"})

(defn redis-test []
  (try
    (let [response
      (car/with-conn (car/make-conn-pool) (car/make-conn-spec) (car/ping))]
      (if (= response "PONG")
        redis-ok
        redis-borked))
    (catch Exception e redis-borked)))

(defroutes test-routes
  (GET "/redis-test" [] (redis-test)))