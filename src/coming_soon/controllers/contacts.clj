(ns coming-soon.controllers.contacts
  (:use [compojure.core :only (defroutes GET POST)]))

(defn new-req [] (str "<h1>new</h1>"))

(defn index-req [format] (str "<h1>index " format "</h1>"))

(defn create-req [contact] (str "<h1>create</h1>"))

(defroutes contact-routes
  ; users
  (GET "/" [] (new-req))
  (POST "/subscribe" [contact] (create-req contact))
  ; admins
  (GET "/contacts" [] (index-req :html))
  (GET "/contacts.json" [] (index-req :json))
  (GET "/contacts.csv" [] (index-req :csv))
  (GET "/contacts.xml" [] (index-req :xml)))