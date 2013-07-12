;; Behavioral driven unit tests for the contact model

(require  '[coming-soon.lib.check :refer (check)]
          '[coming-soon.models.contact :as contact]
          '[clj-time.core :refer (now before? after? ago secs)]
          '[clj-time.format :refer (parse)])

(def updated-at nil)

(defn id-for-contact [email]
  (:id (contact/contact-by-email email)))

(Given #"^I have no contacts$" []
  (contact/erase!))

(Then #"^the contact count is (\d+)$" [count-arg]
  (check (= (read-string count-arg) (contact/contact-count))))

(Then #"^the content store is sane$" []
  (check (contact/sane?)))

(When #"^I add a contact for \"([^\"]*)\"$" [email]
  (check (contact/create email nil))
  (def updated-at (parse (:updated-at (contact/contact-by-email email)))))

(Then #"^the contact \"([^\"]*)\" exists$" [email]
  (check (contact/exists-by-email? email)))

(Then #"^the contact \"([^\"]*)\" does not exist$" [email]
  (check (not (contact/exists-by-email? email))))

(Then #"^the contact can be retrieved by \"([^\"]*)\"$" [email]
  (check
    (when-let [contact (contact/contact-by-email email)]
      (= email (:email contact)))))

(When #"^I add a contact for \"([^\"]*)\" with a referrer from \"([^\"]*)\"$" [email referrer]
  (check (contact/create email referrer)))

(Then #"^the contact \"([^\"]*)\" has a referrer of \"([^\"]*)\"$" [email referrer]
  (check
    (when-let [contact (contact/contact-by-email email)]
      (= email (:email contact)) (= referrer (:referrer contact)))))

(Then #"^the contact \"([^\"]*)\" has no referrer$" [email]
  (check
    (when-let [contact (contact/contact-by-email email)]
      (nil? (:referrer contact)))))

(Then #"^the contact \"([^\"]*)\" and the contact \"([^\"]*)\" have unique ids$" [email1 email2]
  (check (not= (id-for-contact email1) (id-for-contact email2))))

(Then #"^the contact \"([^\"]*)\" has an id before the contact \"([^\"]*)\"$" [email1 email2]
  (check (< (id-for-contact email1) (id-for-contact email2))))

(When #"^I delay a moment$" []
  (Thread/sleep 1000))

(Then #"^the contact \"([^\"]*)\" has an updated-at of just before now$" [email]
  (check
    (when-let [contact (contact/contact-by-email email)]
      (when-let [time (parse (:updated-at contact))]
        (and (after? time (-> 10 secs ago)) (before? time (now)))))))

(Then #"^the contact \"([^\"]*)\" has an updated-at before the updated-at for contact \"([^\"]*)\"$" [email1 email2]
  (check
    (let [contact1 (contact/contact-by-email email1)
          contact2 (contact/contact-by-email email2)]
      (when (and contact1 contact2)
        (let [time1 (parse (:updated-at contact1))
              time2 (parse (:updated-at contact2))]
          (when (and time1 time2)
            (before? time1 time2)))))))

(Then #"^the contact \"([^\"]*)\" has a more recent updated-at than before$" [email]
  (check
    (when-let [contact (contact/contact-by-email email)]
      (when-let [time (parse (:updated-at contact))]
        (before? updated-at time)))))


(Given #"^the system knows about the following contacts$" [table]
  (let [users (table->rows table)]
    (doseq [user users] (contact/create (:email user) (:referrer user)))))

(When #"^I remove the contact for \"([^\"]*)\"$" [email]
  (contact/remove-by-email! email))

(When #"^I remove the contact with id \"([^\"]*)\"$" [id]
  (contact/remove-by-id! id))

(When #"^I erase all contacts$" []
  (contact/erase!))

(When #"^I retrieve the contact for \"([^\"]*)\" the \"([^\"]*)\" is \"([^\"]*)\"$" [email property value]
  (let [compare (if (= property "id") (read-string value) value)]
    (check (= compare ((contact/contact-by-email email) (keyword property))))))

(When #"^I retrieve the contact for \"([^\"]*)\" the \"([^\"]*)\" is blank$" [email property]
  (check (nil? ((contact/contact-by-email email) (keyword property)))))

(When #"^I retrieve the contact for id \"([^\"]*)\" the \"([^\"]*)\" is \"([^\"]*)\"$" [id property value]
  (check (= value ((contact/contact-by-id id) (keyword property)))))

(When #"^I retrieve the contact for id \"([^\"]*)\" the \"([^\"]*)\" is blank$" [id property]
  (check (nil? ((contact/contact-by-id id) (keyword property)))))

(When #"^I retrieve the contact for \"([^\"]*)\" it doesn't exist$" [email]
  (check (nil? (contact/contact-by-email email))))

(When #"^I retrieve the contact for id \"([^\"]*)\" it doesn't exist$" [id]
  (check (nil? (contact/contact-by-id id))))