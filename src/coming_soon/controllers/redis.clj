(ns coming-soon.controllers.redis
  (:require [compojure.core :refer (defroutes GET)]
            [hiccup.core :refer (html)]
            [coming-soon.lib.redis :refer (redis-conn)]
            [taoensso.carmine :as car]))

(def head (html [:head [:title "Redis Test"]]))

(defn- response-content [status]
  (let [
    color (if status "green" "red")
    label (if status "OK" "BORKED")]
    (html [:html head [:body "Connection to Redis is: "
          [:span {:style (str "font-weight: bold;color:" color ";")} label]]])))

(def redis-ok {:status 200 :body (response-content true)})
(def redis-borked {:status 500 :body (response-content false)})

(defn- redis-test []
  (try
    (let [response
      (car/wcar redis-conn (car/ping))]
      (if (= response "PONG")
        redis-ok
        redis-borked))
    (catch Exception e redis-borked)))

(defroutes test-routes
  (GET "/redis-test" [] (redis-test)))