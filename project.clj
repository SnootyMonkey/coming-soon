(defproject coming-soon "0.2.0-SNAPSHOT"
  :description "coming-soon is a simple Clojure/ClojureScript/Redis 'landing page' application that takes just a few minute to setup"
  :url "https://github.com/SnootyMonkey/coming-soon/"
  :license {:name "Mozilla Public License v2.0"
            :url "http://www.mozilla.org/MPL/2.0/"}

  :min-lein-version "2.3.4" ;; highest version supported by Travis-CI as of 1/14/2014

  :dependencies [
    [org.clojure/clojure "1.6.0"] ; Lisp on the JVM http://clojure.org/documentation
    [org.clojure/clojurescript "0.0-2202"] ; ClojureScript compiler https://github.com/clojure/clojurescript
    [ring/ring-jetty-adapter "1.3.0-beta2"] ; Web Server https://github.com/ring-clojure/ring
    [ring-basic-authentication "1.0.5"] ; Basic HTTP/S Auth https://github.com/remvee/ring-basic-authentication
    [compojure "1.1.8"] ; Web routing http://github.com/weavejester/compojure
    [com.taoensso/carmine "2.6.2"] ; Redis client https://github.com/ptaoussanis/carmine
    [environ "0.5.0"] ; Get environment settings from different sources https://github.com/weavejester/environ
    [clj-json "0.5.3"] ; JSON encoding https://github.com/mmcgrana/clj-json/
    [org.clojure/data.xml "0.0.7"] ; XML encoding https://github.com/clojure/data.xml
    [clojure-csv/clojure-csv "2.0.1"] ; CSV encoding https://github.com/davidsantiago/clojure-csv
    [enlive "1.1.5"] ; HTML templates https://github.com/cgrand/enlive
    [hiccup "1.0.5"] ; HTML generation https://github.com/weavejester/hiccup
    [jayq "2.5.0"] ; ClojureScript wrapper for jQuery https://github.com/ibdknox/jayq
    [tinter "0.1.1-SNAPSHOT"] ; color manipulation https://github.com/andypayne/tinter
    [clj-time "0.7.0"] ; DateTime utilities https://github.com/clj-time/clj-time
  ]

  :profiles {
    :dev {
      :dependencies [
        [print-foo "0.5.1"] ; Old school print debugging https://github.com/danielribeiro/print-foo
      ]
      :jvm-opts ["-Dphantomjs.binary.path=phantomjs"]
    }
    :qa {
      :dependencies [
        [expectations "2.0.7"] ; Unit testing https://github.com/jaycfields/expectations
        [ring-mock "0.1.5"] ; Test Ring requests https://github.com/weavejester/ring-mock
        ;;[org.seleniumhq.selenium/selenium-server "2.34.0"]
        [clj-webdriver/clj-webdriver "0.6.1"] ; Clojure API for Selenium-WebDriver https://github.com/semperos/clj-webdriver
        [com.github.detro.ghostdriver/phantomjsdriver "1.1.0"] ; PhantomJS as Selenium back-end https://github.com/detro/ghostdriver
      ]
      :env {
        :config-file "test/test-config.edn"
      }
      :cucumber-feature-paths ["test/coming_soon/features"]
    }
  }

  :aliases {
    "build" ["do" "clean," "deps"]
    "cucumber" ["with-profile" "qa" "cucumber"]
    "expectations" ["with-profile" "qa" "test"]
    "test-server" ["with-profile" "qa" "ring" "server-headless"]
    "test" ["with-profile" "qa" "test"]
    "test-all" ["with-profile" "qa" "do" "cucumber," "test"]
    "test!" ["do" "build,", "test-all"]
    "spell" ["spell" "-n"]
    "ancient" ["with-profile" "qa" "do" "ancient" ":allow-qualified," "ancient" ":plugins" ":allow-qualified"]
  }
  
  :plugins [
    [lein-ancient "0.5.5"] ; Check for outdated dependencies https://github.com/xsc/lein-ancient
    [lein-ring "0.8.10"] ; Common ring tasks https://github.com/weavejester/lein-ring
    [lein-environ "0.5.0"] ; Get environment settings from different sources https://github.com/weavejester/environ
    [lein-cljsbuild "1.0.3"] ; ClojureScript compiler https://github.com/emezeske/lein-cljsbuild
    [lein-cucumber "1.0.2"] ; cucumber-jvm (BDD testing) tasks https://github.com/nilswloka/lein-cucumber
    [lein-spell "0.1.0"] ; Catch spelling mistakes in docs and docstrings https://github.com/cldwalker/lein-spell
  ]

  ;; ----- ClojureScript -----

  :cljsbuild {
    :crossovers [coming-soon.models.email] ; compile for both Clojure and ClojureScript
    :builds
      [{
      :source-paths ["src/coming_soon/cljs" "src"] ; CLJS source code path
      ;; Google Closure (CLS) options configuration
      :compiler {
        :output-to "resources/public/js/coming_soon.js" ; generated JS script filename
        :optimizations :simple ; JS optimization directive
        :pretty-print false ; generated JS code prettyfication
      }}]
  }

  ;; ----- Web Application -----

  :ring {:handler coming-soon.app/app}
  :main coming-soon.app

)