(ns toshl.expense
  (require [org.httpkit.client :as http]
           [cheshire.core :as json]
           [clojure.string :as str]))

(declare in-words)

(defn all [access-token]
  (let [response (http/get "https://api.toshl.com/expenses"
                                {:oauth-token access-token})
        expenses (json/parse-string (:body @response) true)]
    (map in-words expenses)))

(defn in-words [expense]
  (str (:amount expense) " " (:currency expense) " on " (:date expense) ". Tags are: " (str/join " " (:tags expense))))
