;; Behavioral driven unit tests for the contact model
(require '[coming-soon.lib.check :refer (check)]
         '[coming-soon.models.contact :as contact]
         '[clj-time.core :refer (now before? after? ago secs)]
         '[clj-time.format :refer (parse)])

(def updated-at (atom nil))

(def result-list (atom nil))

(defn id-for-contact [email]
  (:id (contact/contact-by-email email)))

(defn next-result []
  (let [next (first @result-list)]
    (swap! result-list rest)
    next))

(Given #"^there are no contacts$" []
  (contact/erase!))

(Then #"^(now the|the) contact count is (\d+)$" [_ contact-count]
  (check (= (read-string contact-count) (contact/contact-count))))

(Then #"^the content store is sane$" []
  (check (contact/sane?)))

(When #"^I add a contact for \"([^\"]*)\"$" [email]
  (check (contact/create email nil))
  (reset! updated-at (parse (:updated-at (contact/contact-by-email email)))))

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
        (before? @updated-at time)))))


(Given #"^the system knows about the following contacts:$" [table]
  (contact/erase!)
  (let [users (table->rows table)]
    (doseq [user users] (contact/create (:email user) (:referrer user)))))

(When #"^I remove the contact for \"([^\"]*)\"$" [email]
  (contact/remove-by-email! email))

(When #"^I remove the contact with id (\d+)$" [id]
  (contact/remove-by-id! id))

(When #"^I erase all contacts$" []
  (contact/erase!))

(When #"^I retrieve the contact for \"([^\"]*)\" the \"([^\"]*)\" is \"([^\"]*)\"$" [email property value]
  (let [compare (if (= property "id") (read-string value) value)]
    (check (= compare ((contact/contact-by-email email) (keyword property))))))

(When #"^I retrieve the contact for \"([^\"]*)\" the \"([^\"]*)\" is blank$" [email property]
  (check (nil? ((contact/contact-by-email email) (keyword property)))))

(When #"^I retrieve the contact for id (\d+) the \"([^\"]*)\" is \"([^\"]*)\"$" [id property value]
  (check (= value ((contact/contact-by-id id) (keyword property)))))

(When #"^I retrieve the contact for id (\d+) the \"([^\"]*)\" is blank$" [id property]
  (check (nil? ((contact/contact-by-id id) (keyword property)))))

(When #"^I retrieve the contact for \"([^\"]*)\" it doesn't exist$" [email]
  (check (nil? (contact/contact-by-email email))))

(When #"^I retrieve the contact for id (\d+) it doesn't exist$" [id]
  (check (nil? (contact/contact-by-id id))))

(When #"^I list all contacts$" []
  (reset! result-list (contact/all-contacts)))

(When #"^I list all emails$" []
  (reset! result-list (contact/all-emails)))

(When #"^I list all referrals$" []
  (reset! result-list (contact/all-referrers)))

(Then #"^the list contains (\d+) items$" [item-count]
  (check (= (read-string item-count) (count @result-list))))

(Then #"^the next contact is \"([^\"]*)\" with the id (\d+) and the referrer \"([^\"]*)\"$" [email id referrer]
  (let [contact (next-result)]
    (check (= email (:email contact)))
    (check (= (read-string id) (:id contact)))
    (check (= referrer (:referrer contact)))))

(Then #"^the next contact is \"([^\"]*)\" with the id (\d+) and no referrer$" [email id]
  (let [contact (next-result)]
    (check (= email (:email contact)))
    (check (= (read-string id) (:id contact)))
    (check (nil? (:referrer contact)))))

(Then #"^the next email is \"([^\"]*)\"$" [expected-email]
  (let [actual-email (next-result)]
    (check (= expected-email actual-email))))

(Then #"^the next referrer is \"([^\"]*)\" with a count of (\d+)$" [url referral-count]
  (let [referrer (next-result)]
    (check (= url (first referrer)))
    (check (= (read-string referral-count) (last referrer)))))


