;; Connect to the browser REPL:
;; lein ring server-headless
;; cd src/coming_soon/cljs
;; lein trampoline cljsbuild repl-listen
;; open browser at http://localhost:3000

(ns connect
  (:require [clojure.browser.repl :as repl]))

(repl/connect "http://localhost:9000/repl")