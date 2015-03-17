(defproject dinga-invaders "0.1"
  :description "hail from the other side!"
  :license {:name "Apache License Version 2"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :source-paths ["src/clj"]
  :java-source-paths ["src/main/java"]
  :prep-tasks [["compile" "dinga_invaders.main"]
               "javac" "compile"]
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:uberjar {:aot :all}}
  :main dinga_invaders.main)

