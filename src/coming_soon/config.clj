(ns coming-soon.config)

(def config (read-string (slurp "config.edn")))

(def coming-soon (config :coming-soon))

(def landing-page (config :landing-page))