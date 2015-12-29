(ns coming-soon.controllers.contacts
  (:require [compojure.core :refer (routes GET POST)]
            [coming-soon.models.contact :as contact]
            [coming-soon.models.email :as email]
    		    [coming-soon.views.contacts :as view]
            [coming-soon.config :as config]
            [coming-soon.lib.webhooks :as webhooks]))

(defn- create-req [email referrer]
  (if-not (email/valid? email)
    {:status 403 :body (pr-str {:contact "invalid"})}
    (if (contact/create email referrer)
      (do
        (webhooks/call email referrer) ; invoke configured webhooks asynchronously
        {:status 200 :body (pr-str {:contact "created"})})
      {:status 500 :body (pr-str {:contact "error"})})))

(def contact-routes
  (apply routes
    (conj
      (map #(GET % request
        (view/home-page (get (request :headers) "referer" "") (request :uri)))
        ;; route for / and any defined variations
        (conj (map :route config/variations) "/"))
      ;; route for POSTing new subscribers
      (POST "/subscribe" [email referrer] (create-req email referrer)))))