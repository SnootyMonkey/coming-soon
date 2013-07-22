(defproject coming-soon "0.2.0-SNAPSHOT"
  :description "coming-soon is a simple Clojure/ClojureScript/Redis 'landing page' application that takes just a few minute to setup"
  :url "https://github.com/SnootyMonkey/coming-soon/"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.5.1"] ; Lisp on the JVM http://clojure.org/documentation
    [ring/ring-jetty-adapter "1.2.0"] ; Web Server https://github.com/ring-clojure/ring
    [ring-basic-authentication "1.0.2"] ; Basic HTTP/S Auth https://github.com/remvee/ring-basic-authentication
    [compojure "1.1.5"] ; Web routing http://github.com/weavejester/compojure
    [com.taoensso/carmine "1.12.0"] ; Redis client https://github.com/ptaoussanis/carmine
    [environ "0.4.0"] ; Get environment settings from different sources https://github.com/weavejester/environ
    [clj-json "0.5.3"] ; JSON encoding https://github.com/mmcgrana/clj-json/
    [org.clojure/data.xml "0.0.7"] ; XML encoding https://github.com/clojure/data.xml
    [clojure-csv/clojure-csv "2.0.1"] ; CSV encoding https://github.com/davidsantiago/clojure-csv
    [enlive "1.1.1"] ; HTML templates https://github.com/cgrand/enlive
    [hiccup "1.0.4"] ; HTML generation https://github.com/weavejester/hiccup
    [jayq "2.4.0"] ; ClojureScript wrapper for jQuery https://github.com/ibdknox/jayq
    [tinter "0.1.1-SNAPSHOT"] ; color manipulation https://github.com/andypayne/tinter
    [clj-time "0.5.1"] ; DateTime utilities https://github.com/clj-time/clj-time
  ]
  :profiles {
    :dev {
      :dependencies [
        [print-foo "0.3.7"] ; Old school print debugging https://github.com/danielribeiro/print-foo
      ]
    }
    :test [:dev {
      :env {
        :config-file "test/test-config.edn"
      }
      :cucumber-feature-paths ["test/coming_soon/features"]
      :dependencies [
        [expectations "1.4.49"] ; Unit testing https://github.com/jaycfields/expectations
        [ring-mock "0.1.5"] ; Test Ring requests https://github.com/weavejester/ring-mock
      ]}
    ]
  }
  :aliases {
    "cucumber" ["with-profile" "test" "cucumber"]
    "test" ["with-profile" "test" "do" "expectations," "cucumber"]
    "test!" ["with-profile" "test" "do" "clean," "deps," "expectations," "cucumber"]
  }
  :plugins [
    [lein-ancient "0.4.0"] ; Check for outdated dependencies https://github.com/xsc/lein-ancient
    [lein-ring "0.8.3"] ; Common ring tasks https://github.com/weavejester/lein-ring
    [lein-environ "0.4.0"] ; Get environment settings from different sources https://github.com/weavejester/environ
    [lein-cljsbuild "0.3.2"] ; ClojureScript compiler https://github.com/emezeske/lein-cljsbuild
    [lein-expectations "0.0.7"] ; Unit testing https://github.com/gar3thjon3s/lein-expectations
    [lein-cucumber "1.0.2"] ; cucumber-jvm (BDD testing) tasks https://github.com/nilswloka/lein-cucumber
    [lein-spell "0.1.0"] ; Catch spelling mistakes in docs and docstrings https://github.com/cldwalker/lein-spell
  ]
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
  :ring {:handler coming-soon.app/app}
  :min-lein-version "2.2"
  :main coming-soon.app)