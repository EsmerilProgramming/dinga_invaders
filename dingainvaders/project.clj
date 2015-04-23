(defproject dingainvaders "0.1.0"
  :description "small invaders based game"
  :license {:name "Apache License Version 2"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot dingainvaders.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

