(ns pulap-client.views.real-estate
  (:require [re-frame.core :as re-frame]
            [pulap-client.subs :as subs]
            ))


;; real-estate panel

(defn real-estate-panel []
  [:div "This is the Real Estate Page."
   [:div [:a {:href "#/"} "go to Home Page"]]
   [:div [:a {:href "#/about"} "go to About Page"]]])


;; <nav class="navbar" role="navigation" aria-label="main navigation">
;;   <div class="navbar-brand">
;;     <!-- navbar items, navbar burger... -->
;;   </div>
;; </nav>

