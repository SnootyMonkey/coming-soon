(ns coming-soon.models.contact)

(defn email? [identifier] 
  false)

(defn twitter? [identifier]
  (not (email? identifier)))

(defn exists? [identifier]
  false)