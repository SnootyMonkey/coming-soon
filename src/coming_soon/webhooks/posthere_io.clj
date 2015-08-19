(ns coming-soon.webhooks.posthere-io
  (:require [clj-http.client :as client]
            [clj-json.core :as json]
            [coming-soon.webhooks.webhook :as webhook]))

(def posthere-io-url "http://posthere.io/")

(defmethod webhook/invoke :posthere-io [args]
  (let [url (str posthere-io-url (:posthere-io-uuid (:config args)))]
    (println "Webhook callback posting to:" url)
    (client/post url {
      :content-type :json
      :body (json/generate-string {
        :email (:email args)
        :referrer (:referrer args)})})))