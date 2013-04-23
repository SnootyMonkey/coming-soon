(ns coming-soon.controllers.admin
  (:use [compojure.core :only (defroutes GET POST)]
        [coming-soon.models.contact :only (all-contacts)]
        [coming-soon.config :only (coming-soon)]
        coming-soon.views.admin)
  (:require [clj-json.core :as json]
            [clojure.data.xml :as xml]))

(defn html-contacts []
  (apply str (admin-page (all-contacts))))

(defn json-contacts []
  {:status 200
   :headers {"Content-Type" "application/json;charset=utf-8"}
   :body (json/generate-string (all-contacts))})

(defn xml-contact [contact]
  (xml/element :contact {:id (str (:id contact))} [
    (xml/element :email {} (:email contact))
    (xml/element :referrer {} (:referrer contact))
    (xml/element :updated-at {} (:updated-at contact))]))

(defn xml-contacts []
  {:status 200
   :headers {"Content-Type" "application/xml;charset=utf-8"}
   :body (xml/emit-str (xml/element :contacts {} 
      (map xml-contact (all-contacts))))})

(defn authenticated? [username password]
  (and 
    (= username (coming-soon :admin-user))
    (= password (coming-soon :admin-password))))

(defroutes admin-routes
  (GET "/contacts" [] (html-contacts))
  (GET "/contacts.html" [] (html-contacts))
  (GET "/contacts.json" [] (json-contacts))
  (GET "/contacts.xml" [] (xml-contacts)))
  ;(GET "/contacts.csv" [] (index-req :csv))