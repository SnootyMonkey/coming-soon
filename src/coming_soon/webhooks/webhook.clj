(ns coming-soon.webhooks.webhook)

;; Call an external service as a result of a new contact (:email and
;; :referrer) being created. The webhook specific configuration options, if any,
;; are passed in as a :config map. It's OK if your implementation is blocking as
;; this will be invoked asynchronously.
(defmulti invoke :webhook)

(defmethod invoke :default [args]
  (println "Warning: no webhook implementation for -" (:webhook args)))