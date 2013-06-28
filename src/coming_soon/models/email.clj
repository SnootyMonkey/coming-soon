(ns coming-soon.models.email)

;; Regex for a valid email address
(def email-regex #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")

(defn valid?
  "Determine if the specified email address is valid according to our email regex."
  [email]
  (if (and (not (nil? email)) (re-matches email-regex email))
  true 
  false))