(ns coming-soon.controllers.contacts
  (:use [compojure.core :only (defroutes GET POST)]
        [coming-soon.models.contact :as contact]
        [coming-soon.models.email :as email]
    		coming-soon.views.contacts))

(defn create-req [email referrer]
  (if-not (email/valid? email)
    {:status 403 :body (pr-str {:contact "invalid"})}
    (if (contact/create email referrer)
      {:status 200 :body (pr-str {:contact "created"})}
      {:status 500 :body (pr-str {:contact "error"})})))

(defroutes contact-routes
  (GET "/" [:as {headers :headers}]
    (apply str (home-page (get headers "referer" ""))))
  (POST "/subscribe" [email referrer]
    (create-req email referrer)))