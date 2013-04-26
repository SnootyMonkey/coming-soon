(ns coming-soon.views.contacts
  (:use [net.cgrand.enlive-html])
  (:require [clojure.string :refer (blank?)]
            [coming-soon.config :refer (landing-page)]
            [coming-soon.helpers.colors :refer (rgb-color rgba-color)]))

(def google-font-url "http://fonts.googleapis.com/css?family=")

(defn analytics-content []
  (let [analytics (first (html-resource "coming_soon/templates/analytics.html"))]
    ;; strip the <html></html> tags if necessary
    (if (= :html (:tag analytics))
      (:content analytics)
      analytics)))

(defn linked-icon [{:keys [link-name icon-name]}]
  (let [url (landing-page (keyword (str link-name "-url")))]
    (if-not (blank? url)
      (str "<li><a class='social-link' href='" url "'><i class='icon-" icon-name " icon-large'></i></a></li>")
      "")))

(defn signup-button-icon []
  (let [signup-btn-icon-class (landing-page :signup-btn-icon)]
    (if-not (blank? signup-btn-icon-class)
      (str "<i id='submit-icon' class='" signup-btn-icon-class "''></i> ")
      "")))

(defn background-image []
  (let [image-url (landing-page :background-image)]
    (if-not (blank? image-url)
      (str "$.backstretch('" image-url "');")
      "")))

(deftemplate home-page "coming_soon/templates/home.html" [referrer]
  ;; head
  [:title] (content (landing-page :page-title))
  [:#google-title-font] (set-attr :href (str google-font-url (landing-page :app-title-font)))
  [:#google-body-font] (set-attr :href (str google-font-url (landing-page :body-font)))
  [:#configured-styles] (html-content (str 
    "body {background-color:" (landing-page :background-color) ";"
      "font-family:" (landing-page :body-font) "," (landing-page :body-backup-fonts) ";}"
    "#main-container {background:" (rgb-color (landing-page :container-bg-color)) ";"
    "background:" (rgba-color (landing-page :container-bg-color) (landing-page :container-opacity)) ";}"
    "img.logo {display:" (if (blank? (landing-page :logo)) "none" "inline") ";}"
    "#app-title {color:" (landing-page :app-title-color) ";"
      "font-family:" (landing-page :app-title-font) "," (landing-page :app-title-backup-fonts) ";}"
    "#app-tagline {color:" (landing-page :app-tagline-color) ";}"
    "#app-summary {color:" (landing-page :app-summary-color) ";}"
    "#instructions {color:" (landing-page :instructions-color) ";}"
    "#thank-you {color:" (landing-page :thank-you-color) ";}"
    "#spam-msg {color:" (landing-page :spam-msg-color) ";}"
    "a.social-link {color:" (landing-page :social-color) ";}"
    "a.social-link:hover {color:" (landing-page :social-hover-color) ";}"
    "#copyright {color:" (landing-page :copyright-color) ";}"))

  ;; body
  [:#backstretch] (html-content (background-image))

  ;; container
  [:img.logo] (set-attr :src (landing-page :logo))
  [:#app-title] (html-content (landing-page :app-title))
  [:#app-tagline] (html-content (landing-page :app-tagline))
  [:#app-summary] (html-content (landing-page :app-summary))
  [:#instructions] (html-content (landing-page :instructions))
  [:#thank-you] (html-content (landing-page :thank-you))
  [:#email] (set-attr :placeholder (landing-page :placeholder))
  [:#referrer] (set-attr :value referrer)
  [:#submit] (add-class (landing-page :signup-btn-class))  
  [:#submit] (html-content (str (signup-button-icon) (landing-page :sign-up-btn)))
  [:#spam-msg] (html-content (landing-page :spam-msg))
  [:#social-links] (html-content (apply str (map linked-icon
    [{:link-name "twitter" :icon-name "twitter"}
    {:link-name "facebook" :icon-name "facebook"}
    {:link-name "github" :icon-name "github"}
    {:link-name "blog" :icon-name "rss"}])))

  ;; footer
  [:#copyright] (html-content (landing-page :copyright))

  ;; scripts
  [:body] (append
    (if (landing-page :analytics) 
      (analytics-content)
      "")))