(ns coming-soon
  (:require [goog.net.XhrIo :as xhr]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ document-ready bind val]]
        [jayq.util :only [log]]
        [coming-soon.models.email :only [valid?]]))

(def subscribe-url "/subscribe")

;; jQuery DOM lookups
(def email ($ :#email))
(def subscribe ($ :#subscribe))
(def referrer ($ :#referrer))

;; receive the server response to the email submission
(defn receive-result [event]
  (log "status " (.getStatus (.-target event)))
  (log "received " (reader/read-string (.getResponseText (.-target event)))))

;; submit the provided email to the server with AJAX
(defn submit [email]
  (log "submitting " email)
  (xhr/send subscribe-url receive-result "POST" (str "email=" email "&referrer=" (val referrer))))

;; validate the provided email address
(defn validate-email []
  (log "in validate-email, email is: " (val email))
  ; IE doesn't support the HTML 5 email text input validation, so it could be blank
  (if (< (count (val email)) 1)
    (js/alert "Please provide your email address.")
    ; IE doesn't support the HTML 5 email text input validation, so it could be invalid
    (if (valid? (val email))
      (submit (val email))
      (js/alert "Please provide a valid email address.")))
  false) ; prevent the form from submitting on its own

;; define the function to attach validate-email to the submit event of the form
(defn init []
  (log "ClojureScript is working")
  (bind subscribe :submit validate-email))

;; initialize once the HTML page has loaded
(document-ready init)