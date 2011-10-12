(ns unit02.trees)


; Tree ------------------------------------------------------------------------
;
; A tree is equivalent to a single node.  A single node is a record:
;
; {:label :foo :children [... nodes ...]}

(defrecord Node [label children])

; Searches --------------------------------------------------------------------
(defn- search [frontier-conj tree goal]
  ; frontier is a sequence of vectors representing paths we still need to try:
  ; ([Node :a, Node :b], [Node :a, Node :c], ...)
  (loop [frontier (seq [[tree]])
         examined 1]
    (let [path (first frontier)
          current (last path)]
      (if (= (:label current) goal)
        {:path (map :label path)
         :examined examined}
        (recur (frontier-conj (rest frontier)
                              (map #(conj path %) (:children current)))
               (inc examined))))))


(defn- frontier-conj-depth [frontier children]
  (concat children frontier))

(defn- frontier-conj-breadth [frontier children]
  (concat frontier children))


(def search-depth (partial search frontier-conj-depth))
(def search-breadth (partial search frontier-conj-breadth))

; Sample Tree -----------------------------------------------------------------
;
;     a
;  b     c
; d e   f g

(def sample-tree (Node. :a [(Node. :b [(Node. :d [])
                                       (Node. :e [])])
                            (Node. :c [(Node. :f [])
                                       (Node. :g [])])]))
