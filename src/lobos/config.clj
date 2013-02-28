; All from the lobos README here: https://github.com/budu/lobos/
(ns lobos.config
  (:use lobos.connectivity)
  (:require [coming-soon.app :as app]))

(def db
  (app/config :db))