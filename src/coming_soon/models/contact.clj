(ns coming-soon.models.contact
  (:require [taoensso.carmine :as car]
            [coming-soon.lib.redis :refer (prefix with-car)]
            [clj-time.format :refer (formatters unparse)]
            [clj-time.core :refer (now)]))

;; Redis "schema"
;; <prefix>:coming-soon-id - a simple incrementing counter
(def coming-soon-id (str prefix ":coming-soon-id")) 
;; <prefix>:coming-soon-contacts - hash of all contacts by id
(def coming-soon-contacts (str prefix ":coming-soon-contacts"))
;; <prefix>:coming-soon-emails - hash of all ids, hashed by email (a secondary index)
(def coming-soon-emails (str prefix ":coming-soon-emails"))

(def timestamp-format (formatters :date-time-no-ms))

(defn exists-by-email?
  "Determine if a contact with the specified email address has been collected."
  [email]
  (if (= 1 (with-car (car/hexists coming-soon-emails email)))
    true
    false))

(defn exists-by-id?
  "Determine if a contact with the specified id has been collected."
  [id]
  (if (= 1 (with-car (car/hexists coming-soon-contacts id)))
    true
    false))

(defn contact-by-id
  "Return the contact for an id, nil if no contact exists by that id."
  [id]
  (if (exists-by-id? id)
    (read-string (with-car (car/hget coming-soon-contacts id)))
    nil))
  
(defn contact-by-email
  "Return the contact for an email, nil if the email has not been collected."
  [email]
  (if (exists-by-email? email)
    (let [id (with-car (car/hget coming-soon-emails email))]
      (contact-by-id id))
    nil))

(defn contact-count
  "Return the number of contacts collected."
  []
  (with-car (car/hlen coming-soon-contacts)))

(defn all-contacts
  "Return all the contacts that have been collected so far in date of collection order."
  []
  (sort-by :updated-at (map read-string (with-car (car/hvals coming-soon-contacts)))))

(defn all-emails
  "Return all the emails that have been collected so far in date of collection order."
  []
  (map :email (all-contacts)))

(defn all-referrers
  "Return all the referral URLs and the # of referrals by that URL, in order of most referrals."
  []
  (sort-by last > (map identity (frequencies (map :referrer (all-contacts))))))

;; ISO 8601 timestamp
(defn- current-timestamp [] 
  (unparse timestamp-format (now)))

;; Store the contact by email and by id
(defn- store [id email referrer]
  (with-car
    ;; start a transaction
    (car/multi)
    ;; hash the id by the email
    (car/hset coming-soon-emails email id)
    ;; hash the contact by the id
    (car/hset coming-soon-contacts id (pr-str
      {:id id 
      :email email
      :referrer referrer
      :updated-at (current-timestamp)}))
    ;; commit the transaction
    (car/exec)))

(defn create
  "Store the contact"
  [email referrer]
  (if (exists-by-email? email)
    ;; use the id we already have for this email to store the contact
    (store (:id (contact-by-email email)) email referrer)
    ;; increment the counter to provide a new id to store the contact
    (let [id (with-car (car/incr coming-soon-id))]
      (store id email referrer)))
  true)

;; Remove the contact from the hash by id and the hash by email
(defn- remove! [id email]
  (with-car
    ;; start a transaction
    (car/multi)
    ;; remove the contact
    (car/hdel coming-soon-contacts id)
    (car/hdel coming-soon-emails email)
    ;; commit the transaction
    (car/exec)))

(defn remove-by-email!
  "Remove the contact."
  [email]
  (if (exists-by-email? email)
    (do 
      (let [id (with-car (car/hget coming-soon-emails email))]
        (remove! id email))
      true)
    false))

(defn remove-by-id!
  "Remove the contact."
  [id]
  (if (exists-by-id? id)
    (do 
      (let [email (:email (contact-by-id id))]
        (remove! id email))
      true)
    false))

(defn empty!
  "USE WITH CAUTION - Wipes out all the stored contacts."
  []
  (with-car
    ;; start a transaction
    (car/multi)
    ;; remove the hashs and counter
    (car/del coming-soon-id)
    (car/del coming-soon-contacts)
    (car/del coming-soon-emails)
    ;; commit the transaction
    (car/exec))
  true)

(defn sane?
  "Is the contact store in a sane state?"
  []
  (= (with-car (car/hlen coming-soon-contacts)) (with-car (car/hlen coming-soon-emails))))