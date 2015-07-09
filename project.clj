(defproject coming-soon "0.3.0-SNAPSHOT"
  :description "coming-soon is a simple Clojure/ClojureScript/Redis 'landing page' application that takes just a few minute to setup"
  :url "https://github.com/SnootyMonkey/coming-soon/"
  :license {:name "Mozilla Public License v2.0"
            :url "http://www.mozilla.org/MPL/2.0/"}

  :min-lein-version "2.5.1" ; highest version supported by Travis-CI as of 5/4/2015

  :dependencies [
    [org.clojure/clojure "1.7.0"] ; Lisp on the JVM http://clojure.org/documentation
    [org.clojure/clojurescript "0.0-3308"] ; ClojureScript compiler https://github.com/clojure/clojurescript
    [ring/ring-jetty-adapter "1.4.0-RC1"] ; Web Server https://github.com/ring-clojure/ring
    [ring-basic-authentication "1.0.5"] ; Basic HTTP/S Auth https://github.com/remvee/ring-basic-authentication
    [compojure "1.3.4"] ; Web routing http://github.com/weavejester/compojure
    [com.taoensso/carmine "2.11.1"] ; Redis client https://github.com/ptaoussanis/carmine
    [environ "1.0.0"] ; Get environment settings from different sources https://github.com/weavejester/environ
    [clj-json "0.5.3"] ; JSON encoding https://github.com/mmcgrana/clj-json/
    [org.clojure/data.xml "0.0.8"] ; XML encoding https://github.com/clojure/data.xml
    [clojure-csv/clojure-csv "2.0.1"] ; CSV encoding https://github.com/davidsantiago/clojure-csv
    [enlive "1.1.5"] ; HTML templates https://github.com/cgrand/enlive
    [hiccup "1.0.5"] ; HTML generation https://github.com/weavejester/hiccup
    [jayq "2.5.4"] ; ClojureScript wrapper for jQuery https://github.com/ibdknox/jayq
    [tinter "0.1.1-SNAPSHOT"] ; color manipulation https://github.com/andypayne/tinter
    [clj-time "0.10.0"] ; DateTime utilities https://github.com/clj-time/clj-time
  ]

  :profiles {
    :qa {
      :dependencies [
        [midje "1.7.0"] ; Example-based testing https://github.com/marick/Midje
        [ring-mock "0.1.5"] ; Test Ring requests https://github.com/weavejester/ring-mock
      ]
      :plugins [
        [lein-midje "3.1.3"] ; Example-based testing https://github.com/marick/lein-midje
        [lein-cucumber "1.0.2"] ; cucumber-jvm (BDD testing) tasks https://github.com/nilswloka/lein-cucumber
        [jonase/eastwood "0.2.1"] ; Clojure linter https://github.com/jonase/eastwood        
      ]
      :env {
        :config-file "test/test-config.edn"
      }
      :cucumber-feature-paths ["test/coming_soon/features"]
    }
    :dev [:qa {
      :dependencies [
        [print-foo "1.0.2"] ; Old school print debugging https://github.com/danielribeiro/print-foo
      ]
      :plugins [
        [lein-ancient "0.6.7"] ; Check for outdated dependencies https://github.com/xsc/lein-ancient
        [lein-cljsbuild "1.0.6"] ; ClojureScript compiler https://github.com/emezeske/lein-cljsbuild
        [lein-spell "0.1.0"] ; Catch spelling mistakes in docs and docstrings https://github.com/cldwalker/lein-spell
        [lein-bikeshed "0.2.0"] ; Check for code smells https://github.com/dakrone/lein-bikeshed
        [lein-kibit "0.1.2"] ; Static code search for non-idiomatic code https://github.com/jonase/kibit
        [lein-checkall "0.1.1"] ; Runs bikeshed, kibit and eastwood https://github.com/itang/lein-checkall
        [lein-cljfmt "0.1.10"] ; Code formatting https://github.com/weavejester/cljfmt
        [lein-deps-tree "0.1.2"] ; Print a tree of project dependencies https://github.com/the-kenny/lein-deps-tree
      ]
      :env {
        :config-file "config.edn"
      }
      :cljfmt {
        :file-pattern #"\/src\/.+\.clj[csx]?$"
      }
    }]
  }

  :aliases {
    "build" ["do" "clean," "deps"]
    "cucumber" ["with-profile" "qa" "cucumber"]
    "midje" ["with-profile" "qa" "midje"]
    "test-all" ["with-profile" "qa" "do" "cucumber," "midje"]
    "test!" ["do" "build,", "test-all"]
    "test-server" ["with-profile" "qa" "ring" "server-headless"]
    "run" ["with-profile" "dev" "ring" "server-headless"]
    "run!" ["ring" "server-headless"]
    "spell" ["spell" "-n"]
    "ancient" ["with-profile" "dev" "do" "ancient" ":allow-qualified," "ancient" ":plugins" ":allow-qualified"]
  }
  
  :plugins [
    [lein-ring "0.9.6"] ; Common ring tasks https://github.com/weavejester/lein-ring
    [lein-environ "1.0.0"] ; Get environment settings from different sources https://github.com/weavejester/environ
  ]

  ;; ----- ClojureScript -----

  :cljsbuild {
    ; crossovers is deprecated, need to replace with cljx https://github.com/lynaghk/cljx
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