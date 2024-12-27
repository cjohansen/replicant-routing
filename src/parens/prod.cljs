(ns parens.prod
  (:require [parens.core :as app]
            [parens.data :as data]))

(defn main []
  ;; Make production adjustments here
  (app/bootup data/data (js/document.getElementById "app")))
