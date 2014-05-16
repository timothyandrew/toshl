(ns toshl.core
  (:gen-class)
  (require [toshl.oauth :as oauth]
           [toshl.expense :as expense]
           [toshl.env :as env]
           [toshl.dispatch :as dispatch]))

(defn -main
  [& args]
  (if-not (env/access-token)
    (env/set-access-token (oauth/login)))
  (dispatch/by-arguments args))
