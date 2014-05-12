(ns toshl.core
  (:gen-class)
  (require [toshl.oauth :as oauth]
           [toshl.expense :as expense]
           [toshl.env :as env]))

(defn -main
  [& args]
  (let [access-token (or (env/access-token) (oauth/login))]
    (env/set-access-token access-token)
    (println (expense/all access-token))))
