(ns pulap-client.events
  (:require [pulap-client.db :as db]
            [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
            [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [re-frame.core :as re-frame :refer [reg-event-fx]]
            [cljs.pprint :refer [pprint]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn-traced [db [_ active-panel]]
            (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 ::set-real-estate-id
 (fn [db [_ id]]
   (assoc db :real-estate-id id)))

(reg-event-fx                 ;; <-- note the `-fx` extension
 ::fetch-real-estate-item     ;; <-- the event id
 (fn                          ;; <-- the handler function
   [{db :db} [_ id]]          ;; <-- 1st argument is coeffect, from which we extract db

   (let [url (str "http://localhost:4000/api/v1/real-estate/" id)]
     (cljs.pprint/pprint url)
      ;; we return a map of (side) effects
     {:http-xhrio {:method          :get
                   :uri             url
                   :format          (ajax/json-request-format)
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [::process-real-estate-item-response]
                   :on-failure      [::process-real-estate-item-response-failure]}
      :db  (assoc db :loading? true)})))

(re-frame/reg-event-db
 ::process-real-estate-item-response
 (fn [db [_ response]]
   (let [response (js->clj response)
         data (:data response)]
     (-> db
         (assoc :loading? false)
         (assoc :real-estate-item-data data)))))

(re-frame/reg-event-db
 ::process-real-estate-item-response-failure
 (fn [db [_ response]]
   (do
     (pprint "::process-real-estate-item-response-failure")
     (assoc :loading? false)
     (assoc :real-estate-item-data (js->clj response)))))

(reg-event-fx
 ::update-real-estate
 (fn
   [{db :db} [_ id]]

   (let [url (str "http://localhost:4000/api/v1/real-estate/" id)]
     (cljs.pprint/pprint url)
     ;; we return a map of (side) effects
     {:http-xhrio {:method          :get
                   :uri             url
                   :format          (ajax/json-request-format)
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [::process-real-estate-item-response]
                   :on-failure      [::process-real-estate-item-response-failure]}
      :db  (assoc db :loading? true)})))
