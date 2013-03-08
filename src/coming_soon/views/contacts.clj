(ns coming-soon.views.contacts
  (:use [net.cgrand.enlive-html])
  (:require [coming-soon.config :as config]))

(def backup-fonts "Arial,Helvetica,sans-serif")

(def google-font-url "http://fonts.googleapis.com/css?family=")

(deftemplate home-page "coming_soon/templates/home.html" []
  [:title] (content (config/landing-page :page-title))
  [:link#google-title-font] (set-attr :href (str google-font-url (config/landing-page :app-title-font)))
  [:link#google-body-font] (set-attr :href (str google-font-url (config/landing-page :body-font)))
  [:h1#app-title] (content (config/landing-page :app-title))
  [:h1#app-title] (set-attr :style (str "font-family:" (str (config/landing-page :app-title-font) "," backup-fonts)))
  [:span#app-summary] (content (config/landing-page :app-summary))
  [:body] (set-attr :style (str "background-color:" (config/landing-page :bg-color) ";"
                                "font-family:" (str (config/landing-page :app-body-font) "," backup-fonts))))

(deftemplate admin-page "coming_soon/templates/admin.html" []
  [:title] (content (config/landing-page :page-title)))