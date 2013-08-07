(require '[coming-soon.lib.check :refer (check)]
         '[coming-soon.lib.browser :refer (browser-up browser-down)]
         '[clj-webdriver.taxi :as taxi]
         '[coming-soon.config :refer (landing-page)])

(def email-field "input#email")
(def submit "#submit")
(def thank-you "span#thank-you")
(def error-message "span#error-message")

;; TODO validate all the site content
(When #"^I go to the site$" []
  (browser-up)
  (taxi/to "http://localhost:3000/")
  (check (= (taxi/title) (landing-page :page-title))))

(When #"^I provide my email \"([^\"]*)\"$" [email]
  (taxi/input-text email-field email)
  (taxi/click submit)
  (Thread/sleep 1000))

(Then #"^I see the thank-you message$" []
  (check (taxi/displayed? thank-you)))

(Then #"^I don't see the thank-you message$" []
  (check (not (taxi/displayed? thank-you))))

(Then #"^I don't see the error message$" []
  (check (not (taxi/displayed? error-message))))

(Then #"^I'm done$" []
  (browser-down :force true))