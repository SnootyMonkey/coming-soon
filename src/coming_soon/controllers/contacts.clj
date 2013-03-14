(ns coming-soon.controllers.contacts
  (:use [compojure.core :only (defroutes GET POST)]
        [coming-soon.models.contact :as contact]
    		coming-soon.views.contacts))

(defn index-req [format] (apply str (admin-page)))

(defn create-req [email]
  (if-not (contact/valid? email)
    {:status 403 :body (pr-str {:contact "invalid"})}
    (if (contact/create email)
      {:status 200 :body (pr-str {:contact "created"})}
      {:status 500 :body (pr-str {:contact "error"})})))

(defroutes contact-routes
  ; users
  (GET "/" [] (apply str (home-page)))
  (POST "/subscribe" [email] (create-req email))
  ; admins
  (GET "/contacts" [] (index-req :html))
  (GET "/contacts.json" [] (index-req :json))
  (GET "/contacts.csv" [] (index-req :csv))
  (GET "/contacts.xml" [] (index-req :xml)))