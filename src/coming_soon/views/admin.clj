(ns coming-soon.views.admin
  (:use [net.cgrand.enlive-html])
  (:require [clj-time.coerce :as coerce]
            [clj-time.format :as format]))

(def date-format (format/formatter "M-d-yyyy h:m a"))

(deftemplate admin-page "coming_soon/templates/admin.html" [contacts]
  [:table.contacts :tbody :tr] (clone-for [contact contacts]
    [:tr first-child] (content (str (:id contact)))
    [:tr (nth-child 2)] (content (:email contact))
    [:tr (nth-child 3)] (content (:referrer contact))
    [:tr last-child] (content (format/unparse date-format (coerce/from-string (:updated-at contact))))))