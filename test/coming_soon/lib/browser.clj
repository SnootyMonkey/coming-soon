;; General technique courtesy of Sean Corfield http://corfield.org/blog/post.cfm/automated-browser-based-testing-with-clojure
(ns coming-soon.lib.browser
  (:require [clj-webdriver.taxi :refer (set-driver! quit)]
            [clj-webdriver.driver :refer (init-driver)]))

(import 'org.openqa.selenium.phantomjs.PhantomJSDriver)
(import 'org.openqa.selenium.remote.DesiredCapabilities)

(def ^:private browser-count (atom 0))

(defn browser-up
  "Start up a browser if it's not already started."
  []
  (when (= 1 (swap! browser-count inc))
    (set-driver! (init-driver {:webdriver (PhantomJSDriver. (DesiredCapabilities.))}))))
    ;;(set-driver! {:browser :firefox})))
    ;;(set-driver! {:browser :chrome})))

(defn browser-down
  "If this is the last request, shut the browser down."
  [& {:keys [force] :or {force false}}]
  (when (zero? (swap! browser-count (if force (constantly 0) dec)))
    (quit)))