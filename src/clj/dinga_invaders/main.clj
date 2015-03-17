(ns dinga-invaders.main
  (:import dingaInvaders.Invaders))

(defn -main [& args]
  (let [invaders (Invaders.)] ; Declara uma variavel local, para ser usada dentro da função.
    (.game invaders)))
