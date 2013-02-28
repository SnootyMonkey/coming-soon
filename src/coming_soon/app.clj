(ns coming-soon.app
  (:use compojure.core)
  (:require [coming-soon.controllers.contacts :as contacts]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(def config (read-string (slurp "config.edn")))

(defroutes app-routes
  contacts/contact-routes
  (route/not-found "Page Not Found"))

(def app
  (handler/site app-routes))