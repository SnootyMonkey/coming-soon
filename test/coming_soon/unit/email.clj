(ns coming-soon.unit.email
  (:require [midje.sweet :refer :all]
            [coming-soon.models.email :refer (valid?)]))

; Source of email addresses: http://codefool.tumblr.com/post/15288874550/list-of-valid-and-invalid-email-addresses
; Note: not all the unusual ones were used

(tabular (fact "valid email addresses are determined to be valid"
  (valid? ?address) => true)
  ?address
  "email@example.com"
  "firstname.lastname@example.com"
  "email@subdomain.example.com"
  "firstname+lastname@example.com"
  "1234567890@example.com"
  "email@example-one.com"
  "_______@example.com"
  "email@example.name"
  "email@example.museum"
  "email@example.co.jp"
  "firstname-lastname@example.com")

(tabular (fact "invalid email addresses are determined to be invalid"
  (valid? ?address) => false)
  ?address
  "plainaddress"
  "#@%^%#$@#$@#.com"
  "@example.com"
  "Joe Smith <email@example.com>"
  "email.example.com"
  "email@example@example.com"
  ".email@example.com"
  "email.@example.com"
  "email..email@example.com"
  "あいうえお@example.com"
  "email@example.com (Joe Smith)"
  "email@example"
  "email@-example.com"
  "email@example..com"
  "Abc..123@example.com")