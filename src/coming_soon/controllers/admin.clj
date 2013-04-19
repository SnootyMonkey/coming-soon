(ns coming-soon.controllers.admin
  (:use [compojure.core :only (defroutes GET POST)]
        [coming-soon.models.contact :only (all-contacts)]
        [coming-soon.config :only (coming-soon)]
        coming-soon.views.admin))

(defn index-req [format] (apply str (admin-page (all-contacts))))

(defn authenticated? [username password]
  (and 
    (= username (coming-soon :admin-user))
    (= password (coming-soon :admin-password))))

(defroutes admin-routes
  (GET "/contacts" [] (index-req :html))
  (GET "/contacts.json" [] (index-req :json))
  (GET "/contacts.csv" [] (index-req :csv))
  (GET "/contacts.xml" [] (index-req :xml)))