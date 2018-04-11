(ns pulap-client.views
  (:require [re-frame.core :as re-frame]
            [pulap-client.subs :as subs]
            [pulap-client.views.real-estate :as real-estate-views]
            ))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div (str "Hello from " @name ". This is the Home Page.")
     [:div [:a {:href "#/real-estate"} "go to Real Estate Page"]]
     [:div [:a {:href "#/about"} "go to About Page"]]]))


;; about

(defn about-panel []
  [:div "This is the About Page."
   [:div [:a {:href "#/"} "go to Home Page"]]])



;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    :real-estate-panel [pulap-client.views.real-estate/real-estate-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div [main-header] 
      [show-panel @active-panel]]))

(defn main-header []
  [:div [navbar]])

(defn navbar [] 
  [:div {:class "navbar is-transparent"}
   [:div {:class "navbar-brand"}
    [:a {:class "navbar-item", :href "https://bulma.io"}
     [:img {:src "https://bulma.io/images/bulma-logo.png", :alt "Pulap", :width "112", :height "28"}]]
    [:div {:class "navbar-burger burger", :data-target "pulap-navbar"}
     [:span]
     [:span]
     [:span]]]

   [:div {:id "pulap-navbar", :class "navbar-menu"}

      [:div {:class "navbar-start"}
        [:a {:class "navbar-item", :href "/"} "Home" ]
       ]

      [:div {:class "navbar-end"} 
        [:div {:class "navbar-item has-dropdown is-hoverable"}
          [:div {:class "field is-grouped"}
            [:p {:class "control"}
              [:a {:class "navbar-link", :href "/user"} "User" ]
              [:div {:class "navbar-dropdown is-boxed"}
                [:a {:class "navbar-item is-active", :href "/profile"} "Profile" ]
                 [:a {:class "navbar-item", :href "/settings"} "Setings" ]
                 [:hr {:class "navbar-divider"}]
                 [:a {:class "navbar-item", :href "/signout"} "Signout" ]
              ] 
            ]
          ]
        ]
      ]
    ]
  ]
)

<div class="field is-grouped">
<p class="control">
