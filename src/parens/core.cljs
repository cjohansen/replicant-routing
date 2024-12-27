(ns parens.core
  (:require [parens.ui :as ui]
            [replicant.dom :as r]))

(defn main [el state]
  (r/render el (ui/render-page state)))

(defn bootup [el state]
  ;; Perform bootup steps that should only be done once here
  (main el state))
