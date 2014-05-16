(defproject toshl "0.1.0-SNAPSHOT"
  :description "A command-line interface to the toshl.com web application"
  :url "http://github.com/timothyandrew/toshl"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.cli "0.3.1"]
                 [http-kit "2.1.18"]
                 [cheshire "5.3.1"]
                 [clj-time "0.7.0"]]
  :main ^:skip-aot toshl.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
