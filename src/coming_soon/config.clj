(ns coming-soon.config
  (:require [environ.core :refer (env)]))

(def config-file (or (env :config-file) "config.edn"))

(def config (read-string (slurp config-file)))

(def coming-soon (config :coming-soon))

(def redis (config :redis))

(def landing-page (config :landing-page))