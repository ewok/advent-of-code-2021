(ns aoc2021.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input
  (map #(str/split % #" ")
       (str/split-lines
        (slurp
         (io/file (io/resource "day2_input.txt"))))))

(defn part1 [route-input]
  (loop [horizontal 0 depth 0 route route-input]
    (let [first-route (if (nil? (first route)) ["0" "0"] (first route))
          way (get first-route 0)
          steps (Integer/parseInt (re-find #"\d+" (get first-route 1)))
          rest-route (rest route)]
      (cond
        (= way "forward") (recur (+ horizontal steps) depth rest-route)
        (= way "down") (recur horizontal (+ depth steps) rest-route)
        (= way "up") (recur horizontal (- depth steps) rest-route)
        :else (* horizontal depth)))))

(defn part2 [route-input]
  (loop [horizontal 0
         depth 0
         aim 0
         route route-input]
    (let [first-route (if (nil? (first route)) ["0" "0"] (first route))
          way (get first-route 0)
          steps (Integer/parseInt (re-find #"\d+" (get first-route 1)))
          rest-route (rest route)]
      (cond
        (= way "forward") (recur (+ horizontal steps) (+ depth (* aim steps)) aim rest-route)
        (= way "down") (recur horizontal depth (+ aim steps) rest-route)
        (= way "up") (recur horizontal depth (- aim steps) rest-route)
        :else (* horizontal depth)))))

(defn do []
  (format "part1: %s; part2: %s" (part1 input) (part2 input)))
