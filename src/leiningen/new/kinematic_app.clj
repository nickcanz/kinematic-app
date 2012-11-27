(ns leiningen.new.kinematic-app
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "kinematic-app"))

(defn kinematic-app
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (->files data
             ["project.clj"                         (render "project.clj" data)]
             ["src/{{sanitized}}/daemon.clj"        (render "daemon.clj" data)]
             ["src/{{sanitized}}/web.clj"           (render "web.clj" data)]
             ["src/{{sanitized}}/web/api/hello.clj" (render "web/api/hello.clj" data)]
             ["run-server.sh"                       (render "run-server.sh" data)]
             )))
