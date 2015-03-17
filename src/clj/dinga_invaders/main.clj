(ns dinga_invaders.main
  (:import dinga_invaders.Invaders))

(defn -main [& args]
  (let [invaders (Invaders.)] ; Declara uma variavel local, para ser usada dentro da função.
    (.game invaders)))
