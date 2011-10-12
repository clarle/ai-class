(ns unit02.graphs)



; Undirected Graph ------------------------------------------------------------
;
; A graph is a map of label -> node.
; Each node has a label and a list of connections.
; Each connection is a map of label -> cost.
;
; Connections between nodes are undirected, and the cost is the same in both
; directions.

(defrecord Node [label connections])

(defn make-graph [labels connections]
  (let [graph (zipmap labels (map #(Node. %1 %2) labels (cycle [{}])))]
    (loop [graph graph
           connections connections]
      (if (empty? connections)
        graph
        (let [[labels cost] (first connections)
              [a b] labels]
          (recur (-> graph
                   (assoc-in [a :connections b] cost)
                   (assoc-in [b :connections a] cost))
                 (rest connections)))))))


; Searches --------------------------------------------------------------------
(defn- path-cost [path graph]
  (loop [prev (first path)
         path (rest path)
         cost 0]
    (if (empty? path)
      cost
      (recur (first path)
             (rest path)
             (+ cost ((:connections (graph prev))
                        (first path)))))))

(defn- new-targets [path current-node explored graph]
  (let [unseen (filter (complement explored)
                       (keys (:connections current-node)))
        new-paths (map #(conj path %) unseen)]
    (map #(with-meta % {:cost (path-cost % graph)})
         new-paths)))


(defn search [frontier-conj graph start goal]
  (loop [explored #{}
         frontier (seq [[start]])
         examined 1]
    (let [path (first frontier)
          current (last path)]
      (if (= current goal)
        {:path path
         :examined examined
         :cost (:cost (meta path))}
        (when current
          (recur (conj explored current)
                 (let [current-node (graph current)]
                   (frontier-conj (rest frontier)
                                  (new-targets path current-node explored graph)))
                 (inc examined)))))))


(defn frontier-conj-breadth [frontier children]
  (concat frontier children))

(defn frontier-conj-depth [frontier children]
  (concat children frontier))


(def search-breadth (partial search frontier-conj-breadth))
(def search-depth (partial search frontier-conj-depth))

; Sample Graph ----------------------------------------------------------------
(def sample-graph-ny
  (make-graph [:buffalo :rochester :syracuse :binghamton :new-york-city]
              {[:buffalo :rochester] 75
               [:syracuse :rochester] 90
               [:ithaca :rochester] 90
               [:ithaca :binghamton] 50
               [:ithaca :syracuse] 60
               [:binghamton :syracuse] 75
               [:binghamton :rochester] 141
               [:binghamton :new-york-city] 176
               [:syracuse :new-york-city] 247
               [:albany :new-york-city] 150
               [:albany :syracuse] 150}))
