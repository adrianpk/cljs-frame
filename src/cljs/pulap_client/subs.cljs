(ns pulap-client.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(re-frame/reg-sub
 ::real-estate-id
 (fn [db _]
   (:real-estate-id db)))

(re-frame/reg-sub
 ::real-estate-item-data
 (fn [db _]
   (:real-estate-item-data db)))
