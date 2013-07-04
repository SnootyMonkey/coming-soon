(ns coming-soon.config
  (:require [clojure.string :refer (lower-case)]))

(def env (or (System/getenv "ENV") "prod"))

(def config (read-string (slurp 
  (if (= (lower-case env) "test")
    "test/test-config.edn"
    "config.edn"))))

(def coming-soon (config :coming-soon))

(def redis (config :redis))

(def landing-page (config :landing-page))