(ns pulap-client.views
  (:require [re-frame.core :as re-frame]
            [pulap-client.subs :as subs]
            [pulap-client.assets.images :as assets]
            [pulap-client.views.real-estate :as real-estate-views]
            [cljs.pprint :refer [pprint]]))

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [:div [:a {:href "#/real-estate"} "go to Real Estate Page"]]
     [:div [:a {:href "#/about"} "go to About Page"]]]))

(defn about-panel []
  [:div "This is the About Page."
   [:div [:a {:href "#/"} "go to Home Page"]]])

(defn panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    :real-estate-list-page [real-estate-views/real-estate-list-page]
    :real-estate-item-page [real-estate-views/real-estate-item-page]
    :real-estate-item-edit-page [real-estate-views/real-estate-item-edit-page]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (pprint "pulap-client.views/main-panel")
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div
     (navbar)
     (show-panel @active-panel)
     (bottom-bar)]))

(defn navbar []
  [:div {:class "navbar has-shadow is-spaced is-info is-fixed-top" :role "navigation"}
   [:div {:class "container"}
    [:div {:class "navbar-brand"} ;;style="margin-left: 0px; margin-right: 0px"
     [:div {:class "navbar-burger burger" :style {:margin-left "0px" :margin-right "auto"} :data-target "pulap-navbar"}
      [:span]
      [:span]
      [:span]]
     [:a {:class "navbar-item" :href "/"}
      [:img {:src (assets/logo), :alt "Pulap", :height "28"}]]]
      ;; [:img {:height "28"
      ;;        :alt "Pulap - Real Estate Manager"
      ;;        :src "/images/pulap-logo.jpg"}]]]

    [:div {:id "pulap-navbar" :class "navbar-menu"}
     [:div {:class "navbar-start"}
      [:a {:class "navbar-item bd-navbar item-documentation" :href "/documentation"}
       [:span {:class "icon has-text-primary"} "-"]
       [:span "Documentation"]]]]]])

(defn navbar-prev []
  [:div {:class "navbar is-info is-fixed-top ", :role "navigation"}
   [:div {:class "navbar-brand"}
    [:div {:class "navbar-burger burger" :style {:margin-left "0px" :margin-right "auto"} :data-target "pulap-navbar"}
     [:span]
     [:span]
     [:span]]

    [:a {:class "navbar-item", :href "/"}
     [:img {:src (assets/logo), :alt "Pulap", :height "28"}]]]
     ;;[:img {:src "/images/pulap-logo.jpg", :alt "Pulap", :height "28"}]]]

   [:div {:id "pulap-navbar", :class "navbar-menu"}
    [:div {:class "navbar-start"}
     [:a {:class "navbar-item", :href "/"} "Home"]]
    [:div {:class "navbar-end"}
     [:div {:class "navbar-item has-dropdown is-hoverable"}
      [:div {:class "field is-grouped"}
       [:div {:class "control"}
        [:a {:class "navbar-link", :href "/user"} "User"]
        [:div {:class "navbar-dropdown is-boxed"}
         [:a {:class "navbar-item is-active", :href "/profile"} "Profile"]
         [:a {:class "navbar-item", :href "/settings"} "Setings"]
         [:hr {:class "navbar-divider"}]
         [:a {:class "navbar-item", :href "/signout"} "Signout"]]]]]]]])

(defn bottom-bar []
  [:div {:class "navbar is-fixed-bottom"}
   [:div {:class "navbar-brand"}
    [:a {:class "navbar-item is-expanded is-block has-text-centered"}
     [:i {:class "fa fa-home"}]]
      ;; [:p {:class "is-size-7"} ""]]
    [:a {:class "navbar-item is-expanded is-block has-text-centered"}
     [:i {:class "fa fa-search"}]]
      ;; [:p {:class "is-size-7"} "Search"]]
    [:a {:class "navbar-item is-expanded is-block has-text-centered"}
     [:i {:class "fa fa-bell"}]]
      ;; [:p {:class "is-size-7"} "Notifications"]]
    [:a {:class "navbar-item is-expanded is-block has-text-centered"}
     [:i {:class "fa fa-envelope"}]]
     ;; [:p {:class "is-size-7"} "Messages"
    ]])
