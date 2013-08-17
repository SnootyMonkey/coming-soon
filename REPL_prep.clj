;; productive set of development namespaces
(use 'clojure.stacktrace)
(use 'clj-time.format)
(use 'print.foo)
(require '[coming-soon.config :as config] :reload-all)
(require '[coming-soon.models.email :as email] :reload-all)
(require '[coming-soon.models.contact :as contact] :reload-all)
(require '[coming-soon.lib.browser :refer (browser-up browser-down)])
(require '[clj-webdriver.taxi :as taxi])

;; print last exception
(print-stack-trace *e)

;; test the app in a browser
(browser-up) ;; headless
(browser-up :chrome)
(browser-up :firefox)

(taxi/to "http://localhost:3000/")

(taxi/title)
(taxi/text "span#app-title")
(taxi/text "span#app-tagline")

(taxi/input-text "input#email" "")
(taxi/input-text "input#email" "foo@bar.com")

(taxi/click "#submit")

(taxi/displayed? "span#thank-you")
(taxi/displayed? "span#error-message")

(taxi/take-screenshot)

(browser-down :force true)