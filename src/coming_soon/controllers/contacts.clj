(ns coming-soon.controllers.contacts
  (:use [compojure.core :only (defroutes GET POST)]
        [coming-soon.models.contact :as contact]
        [coming-soon.models.email :as email]
    		coming-soon.views.contacts))

(defn index-req [format] (apply str (admin-page)))

(defn create-req [email referer]
  (if-not (email/valid? email)
    {:status 403 :body (pr-str {:contact "invalid"})}
    (if (contact/create email referer)
      {:status 200 :body (pr-str {:contact "created"})}
      {:status 500 :body (pr-str {:contact "error"})})))

(defroutes contact-routes
  ; users
  (GET "/" [:as {headers :headers}]
    (apply str (home-page {:referer (get headers "referer" "")})))
  (POST "/subscribe" [email referer]
    (create-req email referer))
  ; admins
  (GET "/contacts" [] (index-req :html))
  (GET "/contacts.json" [] (index-req :json))
  (GET "/contacts.csv" [] (index-req :csv))
  (GET "/contacts.xml" [] (index-req :xml)))