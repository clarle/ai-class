(ns unit02.viz
  (:use (incanter core processing))
  (:use [unit02.trees :only (node)]))



(defn- draw-node [canvas node x y]
  (doto canvas
    (stroke-weight 2)
    (fill 0 121 184)
    (stroke 10)
    (ellipse x y 20 20)
    (fill 250)
    (stroke-weight 0)
    (text-size 12)
    (text-align CENTER)
    (text (str (:label node)) x (+ y 5))))

(defn- draw-edge [canvas ax ay bx by]
  (doto canvas
    (stroke-weight 2)
    (stroke 10)
    (line ax ay bx by)))

(defn- draw-tree
  ([tree canvas width] (draw-tree tree canvas width (/ width 2) 20))
  ([tree canvas width x y]
   (when (seq (:children tree))
     (let [children (:children tree)
           width-per-child (/ width (count children))]
       (dorun
         (map (fn [child i]
                (let [cxs-start (- x (/ width 2))                ; where this node's childrens' space starts  [          __________]
                      cx-end (+ cxs-start (* width-per-child i)) ; where this child's space ends              [          _____.....]
                      cx (- cx-end (/ width-per-child 2))        ; put the child into the center of its space [          _____..c..]
                      cy (+ y 50)]
                  (draw-edge canvas x y cx cy)
                  (draw-tree child canvas width-per-child cx cy)))
              children
              (iterate inc 1)))))
   (draw-node canvas tree x y)))


(defn view-tree [tree]
  (let [sketch-tree (sketch
                      (setup []
                             (doto this
                               (size 400 400)
                               (framerate 1)
                               smooth))
                      (draw []
                            (doto this
                              (background 250))
                            (draw-tree tree this 400)))]
    (view sketch-tree :size [400 400])))

