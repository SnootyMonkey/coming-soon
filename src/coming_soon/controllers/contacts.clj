(ns coming-soon.controllers.contacts
  (:use [compojure.core :only (defroutes GET POST)]
  		coming-soon.views.contacts))

(defn index-req [format] (apply str (admin-page)))

(defn create-req [contact] {:body (pr-str {:foo "bar"})})

(defroutes contact-routes
  ; users
  (GET "/" [] (apply str (home-page)))
  (POST "/subscribe" [contact] (create-req contact))
  ; admins
  (GET "/contacts" [] (index-req :html))
  (GET "/contacts.json" [] (index-req :json))
  (GET "/contacts.csv" [] (index-req :csv))
  (GET "/contacts.xml" [] (index-req :xml)))