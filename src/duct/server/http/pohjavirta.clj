(ns duct.server.http.pohjavirta
  (:require [duct.logger :refer [log]]
            [integrant.core :as ig]
            [pohjavirta.server :as server]))

(defmethod ig/init-key :duct.server.http/pohjavirta [_ {:keys [logger async?] :as opts}]
  (let [handler (atom (delay (:handler opts)))
        logger (atom logger)
        options (dissoc opts :handler :logger)]
    (log @logger :report ::starting-server (select-keys opts [:port]))
    {:handler handler
     :logger  logger
     :server  (let [server (server/create (fn [req] (@@handler req)) options)]
                (server/start server)
                server)}))

(defmethod ig/halt-key! :duct.server.http/pohjavirta [_ {:keys [server logger]}]
  (log @logger :report ::stopping-server)
  (server/stop server))

(defmethod ig/suspend-key! :duct.server.http/pohjavirta [_ {:keys [handler]}]
  (reset! handler (promise)))

(defmethod ig/resume-key :duct.server.http/pohjavirta [key opts old-opts old-impl]
  (if (= (dissoc opts :handler :logger) (dissoc old-opts :handler :logger))
    (do (deliver @(:handler old-impl) (:handler opts))
        (reset! (:logger old-impl) (:logger opts))
        old-impl)
    (do (ig/halt-key! key old-impl)
        (ig/init-key key opts))))
