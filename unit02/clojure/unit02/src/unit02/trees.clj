(ns unit02.trees)


; Tree ------------------------------------------------------------------------
;
; A tree is equivalent to a single node.  A single node is a record:
;
; {:label :foo :children [... nodes ...]}

(defrecord Node [label children])

(defn node [label children]
  (Node. label children))

; Searches --------------------------------------------------------------------
(defn- log [log-ref label data]
  (when log-ref
    (dosync (alter log-ref conj [label data]))))

(defn- search 
  ([frontier-conj tree goal] (search frontier-conj tree goal nil))
  ([frontier-conj tree goal log-ref] 
   ; frontier is a sequence of vectors representing paths we still need to try:
   ; ([Node :a, Node :b], [Node :a, Node :c], ...)
   (loop [frontier (seq [[tree]])
          examined 1]
     (log log-ref :frontier (vec (map (comp :label last) frontier)))
     (let [path (first frontier)
           current (last path)]
       (log log-ref :examine current)
       (if (= (:label current) goal)
         {:path (map :label path)
          :examined examined}
         (recur (frontier-conj (rest frontier)
                               (map #(conj path %) (:children current)))
                (inc examined)))))))


(defn- frontier-conj-depth [frontier children]
  (concat children frontier))

(defn- frontier-conj-breadth [frontier children]
  (concat frontier children))


(def search-depth (partial search frontier-conj-depth))
(def search-breadth (partial search frontier-conj-breadth))

; Sample Trees ----------------------------------------------------------------

(def sample-tree-1 (node :a [(node :b [(node :d [])
                                       (node :e [])])
                             (node :c [(node :f [])
                                       (node :g [])])]))

(def sample-tree-2 (node :a [(node :b [(node :d [])
                                       (node :e [])])
                             (node :q [])
                             (node :c [(node :f [(node :x [])
                                                 (node :y [(node :z [])])])
                                       (node :g [])])]))

(def sample-tree-3 (node :a [(node :b [(node :d [(node :i [])
                                                 (node :j [])])
                                       (node :e [])])
                             (node :q [(node :l [(node :n [(node :o [(node :p [])])])])
                                       (node :m [(node :r [])])])
                             (node :c [(node :f [(node :x [])
                                                 (node :y [(node :z [])])])
                                       (node :g [])])]))
