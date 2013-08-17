(require '[coming-soon.lib.check :refer (check)]
         '[coming-soon.lib.browser :refer (browser-up browser-down)]
         '[clj-webdriver.taxi :as taxi]
         '[coming-soon.config :refer (landing-page)])

(def email-field "input#email")
(def submit "#submit")
(def thank-you "span#thank-you")
(def error-message "span#error-message")

(When #"^I go to the site$" []
  (browser-up)
  (taxi/to "http://localhost:3000/"))

;; TODO validate all the site content
(Then #"^the page has the right content$" []
  (check (= (taxi/title) (landing-page :page-title)))
  (check (= (taxi/text "span#app-title") (landing-page :app-title)))
  (check (= (taxi/text "span#app-tagline") (landing-page :app-tagline))))

(When #"^I provide my email \"([^\"]*)\"$" [email]
  (taxi/clear email-field)
  (taxi/input-text email-field email)
  (taxi/click submit)
  (Thread/sleep 6000))

(Then #"^I see the thank-you message$" []
  (check (taxi/displayed? thank-you)))

(Then #"^I don't see the thank-you message$" []
  (check (not (taxi/displayed? thank-you))))

(Then #"^I don't see the error message$" []
  (check (not (taxi/displayed? error-message))))

(Then #"^I'm done$" []
  (browser-down :force true))