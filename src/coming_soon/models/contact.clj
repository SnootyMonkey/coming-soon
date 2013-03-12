(ns coming-soon.models.contact)

(def email-regex #"^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$")

(defn valid? [email]
  (if (re-matches email-regex email)
  true 
  false))