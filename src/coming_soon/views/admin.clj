(ns coming-soon.views.admin
  (:use [net.cgrand.enlive-html]
        [coming-soon.models.contact :only (timestamp-format)]
        [clj-time.format :only (formatter with-zone parse unparse)]
        [clj-time.core :only (to-time-zone default-time-zone)]))

(def output-format (with-zone (formatter "M-d-yyyy h:m a") (default-time-zone)))

(deftemplate admin-page "coming_soon/templates/admin.html" [contacts]
  [:table.contacts :tbody :tr] (clone-for [contact contacts]
    [:tr first-child] (content (str (:id contact)))
    [:tr (nth-child 2)] (content (:email contact))
    [:tr (nth-child 3)] (content (:referrer contact))
    [:tr last-child] (content (unparse output-format (to-time-zone (parse timestamp-format (:updated-at contact)) (default-time-zone))))))