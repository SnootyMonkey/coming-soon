(ns coming-soon.webhooks.mailchimp
  (:require [clj-http.client :as client]
            [clj-json.core :as json]
            [environ.core :refer (env)]
            [coming-soon.config :as config]
            [coming-soon.webhooks.webhook :as webhook]))

(def mailchimp-api "https://us11.api.mailchimp.com/3.0/lists/")

(defonce api-key (or (env :mailchimp-api-key) (get-in config/webhooks [:mailchimp :api-key])))

(defonce list-id (get-in config/webhooks [:mailchimp :list-id]))
(defonce status (get-in config/webhooks [:mailchimp :status]))

(defmethod webhook/invoke :mailchimp [args]
  (let [url (str mailchimp-api list-id "/members")]
    (println "Webhook callback posting to:" url)
    (client/post url {
      :content-type :json
      :basic-auth ["coming-soon" api-key]
      :body (json/generate-string {
        :email_address (:email args)
        :status status})})))