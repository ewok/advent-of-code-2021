(ns aoc2021.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def output
  (map #(Integer/parseInt %)
       (string/split-lines
        (slurp
         (io/file (io/resource "day1_input.txt"))))))

(defn calculate-trend [input]
  (map (fn [x y]
         (if (< x y) 1 0))
       input (rest input)))

(defn do []
  (reduce + (calculate-trend output)))
