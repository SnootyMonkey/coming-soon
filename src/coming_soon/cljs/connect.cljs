;; To connect to the browser REPL:
;;
;; Comment in the last line (repl/connect) in this file
;;
;; In one terminal run: 
;;  lein ring server-headless
;;
;; In a second terminal, run:
;;  cd src/coming_soon/cljs
;;  lein trampoline cljsbuild repl-listen
;;  DON'T type anything yet into the REPL, it's not connected
;;
;; Open a browser to http://localhost:3000 (possibly refresh a few times)
;;
;; Pause for a moment, then in the 2nd terminal, test if your connected with:
;; (+ 1 1)
;;
;; If you get 2, you're all set!
;;
;; To do anything useful, the next thing you probably want to do is (in-ns 'coming-soon) to switch to coming-soon's client-side namespace.

(ns connect
  (:require [clojure.browser.repl :as repl]))

;; Comment in to connect to the browser REPL
(repl/connect "http://localhost:9000/repl")