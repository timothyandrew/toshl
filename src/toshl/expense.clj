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

(defn create [attributes access-token]
  {:pre [(seq attributes)
         (every? #{:amount :date :tags} (keys attributes))]}
  (let [attributes {"amount" (:amount attributes)
                    "date"   (:date attributes)
                    "tags[]" (:tags attributes)}]
    (http/post "https://api.toshl.com/expenses/"
               {:oauth-token access-token
                :form-params attributes})))

(defn- in-words [expense]
  (str (:amount expense) " " (:currency expense) " on " (:date expense) ".
        Tags are: " (str/join " " (:tags expense))))
