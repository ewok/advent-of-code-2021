(ns aoc2021.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input
  (str/split-lines
   (slurp
    (io/file (io/resource "day3_input.txt")))))

(def test1
  ["00100",
   "11110",
   "10110",
   "10111",
   "10101",
   "01111",
   "00111",
   "11100",
   "10000",
   "11001",
   "00010",
   "01010"])

(defn get-size [input]
  (count (first input)))

(defn gamma [input]
  (str/join ""
            (let [input-half-count (/ (count input) 2)
                  input-size (get-size input)]
              (loop [i 0, result []]
                (if (>= i input-size) result
                    (recur (inc i) (conj result
                                         (if
                                          (< (loop [elements input, result-sum 0]
                                               (if (<= (count elements) 0)
                                                 result-sum
                                                 (recur
                                                  (rest elements)
                                                  (+ result-sum (Integer/parseInt (subs (first elements) i (inc i)))))))
                                             input-half-count)
                                           0
                                           1))))))))

(defn epsilon [gamma, size]
  (Integer/toBinaryString (bit-xor gamma (Integer/parseInt (str/join "" (repeat size "1")) 2))))

(defn part1 [input]
  (let [gamma (Integer/parseInt (gamma input) 2)
        epsilon (Integer/parseInt (epsilon gamma (get-size input)) 2)]
    (Integer/toString (* gamma epsilon))))

(defn do []
  (format "part1: %s;" (part1 input)))
