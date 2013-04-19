(ns coming-soon.app
  (:use [compojure.core :only (defroutes)]
        [ring.middleware.params :only (wrap-params)]
        [ring.adapter.jetty :as ring]
        [ring.middleware.basic-authentication :only (wrap-basic-authentication)])
  (:require [coming-soon.controllers.contacts :as contacts]
            [coming-soon.controllers.admin :as admin]
            [coming-soon.controllers.redis :as redis]
            [compojure.route :as route]))

(defroutes app-routes
  contacts/contact-routes
  (wrap-basic-authentication admin/admin-routes admin/authenticated?)
  redis/test-routes
  (route/resources "/")
  (route/not-found "Page Not Found"))

(def app (wrap-params app-routes))

(defn start [port]
  (run-jetty app {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt 
       (or (System/getenv "PORT") "8000"))]
  (start port)))