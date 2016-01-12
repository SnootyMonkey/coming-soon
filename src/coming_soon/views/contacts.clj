(ns coming-soon.views.contacts
  (:require [clojure.string :refer (blank?)]
            [net.cgrand.enlive-html :refer :all]
            [coming-soon.config :refer (landing-page)]
            [coming-soon.lib.colors :refer (rgb-color rgba-color)]))

(def google-font-url "//fonts.googleapis.com/css?family=")

(defn- analytics-content []
  (let [analytics (first (html-resource "coming_soon/templates/analytics.html"))]
    ;; strip the <html></html> tags if necessary
    (if (= :html (:tag analytics))
      (:content analytics)
      analytics)))

(defn- blog-feed-content []
  (html [:link {:rel "alternate" :type "application/rss+xml" :href (landing-page :blog-feed)}]))

(defn- linked-icon [{:keys [link-name icon-name]}]
  (let [url (landing-page (keyword (str link-name "-url")))
        title (landing-page (keyword (str link-name "-title")))]
    (if-not (blank? url)
      (html [:li [:a {:href url :title title :class "social-link" :target "_new"}
        [:i {:class (str "fa fa-" icon-name " fa-lg")}]]]))))

(defn- signup-button-icon []
  (let [signup-btn-icon-class (landing-page :signup-btn-icon)]
    (if-not (blank? signup-btn-icon-class)
      (html [:i {:id "submit-icon" :class (str "fa " signup-btn-icon-class)}] " "))))

(defn- background-image []
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
    "#error-message {color:" (landing-page :error-color) ";}"
    "#spam-msg {color:" (landing-page :spam-msg-color) ";}"
    "a.social-link {color:" (landing-page :social-color) ";}"
    "a.social-link:hover {color:" (landing-page :social-hover-color) ";}"
    "#copyright {color:" (landing-page :copyright-color) ";}"))
  [:head] (append
    (if-not (blank? (landing-page :blog-feed))
      (blog-feed-content)
      ""))

  ;; body
  [:#backstretch] (html-content (background-image))

  ;; container
  [:img.logo] (set-attr :src (landing-page :logo))
  [:#app-title] (html-content (landing-page :app-title))
  [:#app-tagline] (html-content (landing-page :app-tagline))
  [:#app-summary] (html-content (landing-page :app-summary))
  [:#instructions] (html-content (landing-page :instructions))
  [:#thank-you] (html-content (landing-page :thank-you))
  [:#error-message] (html-content (landing-page :error))
  [:#email] (set-attr :placeholder (landing-page :placeholder))
  [:#referrer] (set-attr :value referrer)
  [:#submit] (add-class (landing-page :signup-btn-class))
  [:#submit] (content (signup-button-icon) (landing-page :sign-up-btn))
  [:#spam-msg] (html-content (landing-page :spam-msg))
  [:#social-links] (content (map linked-icon [
    {:link-name "twitter" :icon-name "twitter"}
    {:link-name "facebook" :icon-name "facebook"}
    {:link-name "github" :icon-name "github"}
    {:link-name "blog" :icon-name "rss"}]))

  ;; footer
  [:#copyright] (html-content (landing-page :copyright))

  ;; scripts
  [:body] (append (if (landing-page :analytics) (analytics-content) "")))