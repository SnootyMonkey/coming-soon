(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))

(defmigration add-contacts-table
  (up [] (create
          (tbl :contacts
            (varchar :name 100)
            (varchar :email 100 :unique)
            (varchar :twitter 100 :unique)
            (varchar :referrer 512))))
  (down [] (drop (table :contacts))))