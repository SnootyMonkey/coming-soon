(ns coming-soon.models.contact
  (require [taoensso.carmine :as car]))

;; Regex for a valid email address
(def email-regex #"^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$")

;; A Redis connection with Carmine
(def redis-pool (car/make-conn-pool))
(def redis-server-spec (car/make-conn-spec))
(defmacro with-car [& body] `(car/with-conn redis-pool redis-server-spec ~@body))

(defn valid? [email]
  "Determine if the specified email address is valid according to our email regex."
  (if (and (not (nil? email)) (re-matches email-regex email))
  true 
  false))

(defn exists? [email]
  "Determine if the specified email address has been collected."
  (if (= 1 (with-car (car/hexists :coming-soon-contacts email)))
    true
    false))

(defn create [email referrer]
  "Collect the contact and its referring URL."
  ;; hash the referrer by the contact's email address
  (if (= "OK" (with-car (car/hmset :coming-soon-contacts email referrer)))
    true
    false))

(defn referrer [email]
  "Return the referrer for an email, nil if the email has not been collected."
  (with-car (car/hget :coming-soon-contacts email)))

(defn all []
  "Return a vector of all the emails that have been collected so far."
  (with-car (car/hkeys :coming-soon-contacts)))

(defn contact-count []
  "Return the number of contacts collected."
  (with-car (car/hlen :coming-soon-contacts)))


(defn remove! [email]
  "Remove the contact."
  (if (>= (with-car (car/hdel :coming-soon-contacts email)) 1)
    true
    false))

(defn empty! []
  "USE WITH CAUTION - Wipe out all the stored contacts."
  (if (>= (with-car (car/del :coming-soon-contacts)) 1)
    true
    false))