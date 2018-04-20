(ns pulap-client.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [pulap-client.events :as events]
            [pulap-client.routes :as routes]
            [pulap-client.views :as views]
            [pulap-client.config :as config]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
