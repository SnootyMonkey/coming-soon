(ns coming-soon.views.admin
  (:require [net.cgrand.enlive-html :refer :all]
            [clj-time.format :refer (formatter with-zone parse unparse)]
            [clj-time.core :refer (to-time-zone default-time-zone)]
            [coming-soon.models.contact :refer (timestamp-format)]
            [coming-soon.config :refer (landing-page)]))

(def output-format (with-zone (formatter "M-d-yyyy h:mm a") (default-time-zone)))

(deftemplate admin-page "coming_soon/templates/admin.html" [contacts]
  [:#app-title] (html-content (landing-page :app-title))
  [:table.contacts :tbody :tr] (clone-for [contact contacts]
    [:tr first-child] (content (str (:id contact)))
    [:tr (nth-child 2)] (content (:email contact))
    [:tr (nth-child 3)] (content (html [:a {:href (:referrer contact) :target "_new"} (:referrer contact)]))
    [:tr (nth-child 4)] (content (unparse output-format (to-time-zone (parse timestamp-format (:updated-at contact)) (default-time-zone))))))