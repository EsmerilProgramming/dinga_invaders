(ns dingainvaders.core
  (:gen-class))

(import 'javax.swing.JFrame)

(defn -main
  [& args]
  (doto (JFrame. "Dinga Invaders")
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 800 600)
    (.setLocationRelativeTo nil)
    (.setExtendedState JFrame/MAXIMIZED_BOTH)
    (.setUndecorated true)
    (.setVisible true)))
