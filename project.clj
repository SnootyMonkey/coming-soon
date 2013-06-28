(defproject coming-soon "0.2.0-SNAPSHOT"
  :description "coming-soon is a simple Clojure/ClojureScript/Redis 'landing page' application that takes just a few minute to setup"
  :url "https://github.com/SnootyMonkey/coming-soon/"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.5.0"] ; Lisp on the JVM http://clojure.org/documentation
    [ring/ring-jetty-adapter "1.1.8"] ; Web Server https://github.com/ring-clojure/ring
    [ring-basic-authentication "1.0.2"] ; Basic HTTP/S Auth https://github.com/remvee/ring-basic-authentication
    [compojure "1.1.5"] ; Web routing http://github.com/weavejester/compojure
    [com.taoensso/carmine "1.6.0"] ; Redis client https://github.com/ptaoussanis/carmine
    [clj-json "0.5.3"] ; JSON encoding https://github.com/mmcgrana/clj-json/
    [org.clojure/data.xml "0.0.7"] ; XML encoding https://github.com/clojure/data.xml
    [clojure-csv/clojure-csv "2.0.0-alpha2"] ; CSV encoding https://github.com/davidsantiago/clojure-csv
    [enlive "1.1.1"] ; HTML templates https://github.com/cgrand/enlive
    [hiccup "1.0.3"] ; HTML generation https://github.com/weavejester/hiccup
    [jayq "2.3.0"] ; ClojureScript wrapper for jQuery https://github.com/ibdknox/jayq
    [tinter "0.1.1-SNAPSHOT"] ; color manipulation https://github.com/andypayne/tinter
    [clj-time "0.5.0"] ; DateTime utilities https://github.com/clj-time/clj-time
    [expectations "1.4.49"] ; Unit testing https://github.com/jaycfields/expectations
  ]
  :plugins [
    [lein-ring "0.8.3"] ; Common ring tasks https://github.com/weavejester/lein-ring
    [lein-cljsbuild "0.3.2"] ; ClojureScript compiler https://github.com/emezeske/lein-cljsbuild
    [lein-expectations "0.0.7"] ; Unit testing https://github.com/gar3thjon3s/lein-expectations
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
  :min-lein-version "2.0.0"
  :main coming-soon.app)