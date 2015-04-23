(ns dingainvaders.core
  (:gen-class))

(import 'javax.swing.JFrame)

(defn -main
  [& args]
  (def frame (JFrame. "Dinga Invaders"))
  (.setSize frame 800 600)
  (.setDefaultCloseOperation frame JFrame/EXIT_ON_CLOSE)
  (.setLocationRelativeTo frame nil)
  (.setVisible frame true))
