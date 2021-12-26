(ns aoc2021.core
  (:gen-class)
  (:require [aoc2021.day1 :as day1]
            [aoc2021.day2 :as day2]
            [aoc2021.day3 :as day3]))

(defn -main []
  (println
   (map #(format "Day %s result: %s\n" (get % 0) (get % 1))
        [[1 (day1/do)]
         [2 (day2/do)]
         [3 (day3/do)]])))
