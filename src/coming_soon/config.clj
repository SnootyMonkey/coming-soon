(ns coming-soon.config)

(def config (read-string (slurp "config.edn")))

(def coming-soon (config :coming-soon))

(def redis (config :redis))

(def landing-page (config :landing-page))