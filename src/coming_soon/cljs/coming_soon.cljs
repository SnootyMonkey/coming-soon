(ns coming-soon)

(.log js/console "ClojureScript is working")

;; define the function to be attached to form submission event
(defn validate-form []
	(.log js/console "in validate-form")
	;; get email and password element from their ids in the HTML form
	(let [email (.getElementById js/document "email")]
		(.log js/console (str "email is " (.-value email))))
	false)
	; (if (> (count (.-value email) 0))
  ; 		true
  ;  		(do (js/alert "Please, complete the form!")
  ;  			false))))

;; define the function to attach validate-form to onsubmit event of
;; the subscribe form
(defn init []
	;; verify that js/document exists and that it has a getElementById
	;; property
	(.log js/console "in init")
	(if (and js/document (.-getElementById js/document))
    ;; get subscribe form by element id and set its onsubmit property to
    ;; our validate-form function
    (let [subscribe-form (.getElementById js/document "subscribe")]
  	(set! (.-onsubmit subscribe-form) validate-form)))
		(.log js/console "form validation function set"))

;; initialize the HTML page in unobtrusive way
(set! (.-onload js/window) init)