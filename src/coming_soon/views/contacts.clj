(ns coming-soon.views.contacts
  (:use [net.cgrand.enlive-html])
  (:require [coming-soon.config :as config]))

(deftemplate home-page "coming_soon/templates/home.html" []
  [:title] (content (config/landing-page :page-title)))

(deftemplate admin-page "coming_soon/templates/admin.html" []
  [:title] (content (config/landing-page :page-title)))
