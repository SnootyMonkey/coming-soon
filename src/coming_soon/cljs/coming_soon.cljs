(ns coming-soon
  (:require [goog.net.XhrIo :as xhr]
            [cljs.reader :as reader])
  (:use [jayq.core :only [$ document-ready bind val css]]
        [jayq.util :only [log]]
        [coming-soon.models.email :only [valid?]]))

(def subscribe-url "/subscribe")

;; jQuery DOM lookups
(def $instructions ($ :#instructions))
(def $thank-you ($ :#thank-you))
(def $email ($ :#email))
(def $submit ($ :#submit))
(def $subscribe ($ :#subscribe))
(def $referrer ($ :#referrer))
(def $spam-msg ($ :#spam-msg))

;; 
(defn update-for-success []
  (log "Rejoice!")
  ;; Hide the email submission elements
  (-> $email (css {:visibility "hidden"}))
  (-> $submit (css {:visibility "hidden"}))
  (-> $spam-msg (css {:visibility "hidden"}))
  ;; Hide the instructions
  (-> $instructions (css {:display "none"}))
  ;; Unhide the thank you message
  (-> $thank-you (css {:display "block"})))

;; Let the user know it's all gone pear-shaped
(defn update-for-failure []
  (log "Oh no! We've shat the bed."))

;; Treat any 2xx status code as successful
(defn success? [status]
  (if (and (>= status 200) (< status 300))
    true
    false))

;; receive the server response to the email submission
(defn receive-result [event]
  (let [status (.getStatus (.-target event))]
    (log "status " status)
    (log "received " (reader/read-string (.getResponseText (.-target event))))
    (if (success? status)
      (update-for-success)
      (update-for-failure))))

;; submit the provided email to the server with AJAX
(defn submit [email]
  (log "submitting " email)
  (xhr/send subscribe-url receive-result "POST" (str "email=" email "&referrer=" (val $referrer))))

;; validate the provided email address
(defn validate-email []
  (let [email (val $email)]
    (log "in validate-email, email is: " email)
    ; IE doesn't support the HTML 5 email text input validation, so it could be blank
    (if (< (count email) 1)
      (js/alert "Please provide your email address.")
      ; IE doesn't support the HTML 5 email text input validation, so it could be invalid
      (if (valid? email)
        (submit email)
        (js/alert "Please provide a valid email address."))))
  false) ; prevent the form from submitting on its own

;; define the function to attach validate-email to the submit event of the form
(defn init []
  (log "ClojureScript is working... that's good.")
  (bind $subscribe :submit validate-email))

;; initialize once the HTML page has loaded
(document-ready init)