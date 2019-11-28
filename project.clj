(defproject me.grison/duct-pohjavirta "0.1.0"
  :description "Integrant methods for running a Pohjavirta web server (undertow wrapper)"
  :url "https://github.com/agrison/duct-pohjavirta"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :lein-release {:scm :git :deploy-via :clojars}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [metosin/pohjavirta "0.0.1-alpha5"]
                 [duct/core "0.8.0"]  ; 0.6.2
                 [duct/logger "0.3.0"]] ; 0.2.1
  :profiles
  {:dev {:dependencies [[clj-http "2.1.0"]]}})
