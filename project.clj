(defproject coming-soon "0.0.1-SNAPSHOT"
  :description "coming-soon is a simple Clojure/ClojureScript 'landing page' application that takes just a few minute to setup"
  :url "https://github.com/SnootyMonkey/coming-soon/"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.5.0"] ; Lisp on the JVM http://clojure.org/documentation
    [compojure "1.1.5"] ; Web routing http://github.com/weavejester/compojure
    [postgresql "9.1-901.jdbc4"] ; SQL DB access
    [lobos "1.0.0-beta1"] ; database migrations http://budu.github.com/lobos/
    [enlive "1.0.1"] ; HTML templates
    [jayq "2.3.0"] ; ClojureScript wrapper for jQuery
  ]
  :plugins [
    [lein-ring "0.8.3"] ; common ring tasks https://github.com/weavejester/lein-ring
    [lein-lobos "1.0.0-beta1"] ; database migration tasks https://github.com/harob/lein-lobos/
    [lein-cljsbuild "0.3.0"] ; ClojureScript compiler https://github.com/emezeske/lein-cljsbuild
  ]
  :ring {:handler coming-soon.app/app}
  :cljsbuild {
    :crossovers [coming-soon.models.contact] ; compile for both Clojure and ClojureScript
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
)