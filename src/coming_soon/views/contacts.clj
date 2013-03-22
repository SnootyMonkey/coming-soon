(ns coming-soon.views.contacts
  (:use [clojure.string :only (blank?)]
        [net.cgrand.enlive-html]
        [coming-soon.config :only (landing-page)]))

(def google-font-url "http://fonts.googleapis.com/css?family=")

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

(deftemplate home-page "coming_soon/templates/home.html" [referrer]
  ;; head
  [:title] (content (landing-page :page-title))
  [:#google-title-font] (set-attr :href (str google-font-url (landing-page :app-title-font)))
  [:#google-body-font] (set-attr :href (str google-font-url (landing-page :body-font)))
  [:#social-link-style] (html-content (str 
      "a.social-link {color:" (landing-page :social-color) ";}"
      "a.social-link:hover {color:" (landing-page :social-hover-color) ";}"))
  ;; body
  [:body] (set-attr :style (str "background-color:" (landing-page :bg-color) ";"
                                "font-family:" (str (landing-page :body-font) ","
                                (landing-page :body-backup-fonts) ";")))
  ;; container
  [:#main-container] (set-attr :style (str "background-color:" (landing-page :container-bg-color) ";"))
  [:#app-title] (set-attr :style (str "color:" (landing-page :app-title-color) ";"
                                      "font-family:" (str (landing-page :app-title-font) ","
                                      (landing-page :app-title-backup-fonts) ";")))
  [:#app-title] (html-content (landing-page :app-title))
  [:#app-tagline] (set-attr :style (str "color:" (landing-page :app-tagline-color) ";"))
  [:#app-tagline] (html-content (landing-page :app-tagline))
  [:#app-summary] (set-attr :style (str "color:" (landing-page :app-summary-color) ";"))
  [:#app-summary] (html-content (landing-page :app-summary))
  [:#instructions] (set-attr :style (str "color:" (landing-page :instructions-color) ";"))
  [:#instructions] (html-content (landing-page :instructions))
  [:#thank-you] (set-attr :style (str "color:" (landing-page :thank-you-color) ";"))
  [:#thank-you] (html-content (landing-page :thank-you))
  [:#email] (set-attr :placeholder (landing-page :placeholder))
  [:#referrer] (set-attr :value referrer)
  [:#submit] (add-class (landing-page :signup-btn-class))  
  [:#submit] (html-content (str (signup-button-icon) (landing-page :sign-up-btn)))
  [:#spam-msg] (set-attr :style (str "color:" (landing-page :spam-msg-color) ";"))
  [:#spam-msg] (html-content (landing-page :spam-msg))
  [:#social-links] (html-content (apply str (map linked-icon
    [{:link-name "twitter" :icon-name "twitter"}
    {:link-name "facebook" :icon-name "facebook"}
    {:link-name "github" :icon-name "github"}
    {:link-name "blog" :icon-name "rss"}])))
  [:#copyright] (set-attr :style (str "color:" (landing-page :copyright-color) ";")) 
  [:#copyright] (html-content (landing-page :copyright)))

(deftemplate admin-page "coming_soon/templates/admin.html" []
  [:title] (content (landing-page :page-title)))