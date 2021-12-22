(ns aoc2021.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def route
  (map #(str/split % #" ")
       (str/split-lines
        (slurp
         (io/file (io/resource "day2_input.txt"))))))

(defn calculate [route-input]
  (loop [horizontal 0 depth 0 route route-input]
    (let [first-route (first route)
          way (get first-route 0)
          steps (Integer/parseInt (re-find #"\d+" (get (if (nil? first-route) ["0" "0"] first-route) 1)))
          rest-route (rest route)]
      (cond
        (= way "forward") (recur (+ horizontal steps) depth rest-route)
        (= way "down") (recur horizontal (+ depth steps) rest-route)
        (= way "up") (recur horizontal (- depth steps) rest-route)
        :else (* horizontal depth)))))

(defn do [] (calculate route))
