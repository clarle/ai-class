Unit 2 Algorithms in Clojure
============================

Usage
-----

    lein deps
    lein test

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

    (viz/search-tree trees/sample-tree-1 trees/search-depth :e)
    (viz/search-tree trees/sample-tree-1 trees/search-breadth :e)

    (viz/search-tree trees/sample-tree-3 trees/search-depth :n)
    (viz/search-tree trees/sample-tree-3 trees/search-breadth :n)
