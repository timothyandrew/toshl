(ns toshl.oauth
  (require [org.httpkit.client :as http]
           [clojure.java.browse :as browse]
           [cheshire.core :as json]))

(declare browse-url client-id client-secret authorize get-token)

(defn login []
  (println "Please login on Toshl.com.")
  (print "Paste the auth code here: ")
  (flush)
  (authorize)
  (let [response (get-token (read-line))
        response-body (get @response :body)
        access-token (get (json/parse-string response-body true) :access_token)]
    access-token))

(defn authorize []
  (browse-url
   (format "https://toshl.com/oauth2/authorize?client_id=%s&response_type=code&state=state" client-id)))

(defn get-token [code]
  (http/post "https://toshl.com/oauth2/token"
             {:form-params {:code code :grant_type "authorization_code" :redirect_uri "http://example.com/"}
              :basic-auth [client-id client-secret]}))

(def ^:private client-id "0baa2e2d-e6cd-42c9-9f92-593ace7f46d2ebcc317cf29e23c53f461dc6ca80822d")
(def ^:private client-secret "21a81ddc-5418-4e6f-a1c4-93c37cd42dddf30796befd79bf03e5ecc7e68b55a47d")
(def ^:private browse-url browse/browse-url)

