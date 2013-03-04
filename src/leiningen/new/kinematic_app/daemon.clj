(ns {{name}}.daemon
  (:require
   {{name}}.web
   swank.swank)
  (:use
   [ring.adapter.jetty :only [run-jetty]]
   [kinematic.dsl      :only [dyn-handler]]))


(defonce *jetty* (atom nil))

(def jetty-config {:port 8080 :join? false})

(def swank-config {:port 4444 :host "localhost"})

(defn restart-jetty []
  (when @*jetty*
    (.stop @*jetty*))
  (reset! *jetty* (run-jetty (dyn-handler :{{name}}-api) jetty-config)))

(defn main-service []
  (swank.swank/start-server
   :host (:host swank-config)
   :port (:port swank-config))
  (restart-jetty)
  (println "Kinematic started!")
  (println "Point your browser at http://localhost:8080/{{name}}/api/hello/world"))

(defn -main [& args]
  (try
   (main-service)
   (catch Throwable ex
     (.println System/err (format "Service Failed: %s" ex))
     (.printStackTrace ex))))
