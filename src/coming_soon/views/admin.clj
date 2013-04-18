(ns coming-soon.views.admin
  (:use [net.cgrand.enlive-html]))

(deftemplate admin-page "coming_soon/templates/admin.html" [contacts]
  [:table.contacts :tbody :tr] (clone-for [contact contacts]
    [:tr first-child] (content (str (:id contact)))
    [:tr (nth-child 2)] (content (:email contact))
    [:tr (nth-child 3)] (content (:referrer contact))
    [:tr last-child] (content (str (:updated-at contact)))))