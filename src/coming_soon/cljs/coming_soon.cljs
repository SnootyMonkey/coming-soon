(ns coming-soon
  (:use [jayq.core :only [$ document-ready bind val]]
        [jayq.util :only [log]]
        [coming-soon.models.contact :only [valid?]]))

(def email ($ :#email))
(def subscribe ($ :#subscribe))

;; validate the provided email address with a regex
(defn validate-form []
  (log "in validate-form, email is: " (val email))
  (if (< (count (val email)) 1)
    ; IE doesn't support the HTML 5 email text input validation, so it could be blank
    (do (js/alert "Please provide your email address.")
      false)
    (if (valid? (val email))
      true
      (do (js/alert "Please provide a valid email address.")
      false))))

; ;; define the function to attach validate-form to onsubmit event of
; ;; the subscribe form
(defn init []
  (log "ClojureScript is working")
  (bind subscribe :submit validate-form))

;; initialize once the HTML page has loaded
(document-ready init)