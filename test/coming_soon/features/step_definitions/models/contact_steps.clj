;; Behavioral driven unit tests for the contact model

(use 'coming-soon.models.contact)
(require '[clj-time.core :refer (now before? after? ago secs)]
         '[clj-time.format :refer (parse)])

(def updated-at nil)

(defn id-for-contact [email]
  (:id (contact-by-email email)))

(Given #"^I have no contacts$" []
  (erase!))

(Then #"^the contact count is (\d+)$" [count-arg]
  (assert (and (= (read-string count-arg) (contact-count)) (sane?))))

(When #"^I add a contact for \"([^\"]*)\"$" [email]
  (assert (and (create email nil) (sane?)))
  (def updated-at (parse (:updated-at (contact-by-email email)))))

(Then #"^the contact \"([^\"]*)\" exists$" [email]
  (assert (and (exists-by-email? email) (sane?))))

(Then #"^the contact can be retrieved by \"([^\"]*)\"$" [email]
  (assert
    (when-let [contact (contact-by-email email)]
      (and (= email (:email contact)) (sane?)))))

(When #"^I add a contact for \"([^\"]*)\" with a referrer from \"([^\"]*)\"$" [email referrer]
  (assert (and (create email referrer) (sane?))))

(Then #"^the contact \"([^\"]*)\" has a referrer of \"([^\"]*)\"$" [email referrer]
  (assert
    (when-let [contact (contact-by-email email)]
      (and (= email (:email contact)) (= referrer (:referrer contact)) (sane?)))))

(Then #"^the contact \"([^\"]*)\" has no referrer$" [email]
  (assert
    (when-let [contact (contact-by-email email)]
      (and (nil? (:referrer contact)) (sane?)))))

(Then #"^the contact \"([^\"]*)\" and the contact \"([^\"]*)\" have unique ids$" [email1 email2]
  (assert (not= (id-for-contact email1) (id-for-contact email2))))

(Then #"^the contact \"([^\"]*)\" has an id before the contact \"([^\"]*)\"$" [email1 email2]
  (assert (< (id-for-contact email1) (id-for-contact email2))))

(When #"^I delay a moment$" []
  (Thread/sleep 1000))

(Then #"^the contact \"([^\"]*)\" has an updated-at of just before now$" [email]
  (assert
    (when-let [contact (contact-by-email email)]
      (when-let [time (parse (:updated-at contact))]
        (and (after? time (-> 10 secs ago)) (before? time (now)))))))

(Then #"^the contact \"([^\"]*)\" has an updated-at before the updated-at for contact \"([^\"]*)\"$" [email1 email2]
  (assert
    (let [contact1 (contact-by-email email1)
          contact2 (contact-by-email email2)]
      (when (and contact1 contact2)
        (let [time1 (parse (:updated-at contact1))
              time2 (parse (:updated-at contact2))]
          (when (and time1 time2)
            (before? time1 time2)))))))

(Then #"^the contact \"([^\"]*)\" has a more recent updated-at than before$" [email]
  (assert
    (when-let [contact (contact-by-email email)]
      (when-let [time (parse (:updated-at contact))]
        (before? updated-at time)))))