; All from the lobos README here: https://github.com/budu/lobos/
(ns lobos.config
  (:use lobos.connectivity)
  (:require [coming-soon.config :as config]))

(def db
	(config/config :db))