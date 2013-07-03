;; Behavioral driven unit tests for the contact model

(use 'coming-soon.models.contact)

(defn id-for-contact [email]
  (:id (contact-by-email email)))

(Given #"^I have no contacts$" []
  (erase!))

(Then #"^the contact count is (\d+)$" [count-arg]
  (and (= count-arg (contact-count)) (sane?)))

(When #"^I add a contact for \"([^\"]*)\"$" [email]
  (and (create email nil) (sane?)))

(Then #"^the contact \"([^\"]*)\" exists$" [email]
  (and (exists-by-email? email) (sane?)))

(Then #"^the contact can be retrieved by \"([^\"]*)\"$" [email]
  (if-let [contact (contact-by-email email)]
    (and (= email (:email contact)) (sane?))
    false))

(When #"^I add a contact for \"([^\"]*)\" with a referral from \"([^\"]*)\"$" [email referral]
  (and (create email referral) (sane?)))

(Then #"^the contact \"([^\"]*)\" has a referral of \"([^\"]*)\"$" [email referral]
  (if-let [contact (contact-by-email email)]
    (and (= email (:email contact)) (= referral (:referral contact)) (sane?))
    false))

(Then #"^the contact \"([^\"]*)\" has no referral$" [email]
  (if-let [contact (contact-by-email email)]
    (and (nil? (:referral contact)) (sane?))
    false))

(Then #"^the contact \"([^\"]*)\" and the contact \"([^\"]*)\" have unique ids$" [email1 email2]
  (not= (id-for-contact email1) (id-for-contact email2)))

(Then #"^the contact \"([^\"]*)\" has an id before the contact \"([^\"]*)\"$" [email1 email2]
  (< (id-for-contact email1) (id-for-contact email2)))