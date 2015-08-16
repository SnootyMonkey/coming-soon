(ns coming-soon.helpers.colors
  (:require [clojure.contrib.string :refer (map-str)]
            [tinter.core :refer (hex-str-to-dec)]))

(defn- rgb-tuple [hex-color]
  ;; strip off the prefixed # if there is one
  (let [color (last (clojure.string/split hex-color #"#"))]
    (let [full-color
          ;; convert XYZ to XXYYZZ if necessary
          (if (= (count color) 3) (map-str #(str % %) color) color)]
      ;; convert the 6 hex digits to a sequence of the 3 RGB colors
      (hex-str-to-dec full-color))))

(defn rgb-color 
  "Given a hex CSS color such as #EEE or #1A1A1A, convert it to an RGB CSS color such as rgb(255, 16, 22)"
  [hex-color]
  (str "rgb(" (clojure.string/join "," (rgb-tuple hex-color)) ")"))

(defn rgba-color
  "Given a hex CSS color such as #EEE or #1A1A1A and an opacity, convert it to an RGB CSS color such as rgb(255, 16, 22, 0.5)"
  [hex-color alpha]
  (str "rgba(" (clojure.string/join "," (rgb-tuple hex-color)) "," alpha ")"))