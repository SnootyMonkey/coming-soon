(ns coming-soon.controllers.contacts
  (:require [clojure.string :as s]
            [compojure.core :refer (defroutes GET POST)]
            [coming-soon.models.contact :as contact]
            [coming-soon.models.email :as email]
    		    [coming-soon.views.contacts :as view]
            [coming-soon.lib.webhooks :as webhooks]))

(defn- create-req [email referrer]
  (if-not (email/valid? email)
    {:status 403 :body (pr-str {:contact "invalid"})}
    (if (contact/create email referrer)
      (do
        (webhooks/call email referrer) ; invoke configured webhooks asynchronously
        {:status 200 :body (pr-str {:contact "created"})})
      {:status 500 :body (pr-str {:contact "error"})})))

(defroutes contact-routes
  (GET "/" [:as {headers :headers}]
    (s/join (view/home-page (get headers "referer" ""))))
  (POST "/subscribe" [email referrer]
    (create-req email referrer)))