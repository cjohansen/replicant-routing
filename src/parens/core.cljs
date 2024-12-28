(ns parens.core
  (:require [parens.router :as router]
            [parens.ui :as ui]
            [replicant.alias :as alias]
            [replicant.dom :as r]))

(defn routing-anchor [attrs children]
  (let [routes (-> attrs :replicant/alias-data :routes)]
    (into [:a (cond-> attrs
                (:ui/location attrs)
                (assoc :href (router/location->url routes
                               (:ui/location attrs))))]
          children)))

(alias/register! :ui/a routing-anchor)

(defn find-target-href [e]
  (some-> e .-target
          (.closest "a")
          (.getAttribute "href")))

(defn render-location [el state routes location]
  (r/render
   el
   (ui/render-page state location)
   {:alias-data {:routes routes}}))

(defn main [el state]
  (->> js/location.href
       (router/url->location router/routes)
       (render-location el state router/routes)))

(defn get-current-location []
  (->> js/location.href
       (router/url->location router/routes)))

(defn route-click [e el state routes]
  (let [href (find-target-href e)]
    (when-let [location (router/url->location routes href)]
      (.preventDefault e)
      (if (router/essentially-same? location (get-current-location))
        (.replaceState js/history nil "" href)
        (.pushState js/history nil "" href))
      (render-location el state router/routes location))))

(defn bootup [el state]
  (js/document.body.addEventListener "click" #(route-click % el state router/routes))

  (js/window.addEventListener
   "popstate"
   (fn [_]
     (render-location el state router/routes (get-current-location))))

  (main el state))
