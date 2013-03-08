(ns coming-soon.app
  (:use compojure.core)
  (:require [coming-soon.controllers.contacts :as contacts]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  contacts/contact-routes
  (route/resources "/")
  (route/not-found "Page Not Found"))

(def app
  (handler/site app-routes))