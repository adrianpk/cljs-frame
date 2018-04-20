(ns pulap-client.views.real-estate
  (:require [re-frame.core :as re-frame]
            [pulap-client.subs :as subs]
            [cljs.pprint :refer [pprint]]))

(defn non-mobile-classes []
  ["is-hidden-mobile"])

(defn mobile-classes []
  ["is-mobile-only" "is-hidden-tablet" "is-hidden-desktop" "is-hidden-widescreen" "is-hidden-fullhd"])

(defn app-tabs [plus-classes]
  (pprint "real-estate/app-tabs")
  (let [classes (clojure.string/join " " plus-classes)]
    (pprint classes)
    [:div {:class (str "tabs" " " classes)}
     [:ul
      [:li {:class "is-active"}
       [:a {:href "/#/real-estate"} "Real Estate"]]
      [:li
       [:a {:href "/#/publications"} "Publications"]]]]))

(defn layout [& {:keys [left, main, right-top, right-bottom] :or {left "", main "", right-top "", right-bottom ""}}]
  (pprint "real-estate/layout")
  [:div {:class "section"}
   [:div {:class "container"}
    ;; (app-tabs (non-mobile-classes))
    [:div {:class "tile is-ancestor"}
      ;; Left
     [:div {:class "tile is-parent is-hidden-mobile"}
      [:div {:class "tile is-child green post"} "[left]"]]
      ;; Main
     [:div {:class "tile is-6 is-parent is-mobile-flex"}
      [:div {:class "tile is-child pink post"} main]]
      ;; Right
     [:div {:class "tile is-vertical is-parent is-hidden-mobile"}
      [:div {:class "tile is-child blue post"} "[right-top]"]
      [:div {:class "tile is-child gold post"} "[right-bottom]"]]]
    ;; (app-tabs (mobile-classes))
]])

;; List page
(defn real-estate-list-page []
  (pprint "real-estate/real-estate-list-page")
  [:div
   (layout :main (real-estate-list))])

(defn real-estate-list []
  (pprint "real-estate/real-estate-list")
  [:table {:class "table"}
   [:thead
    [:tr
     [:th
      [:abbr {:title "Id"} "Id"]]
     [:th
      [:abbr {:title "Name"} "Name"]]
     [:th
      [:abbr {:title "Description"} "Description"]]]]
   [:tbody
    [:tr
     [:th "1"]
     [:td
      [:a {:href "/", :title "Name"} "Name"]]
     [:td "Description"]]]])

;; Item page

(defn real-estate-item-page []
  (pprint "pulap-client.views.real-estate/real-estate-item-page")
  [:div
   [real-estate-item]])

(defn real-estate-item []
  (pprint "pulap-client.views.real-estate/real-estate-item")
  (let [item-data @(re-frame/subscribe [::subs/real-estate-item-data])
        description (:description item-data)]
    [:div
     [:div "Name: " (:name item-data)]
     [:div "Description: " (:description item-data)]]))

;; Edit item page

(defn real-estate-item-edit-page []
  (pprint "pulap-client.views.real-estate/real-estate-item-edit-page")
  (let [item-data @(re-frame/subscribe [::subs/real-estate-item-data])
        description (:description item-data)
        main-tile
        [:div
         [:form
      ;; Name
          [:div {:class "field"}
           [:label {:class "label"} "Name"]
           [:div {:class "control has-icons-left has-icons-right"}
            [:input {:class "input", :placeholder "4 to 16 characters", :value "Username", :type "text"}]
            [:span {:class "icon is-small is-left"}
             [:i {:class "fas"}]]
            [:span {:class "icon is-small is-right"}
             [:i {:class ""}]]]
           [:p {:class "help"} ""]]
      ;; Description 
          [:div {:class "field"}
           [:label {:class "label"} "Description"]
           [:div {:class "control has-icons-left has-icons-right"}
            [:input {:class "input", :placeholder "", :value "Description", :type "text"}]
            [:span {:class "icon is-small is-left"}
             [:i {:class "fas"}]]
            [:span {:class "icon is-small is-right"}
             [:i {:class "fas fa-check"}]]]
           [:p {:class "help"} "  "]]
      ;; Buttons
          [:div {:class "field is-grouped"}
           [:div {:class "control"}
            [:button {:class "button is-link"} "Submit"]]
           [:div {:class "control"}
            [:button {:class "button is-text"} "Cancel"]]]]]]
    (layout :main main-tile)))
