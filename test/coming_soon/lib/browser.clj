;; General technique courtesy of Sean Corfield http://corfield.org/blog/post.cfm/automated-browser-based-testing-with-clojure
(ns coming-soon.lib.browser
  (:require [clj-webdriver.taxi :refer (set-driver! quit)]
            [clj-webdriver.driver :refer (init-driver)]))

(import 'org.openqa.selenium.phantomjs.PhantomJSDriver)
(import 'org.openqa.selenium.remote.DesiredCapabilities)

(def ^:private browser-count (atom 0))

(defn- start-browser [browser]
  (when (= 1 (swap! browser-count inc))
    (set-driver! browser)))

(defn browser-up
  "Start up a browser if it's not already started."
  ([] 
    (let [capability (DesiredCapabilities.)]
      (. capability setCapability "phantomjs.binary.path" "/usr/local/phantomjs")
      (start-browser (init-driver {:webdriver (PhantomJSDriver. capability)}))))
  ([browser]
    (if (browser #{:firefox :chrome})
      (start-browser {:browser browser})
      :what-browser-is-that?)))

(defn browser-down
  "If this is the last request, shut the browser down."
  [& {:keys [force] :or {force false}}]
  (when (zero? (swap! browser-count (if force (constantly 0) dec)))
    (quit)))