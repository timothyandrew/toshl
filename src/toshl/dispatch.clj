(ns toshl.dispatch
  (require [toshl.expense :as expense]
           [clojure.string :as string]
           [clojure.tools.cli :as cli]
           [toshl.env :as env]
           [clj-time.core :as time]
           [clj-time.format :as time-format]))

(declare date->string string->date)

(defn dispatch-add [args]
  (let [cli-options     [["-t" "--tags TAGS" "Tags"
                          :id :tags
                          :parse-fn #(string/split % #",")
                          :default ["misc"]]]
        parsed-opts     (cli/parse-opts args cli-options)
        expense-promise (expense/create {:amount (:arguments parsed-opts)
                                         :tags (get-in parsed-opts [:options :tags])
                                         :date (date->string (time/now))}
                                        (env/access-token))]
    (print "Adding the expense...")
    (flush)
    (deref expense-promise)
    (println "added!")))

(defn by-arguments [[command & args]]
  (cond
   (= command "add") (dispatch-add args)))

(defn- date->string [date]
  (time-format/unparse
   (time-format/formatter "YYYY-MM-dd")
   date))

(defn- string->date [date-string]
  (println string->date)
  (time-format/parse
   (time-format/formatter "YYYY-MM-dd")
   date-string))
