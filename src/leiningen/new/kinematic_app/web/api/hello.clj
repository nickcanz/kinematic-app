(ns {{name}}.web.api.hello
  (:use
    kinematic.bindings
    kinematic.dsl))

;; Register a route for the {{name}}-api webapp.
(defapi :{{name}}-api

  ;; URI patterns that will match this route.
  ;; Keywords in route will be available in :route-params.
  ["/hello" "/hello/:name"])


;; Basic get route. Routes must return a map that can be JSON-ified
(api-get
 (if-let [the-name (get-in request [:route-params :name])]
   {:status 200 :message (format "Hello, %s!" the-name)}
   {:status 200 :message "Hello, World!"}))