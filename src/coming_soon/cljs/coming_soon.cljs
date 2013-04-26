(ns coming-soon
  (:require [goog.net.XhrIo :as xhr]
            [cljs.reader :as reader]
            [jayq.core :refer [$ document-ready bind val css attr remove-attr add-class remove-class]]
            [jayq.util :refer [log]]
            [coming-soon.models.email :refer [valid?]]))

(def subscribe-url "/subscribe")

;; jQuery DOM lookups
(def $backstretch ($ :.backstretch))
(def $instructions ($ :#instructions))
(def $thank-you ($ :#thank-you))
(def $email ($ :#email))
(def $submit ($ :#submit))
(def $submit-icon ($ :#submit-icon))
(def $subscribe ($ :#subscribe))
(def $referrer ($ :#referrer))
(def $spam-msg ($ :#spam-msg))

(defn disable-submission []
  ;; disable the submit button
  (-> $submit (attr "disabled" "true"))
  ;; spin the submit icon if there is one
  (if-not (nil? (val $submit-icon))
    (-> $submit-icon (add-class "icon-spin"))))

(defn enable-submission []
  (-> $submit (remove-attr "disabled"))
  ;; stop spinning the submit icon if there is one
  (if-not (nil? (val $submit-icon))
    (-> $submit-icon (remove-class "icon-spin"))))

(defn update-for-success
  "remove the submission gadgetry and show the thank you message"
  []
  ;; hide the email submission elements
  (-> $email (css {:visibility "hidden"}))
  (-> $submit (css {:visibility "hidden"}))
  (-> $spam-msg (css {:visibility "hidden"}))
  ;; Hide the instructions and show the thank you
  (.hide $instructions)
  (.show $thank-you))

(defn update-for-failure
  "let the user know it's all gone pear-shaped"
  []
  (log "Oh no! We've shat the bed.")
  (enable-submission))

(defn success?
  "treat any 2xx status code as successful"
  [status]
  (if (and (>= status 200) (< status 300))
    true
    false))

(defn receive-result
  "receive the server response to the email submission"
  [event]
  (let [status (.getStatus (.-target event))]
    (log "status " status)
    (log "received " (reader/read-string (.getResponseText (.-target event))))
    (if (success? status)
      (update-for-success)
      (update-for-failure))))

(defn submit
  "submit the provided email to the coming-soon server with AJAX"
  [email]
  (log "submitting " email)
  (xhr/send subscribe-url receive-result "POST" (str "email=" email "&referrer=" (val $referrer))))

(defn validate-email-and-submit
  "validate the provided email address and submit it if it's valid"
  []
  (disable-submission)
  (let [email (val $email)]
    (log "in validate-email, email is: " email)
    ; IE doesn't support the HTML 5 email text input validation, so it could be blank
    (if (< (count email) 1)
      (do 
        (enable-submission)
        (js/alert "Please provide your email address."))
      ; IE doesn't support the HTML 5 email text input validation, so it could be invalid
      (if (valid? email)
        (submit email)
        (do
          (enable-submission)
          (js/alert "Please provide a valid email address.")))))
  false) ; prevent the form from submitting on its own

(defn init
  "define the function to attach validate-email to the submit event of the form, focus on the email field"
  []
  (log "ClojureScript is working... that's good.")
  (bind $subscribe :submit validate-email-and-submit)
  (.focus $email))

;; initialize once the HTML page has loaded
(document-ready init)