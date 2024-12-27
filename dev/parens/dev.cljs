(ns parens.dev
  (:require [parens.core :as app]
            [parens.data :as data]))

(defonce el (js/document.getElementById "app"))
(defonce started (app/bootup el data/data))

(defn ^:dev/after-load main []
  ;; Add additional dev-time tooling here
  (app/main el data/data))
