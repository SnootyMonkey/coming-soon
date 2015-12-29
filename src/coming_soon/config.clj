(ns coming-soon.config
  (:require [environ.core :refer (env)]))

(defonce config-file (or (env :config-file) "config.edn"))

(defonce config (read-string (slurp config-file)))

(defonce coming-soon (config :coming-soon))

(defonce admin-user (or (env :admin-user) (coming-soon :admin-user)))

(defonce admin-password (or (env :admin-password) (coming-soon :admin-password)))

(defonce redis (config :redis))

(defonce landing-page (config :landing-page))

(defn landing-page-uri [uri] (println uri) (config :landing-page))

(defonce webhooks (or (config :webhooks) {}))

(defonce variations (or (config :variations) []))