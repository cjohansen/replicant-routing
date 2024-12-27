(ns parens.ui)

(defn render-page [{:keys [videos]}]
  [:div
   [:h1 "Parens of the dead"]
   [:ul
    (for [{:keys [episode/title]} videos]
      [:li title])]])
