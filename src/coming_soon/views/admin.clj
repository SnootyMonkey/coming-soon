(ns coming-soon.views.admin
  (:use [net.cgrand.enlive-html])
  (:require [coming-soon.models.contact :refer (timestamp-format)]
            [coming-soon.views.contacts :refer (google-font-url)]
            [coming-soon.config :refer (landing-page)]
            [coming-soon.helpers.colors :refer (rgb-color rgba-color)]
            [clj-time.format :refer (formatter with-zone parse unparse)]
            [clj-time.core :refer (to-time-zone default-time-zone)]))

(def output-format (with-zone (formatter "M-d-yyyy h:m a") (default-time-zone)))

(deftemplate admin-page "coming_soon/templates/admin.html" [contacts]
  [:#google-title-font] (set-attr :href (str google-font-url (landing-page :app-title-font)))
  [:#configured-styles] (html-content (str 
    "body {background-color:" (landing-page :background-color) "}"
    "#main-container {background:" (rgb-color (landing-page :container-bg-color)) ";"
    "background:" (rgba-color (landing-page :container-bg-color) (landing-page :container-opacity)) ";}"
    "#app-title {color:" (landing-page :app-title-color) ";"
      "font-family:" (landing-page :app-title-font) "," (landing-page :app-title-backup-fonts) ";}"))
  [:#app-title] (html-content (landing-page :app-title))
  [:table.contacts :tbody :tr] (clone-for [contact contacts]
    [:tr first-child] (content (str (:id contact)))
    [:tr (nth-child 2)] (content (:email contact))
    [:tr (nth-child 3)] (content (html [:a {:href (:referrer contact) :target "_new"} (:referrer contact)]))
    [:tr (nth-child 4)] (content (unparse output-format (to-time-zone (parse timestamp-format (:updated-at contact)) (default-time-zone))))))