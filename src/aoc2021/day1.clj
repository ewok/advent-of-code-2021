(ns aoc2021.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def input
  (map #(Integer/parseInt %)
       (string/split-lines
        (slurp
         (io/file (io/resource "day1_input.txt"))))))

(defn part1 [input]
  (reduce + (map (fn [x y]
                   (if (< x y) 1 0))
                 input (rest input))))

(defn part2 [input]
  (part1
   (flatten
    (map #(reduce + %)
         (partition 3 1 input)))))

(defn do []
  (format "part1: %d; part2: %s" (part1 input) (part2 input)))
