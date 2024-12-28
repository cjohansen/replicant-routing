(ns parens.ui)

(defn render-frontpage [{:keys [videos]} _]
  [:div
   [:h1 "Parens of the dead"]
   [:ul
    (for [{:keys [episode/title episode/id]} videos]
      [:li
       [:ui/a {:ui/location {:location/page-id :pages/episode
                             :location/params {:episode/id id}}}
        title]])]])

(defn get-episode [{:keys [videos]} {:keys [location/params]}]
  (->> videos
       (filter (comp #{(:episode/id params)} :episode/id))
       first))

(defn render-episode [state location]
  (let [episode (get-episode state location)]
    [:main
     [:h1 (or (:episode/title episode)
              "Unknown episode")]
     (if (-> location :location/hash-params :description)
       (list
        [:p (:episode/description episode)]
        [:ui/a {:ui/location (update location :location/hash-params dissoc :description)}
         "Hide description"])
       (when (:episode/description episode)
         [:ui/a {:ui/location (assoc-in location [:location/hash-params :description] "1")}
          "Show description"]))
     [:p
      [:ui/a {:ui/location {:location/page-id :pages/frontpage}}
       "Back to episode listing"]]]))

(defn render-not-found [_ _]
  [:h1 "Not found"])

(defn render-page [state location]
  (let [f (case (:location/page-id location)
            :pages/frontpage render-frontpage
            :pages/episode render-episode
            render-not-found)]
    (f state location)))
