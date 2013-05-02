(ns coming-soon.app
  (:require [compojure.core :refer (defroutes ANY)]
            [ring.middleware.params :refer (wrap-params)]
            [ring.adapter.jetty :as ring]
            [ring.middleware.basic-authentication :refer (wrap-basic-authentication)]
            [coming-soon.controllers.contacts :as contacts]
            [coming-soon.controllers.admin :as admin]
            [coming-soon.controllers.redis :as redis]
            [compojure.route :as route]))

(defroutes app-routes
  contacts/contact-routes
  redis/test-routes
  (ANY "/contacts*" []
    (wrap-basic-authentication admin/admin-routes admin/authenticated?))
  (route/resources "/")
  (route/not-found "Page Not Found"))
  
(def app (wrap-params app-routes))

(defn start [port]
  (ring/run-jetty app {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt 
       (or (System/getenv "PORT") "8000"))]
  (start port)))