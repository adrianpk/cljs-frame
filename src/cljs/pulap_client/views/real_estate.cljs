(ns pulap-client.views.real-estate
  (:require [re-frame.core :as re-frame]
            [pulap-client.subs :as subs]
            ))


;; real-estate panel

(defn real-estate-list-page []
  [:div
   [app-tabs]
   [real-estate-list]])

(defn app-tabs []
  [:div {:class "tabs"}
   [:ul
    [:li {:class "is-active"}
     [:a {:href "/#/real-estate"} "Real Estate"]]
    [:li
     [:a {:href "/#/publications"} "Publications"]]
    ]]
  )

(defn real-estate-list []
  [:table {:class "table"} 
    [:thead
      [:tr
        [:th
          [:abbr {:title "Id"} "Id"]]
        [:th
          [:abbr {:title "Name"} "Name"]]
        [:th
          [:abbr {:title "Description"} "Description"]]
       ]
    ]
    [:tbody
      [:tr
        [:th "1"]
        [:td
          [:a {:href "/", :title "Name"} "Name"]]
        [:td "Description"]
      ]
    ]
  ]
)

(defn real-estate-item-page []
  (let [id (re-frame/subscribe [::subs/real-estate-id])]
    [:div "This id is " @id]))
  )

  ;; [:div
  ;;  [app-tabs]
  ;;  [real-estate-list]])
