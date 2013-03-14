(ns coming-soon.models.contact)

(def email-regex #"^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$")

(defn valid? [email]
  (if (and (not (nil? email)) (re-matches email-regex email))
  true 
  false))

(defn exists? [email]
  true)

(defn all []
  [])

(defn create [email]
  true)