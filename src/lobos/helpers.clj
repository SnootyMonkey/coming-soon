; All from the lobos README here: https://github.com/budu/lobos/
(ns lobos.helpers
  (:refer-clojure :exclude [bigint boolean char double float time])
  (:use (lobos schema)))

(defn surrogate-key [table]
  (integer table :id :auto-inc :primary-key))

(defn timestamps [table]
  (-> table
      (timestamp :updated_on)
      (timestamp :created_on (default (now)))))

(defmacro tbl [name & elements]
  `(-> (table ~name)
       (timestamps)
       ~@(reverse elements)
       (surrogate-key)))