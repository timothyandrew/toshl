(ns toshl.core
  (:gen-class)
  (require [toshl.oauth :as oauth]
           [toshl.expense :as expense]))

(defn -main
  [& args]
  (let [access-token (oauth/login)]
    (map println (expense/all access-token))))
