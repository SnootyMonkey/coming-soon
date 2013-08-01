(require  '[coming-soon.lib.browser :refer (browser-up browser-down)]
          '[clj-webdriver.taxi :refer (to)])

(When #"^I go to the site$" []
  (browser-up)
  (to "http://localhost:3000/"))

(Then #"^I'm done$" []
  (browser-down :force true))
