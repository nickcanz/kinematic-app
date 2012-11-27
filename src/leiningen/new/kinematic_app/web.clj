(ns {{name}}.web
  (:use kinematic.dsl))

;; Definition of the webapp. The name given here will be used to
;; register new routes that are mounted under the given mount-point.
;; See the generated hello.clj api route for an example.
(defweb :{{name}}-api

  ;; All routes in the app will be relative to this mount point.
  :mount-point        "/{{name}}/api"

  ;; Namespace prefix under which all routes in the app reside.  All
  ;; files with a namespace that matches this prefix will get
  ;; autoloaded by the app when the server starts.
  :app-ns-prefix      :{{name}}.web.api)