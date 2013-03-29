(ns coming-soon.helpers.colors
  (:use [clojure.contrib.string :only (map-str)]
        [tinter.core :only (hex-str-to-dec)]))

(defn rgb-tuple [hex-color]
  ;; strip off the prefixed # if there is one
  (let [color (last (clojure.string/split hex-color #"#"))]
    (let [full-color
      ;; convert XYZ to XXYYZZ if necessary
      (if (= (count color) 3)
        (map-str #(str % %) color)
        color)]
      ;; convert the 6 hex digits to a sequence of the 3 RGB colors
      (hex-str-to-dec full-color))))

(defn rgb-color 
  "Given a hex CSS color such as #EEE or #1A1A1A, convert it to an RGB CSS color such as rgb(255, 16, 22)"
  [hex-color]
  (str "rgb(" (clojure.string/join "," (rgb-tuple hex-color)) ")"))

(defn rgba-color [hex-color alpha]
  "Given a hex CSS color such as #EEE or #1A1A1A and an opacity, convert it to an RGB CSS color such as rgb(255, 16, 22, 0.5)"
  (str "rgba(" (clojure.string/join "," (rgb-tuple hex-color)) "," alpha ")"))