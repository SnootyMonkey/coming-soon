(ns coming-soon.expectations.models.email-expectations
  (:require [expectations :refer (given expect)]
            [coming-soon.models.email :refer (valid?)]))

; Source of email addresses: http://codefool.tumblr.com/post/15288874550/list-of-valid-and-invalid-email-addresses
; Note: not all the unusual ones were used

(given [x] (expect valid? x)
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

(given [x] (expect (not (valid? x)))
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