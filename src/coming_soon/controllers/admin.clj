(ns coming-soon.controllers.admin
  (:use [compojure.core :only (defroutes GET POST)]
        [coming-soon.models.contact :only (all-contacts)]
        coming-soon.views.admin))

(defn index-req [format] (apply str (admin-page (all-contacts))))

(defroutes admin-routes
  (GET "/contacts" [] (index-req :html))
  (GET "/contacts.json" [] (index-req :json))
  (GET "/contacts.csv" [] (index-req :csv))
  (GET "/contacts.xml" [] (index-req :xml)))