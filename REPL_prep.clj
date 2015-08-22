;; productive set of development namespaces
(use 'clojure.stacktrace)
(use 'clj-time.format)
(use 'print.foo)
(require '[coming-soon.config :as config] :reload-all)
(require '[coming-soon.models.email :as email] :reload-all)
(require '[coming-soon.models.contact :as contact] :reload-all)

;; Try out configured webhooks
(require '[coming-soon.lib.webhooks :as wh] :reload-all)
(wh/call "foo@bar.com" "http://cnn.com/")

;; print last exception
(print-stack-trace *e)