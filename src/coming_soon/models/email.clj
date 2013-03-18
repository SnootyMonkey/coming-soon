(ns coming-soon.models.email)

;; Regex for a valid email address
(def email-regex #"^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$")

(defn valid? [email]
  "Determine if the specified email address is valid according to our email regex."
  (if (and (not (nil? email)) (re-matches email-regex email))
  true 
  false))