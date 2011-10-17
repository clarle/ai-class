(ns unit02.viz
  (:use (incanter core processing))
  (:use [unit02.trees :only (node)]))



(defn- set-node-fill-color [canvas node state]
  (if (some #{(:label node)} (:frontier @state))
    (if (= node (:current @state))
      (doto canvas (fill 226 0 55))   ; Red if it's current.
      (doto canvas (fill 0 121 184))) ; Otherwise blue if it's in the frontier.
    (doto canvas (fill 150))))        ; Otherwise grey.

(defn- draw-node-circle [canvas node state x y]
  (set-node-fill-color canvas node state)
  (doto canvas
    (stroke 10)
    (stroke-weight 2)
    (ellipse x y 20 20)))

(defn- draw-node-text [canvas node x y]
  (doto canvas
    (fill 250)
    (text-align CENTER)
    (text (str (:label node)) x (+ y 5))))


(defn- draw-node [canvas node state x y]
  (draw-node-circle canvas node state x y)
  (draw-node-text canvas node x y))

(defn- draw-edge [canvas ax ay bx by]
  (doto canvas
    (stroke-weight 2)
    (stroke 10)
    (line ax ay bx by)))


(defn- draw-child [canvas state width-per-child width x y child i]
  (let [cxs-start (- x (/ width 2))                ; where this node's childrens' space starts  [          __________]
        cx-end (+ cxs-start (* width-per-child i)) ; where this child's space ends              [          _____.....]
        cx (- cx-end (/ width-per-child 2))        ; put the child into the center of its space [          _____..c..]
        cy (+ y 50)]
    (draw-edge canvas x y cx cy)
    (draw-tree child canvas state width-per-child cx cy)))

(defn- draw-tree
  ([tree canvas state width] (draw-tree tree canvas state width (/ width 2) 20))
  ([tree canvas state width x y]
   (when (seq (:children tree))
     (let [children (:children tree)
           width-per-child (/ width (count children))]
       (dorun
         (map (partial draw-child canvas state width-per-child width x y) 
              children
              (iterate inc 1)))))
   (draw-node canvas tree state x y)))


(defn- update-state [state [label data]]
  (case label
    :examine (assoc state
                    :current data
                    :count (inc (:count state)))
    :frontier (assoc state
                     :frontier data
                     :current nil)
    state))


(defn- render-search-tree
  ([tree] (view-tree tree log))
  ([tree log]
   (let [log (ref (seq log))
         state (ref {:frontier []
                     :count 0
                     :current nil})
         sketch-tree (sketch
                       (setup []
                              (doto this
                                (size 400 400)
                                (framerate 2)
                                (text-font (create-font this "Helvetica Neue" 12))
                                smooth))
                       (draw []
                             (dosync
                               (alter state update-state (first @log))
                               (alter log rest))
                             (doto this
                               (background 250)
                               (text-align LEFT)
                               (stroke-weight 0)
                               (fill 10)
                               (text (str "Examined: " (:count @state)) 5 15))
                             (draw-tree tree this state 400)))]
     (view sketch-tree :size [400 400]))))


(defn search-tree [tree search-fn goal]
  (let [log (ref [])]
    (search-fn tree goal log)
    (render-search-tree tree @log)))
