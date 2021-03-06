(ns aoc2021.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input
  (str/split-lines
   (slurp
    (io/file (io/resource "day3_input.txt")))))

;; part1

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

;; part2

;; helpers
(defn find-most-bit [input bit]
  (get (->> input
            (map #(Integer/parseInt (subs % bit (inc bit))))
            frequencies
            ((fn [x] (if (= (get x 0) (get x 1)) {1 1} x)))
            (sort-by val)
            reverse
            first
            )
       0))

(defn find-least-bit [input bit]
  (get (->> input
            (map #(Integer/parseInt (subs % bit (inc bit))))
            frequencies
            ((fn [x] (if (= (get x 0) (get x 1)) {0 0} x)))
            (sort-by val)
            first)
       0))

(defn filter-by-bit [input bit value]
  (->> input
       (filter #(= (Integer/parseInt (subs % bit (inc bit))) value))
       flatten
       vec))

;; oxygen
(defn oxygen [input]
  (loop [i 0, elements input]
      (if
       (= 1 (count elements))
        (first elements)
        (recur (inc i) (filter-by-bit elements i (find-most-bit elements i))))))

;; co2
(defn co2 [input]
  (loop [i 0, elements input]
      (if
       (= 1 (count elements))
        (first elements)
        (recur (inc i) (filter-by-bit elements i (find-least-bit elements i))))))

;; main loop
(defn part2 [input]
  (* (Integer/parseInt (oxygen input) 2) (Integer/parseInt (co2 input) 2)))

(defn do []
  (format "part1: %s; part2: %s" (part1 input) (part2 input)))
