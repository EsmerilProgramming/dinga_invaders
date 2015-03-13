(ns dinga_invaders.main
  (:import dinga_invaders.Hail))

( defn -main [& args]
  (let [hail (Hail.)] ; Declara uma variavel local, para ser usada dentro da função.
    (println (.toString hail))
    (.abc hail)))
