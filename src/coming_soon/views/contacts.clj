(ns coming-soon.views.contacts
  (:use [net.cgrand.enlive-html])
  (:require [coming-soon.config :as config]))

(def google-font-url "http://fonts.googleapis.com/css?family=")

(deftemplate home-page "coming_soon/templates/home.html" [referrer]
  ;; head
  [:title] (content (config/landing-page :page-title))
  [:link#google-title-font] (set-attr :href (str google-font-url (config/landing-page :app-title-font)))
  [:link#google-body-font] (set-attr :href (str google-font-url (config/landing-page :body-font)))
  ;; body
  [:body] (set-attr :style (str "background-color:" (config/landing-page :bg-color) ";"
                                "font-family:" (str (config/landing-page :body-font) ","
                                (config/landing-page :body-backup-fonts) ";")))
  ;; container
  [:div#main-container] (set-attr :style (str "background-color:" (config/landing-page :container-bg-color) ";"))
  [:span#app-title] (set-attr :style (str "color:" (config/landing-page :app-title-color) ";"
                                      "font-family:" (str (config/landing-page :app-title-font) ","
                                      (config/landing-page :app-title-backup-fonts) ";")))
  [:span#app-title] (content (config/landing-page :app-title))
  [:span#app-tagline] (set-attr :style (str "color:" (config/landing-page :app-tagline-color) ";"))
  [:span#app-tagline] (content (config/landing-page :app-tagline))
  [:p#app-summary] (set-attr :style (str "color:" (config/landing-page :app-summary-color) ";"))
  [:p#app-summary] (content (config/landing-page :app-summary))
  [:span#instructions] (set-attr :style (str "color:" (config/landing-page :instructions-color) ";"))
  [:span#instructions] (content (config/landing-page :instructions))
  [:input#email] (set-attr :placeholder (config/landing-page :placeholder))
  [:input#referrer] (set-attr :value referrer)
  [:button#submit] (content (config/landing-page :sign-up-btn))
  [:p#spam-msg] (set-attr :style (str "color:" (config/landing-page :spam-msg-color) ";"))
  [:p#spam-msg] (content (config/landing-page :spam-msg)))

(deftemplate admin-page "coming_soon/templates/admin.html" []
  [:title] (content (config/landing-page :page-title)))