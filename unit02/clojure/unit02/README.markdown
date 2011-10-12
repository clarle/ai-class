Unit 2 Algorithms in Clojure
============================

Usage
-----

    lein deps
    lein repl

    (require '[unit02.trees :as trees])
    (trees/search-breadth trees/sample-tree-1 :e)
    (trees/search-depth trees/sample-tree-1 :e)
    (trees/search-breadth trees/sample-tree-2 :z)
    (trees/search-depth trees/sample-tree-2 :z)

    (require '[unit02.graphs :as graphs])
    (graphs/search-breadth graphs/sample-graph-ny :rochester :albany)
    (graphs/search-depth graphs/sample-graph-ny :rochester :albany)

    (require '[unit02.viz :as viz])
    (viz/view-tree trees/sample-tree-1)
    (viz/view-tree trees/sample-tree-2)
