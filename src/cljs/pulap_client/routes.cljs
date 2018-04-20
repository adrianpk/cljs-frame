(ns pulap-client.routes
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [secretary.core :as secretary]
            [goog.events :as gevents]
            [goog.history.EventType :as EventType]
            [re-frame.core :as re-frame]
            [pulap-client.events :as events]))

(defn hook-browser-navigation! []
  (doto (History.)
    (gevents/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (re-frame/dispatch [::events/set-active-panel :home-panel]))

  (defroute "/about" []
    (re-frame/dispatch [::events/set-active-panel :about-panel]))

  (defroute "/real-estate" []
    (re-frame/dispatch [::events/set-active-panel :real-estate-list-page]))

  (defroute "/real-estate-edit/:id" [id]
    (re-frame/dispatch [::events/set-active-panel :real-estate-item-edit-page])
    (re-frame/dispatch [::events/fetch-real-estate-item id]))

  (defroute "/real-estate/:id" [id]
    (re-frame/dispatch [::events/set-active-panel :real-estate-item-page])
    (re-frame/dispatch [::events/fetch-real-estate-item id])
    ;; (re-frame/dispatch [::events/set-real-estate-id id]))
)
  ;; --------------------
  (hook-browser-navigation!))
