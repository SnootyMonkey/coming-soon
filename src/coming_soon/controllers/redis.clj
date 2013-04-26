(ns coming-soon.controllers.redis
  (:require [compojure.core :refer (defroutes GET)]
            [coming-soon.lib.redis :refer (redis-pool redis-server-spec)]
            [taoensso.carmine :as car]))

(def pre "<html><head><title>Redis Test</title></head>")
(defn body [status] 
  (let [
    color (if status "green" "red")
    label (if status "OK" "BORKED")]
    (str "<body>Connection to Redis is: <span style='font-weight: bold;color:" color ";'>" label "</span></body>")))
(def post "</html>")

(def redis-ok {:status 200 :body (str pre (body true) post)})
(def redis-borked {:status 500 :body (str pre (body false) post)})

(defn redis-test []
  (try
    (let [response
      (car/with-conn redis-pool redis-server-spec (car/ping))]
      (if (= response "PONG")
        redis-ok
        redis-borked))
    (catch Exception e redis-borked)))

(defroutes test-routes
  (GET "/redis-test" [] (redis-test)))