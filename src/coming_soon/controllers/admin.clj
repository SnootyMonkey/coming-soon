(ns coming-soon.controllers.admin
  (:use [compojure.core :only (defroutes GET POST)]
        [coming-soon.models.contact :only (all-contacts)]
        [coming-soon.config :only (coming-soon)]
        coming-soon.views.admin)
  (:require [clj-json.core :as json]))

(defn html-contacts []
  (apply str (admin-page (all-contacts))))

(defn json-contacts []
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string (all-contacts))})

(defn authenticated? [username password]
  (and 
    (= username (coming-soon :admin-user))
    (= password (coming-soon :admin-password))))

(defroutes admin-routes
  (GET "/contacts" [] (html-contacts))
  (GET "/contacts.json" [] (json-contacts)))
  ;(GET "/contacts.csv" [] (index-req :csv))
  ;(GET "/contacts.xml" [] (index-req :xml)))