;; Behavioral driven unit tests for the contact model

(require  '[coming-soon.models.contact :as contact]
          '[clj-time.core :refer (now before? after? ago secs)]
          '[clj-time.format :refer (parse)])

(def updated-at nil)

(defn id-for-contact [email]
  (:id (contact/contact-by-email email)))

(Given #"^I have no contacts$" []
  (contact/erase!))

(Then #"^the contact count is (\d+)$" [count-arg]
  (assert (= (read-string count-arg) (contact/contact-count))))

(Then #"^the content store is sane$" []
  (assert (contact/sane?)))

(When #"^I add a contact for \"([^\"]*)\"$" [email]
  (assert (contact/create email nil))
  (def updated-at (parse (:updated-at (contact/contact-by-email email)))))

(Then #"^the contact \"([^\"]*)\" exists$" [email]
  (assert (contact/exists-by-email? email)))

(Then #"^the contact \"([^\"]*)\" does not exist$" [email]
  (assert (not (contact/exists-by-email? email))))

(Then #"^the contact can be retrieved by \"([^\"]*)\"$" [email]
  (assert
    (when-let [contact (contact/contact-by-email email)]
      (= email (:email contact)))))

(When #"^I add a contact for \"([^\"]*)\" with a referrer from \"([^\"]*)\"$" [email referrer]
  (assert (contact/create email referrer)))

(Then #"^the contact \"([^\"]*)\" has a referrer of \"([^\"]*)\"$" [email referrer]
  (assert
    (when-let [contact (contact/contact-by-email email)]
      (= email (:email contact)) (= referrer (:referrer contact)))))

(Then #"^the contact \"([^\"]*)\" has no referrer$" [email]
  (assert
    (when-let [contact (contact/contact-by-email email)]
      (nil? (:referrer contact)))))

(Then #"^the contact \"([^\"]*)\" and the contact \"([^\"]*)\" have unique ids$" [email1 email2]
  (assert (not= (id-for-contact email1) (id-for-contact email2))))

(Then #"^the contact \"([^\"]*)\" has an id before the contact \"([^\"]*)\"$" [email1 email2]
  (assert (< (id-for-contact email1) (id-for-contact email2))))

(When #"^I delay a moment$" []
  (Thread/sleep 1000))

(Then #"^the contact \"([^\"]*)\" has an updated-at of just before now$" [email]
  (assert
    (when-let [contact (contact/contact-by-email email)]
      (when-let [time (parse (:updated-at contact))]
        (and (after? time (-> 10 secs ago)) (before? time (now)))))))

(Then #"^the contact \"([^\"]*)\" has an updated-at before the updated-at for contact \"([^\"]*)\"$" [email1 email2]
  (assert
    (let [contact1 (contact/contact-by-email email1)
          contact2 (contact/contact-by-email email2)]
      (when (and contact1 contact2)
        (let [time1 (parse (:updated-at contact1))
              time2 (parse (:updated-at contact2))]
          (when (and time1 time2)
            (before? time1 time2)))))))

(Then #"^the contact \"([^\"]*)\" has a more recent updated-at than before$" [email]
  (assert
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

(When #"^I list all contacts$" []
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Then #"^the list contains (\d+) items$" [arg1]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Then #"^the next contact is \"([^\"]*)\" with the id \"([^\"]*)\" and the referrer \"([^\"]*)\"$" [arg1 arg2 arg3]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Then #"^the next contact is \"([^\"]*)\" with the id \"([^\"]*)\" and no referrer$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I list all emails$" []
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I list all referrals$" []
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Then #"^the next referrer is \"([^\"]*)\" with a count of \"([^\"]*)\"$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I retrieve the contact for \"([^\"]*)\" the id is \"([^\"]*)\"$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I retrieve the contact for \"([^\"]*)\" the referrer is \"([^\"]*)\"$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I retrieve the contact for \"([^\"]*)\" it doesn't exist$" [arg1]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I retrieve the contact for id \"([^\"]*)\" the email is \"([^\"]*)\"$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I retrieve the contact for id \"([^\"]*)\" the referrer is \"([^\"]*)\"$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I retrieve the contact for id \"([^\"]*)\" it doesn't exist$" [arg1]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))