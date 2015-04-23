(ns dingainvaders.core
  (:gen-class))

(import 'javax.swing.JFrame)

(defn -main
  [& args]
  (def frame (JFrame. "Dinga Invaders"))
  (.setSize frame 800 600)
  (.setVisible frame true))
