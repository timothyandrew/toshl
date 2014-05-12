(ns toshl.env
  (require [cheshire.core :as json]
           [clojure.java.io :as io]))

(declare env-file env-json set-env-json)

(defn access-token [] (:access-token (env-json)))
(defn set-access-token [access-token] (set-env-json (assoc (env-json) :access-token access-token)))

(def  ^:private env-file (str (System/getenv "HOME") "/.toshl"))
(defn ^:private env-json []
  (if (.exists (io/as-file env-file))
    (json/parse-string (slurp env-file) true)
    {}))
(defn ^:private set-env-json [env-json] (spit env-file (json/generate-string env-json)))
