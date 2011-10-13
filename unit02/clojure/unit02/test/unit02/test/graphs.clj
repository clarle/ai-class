(ns unit02.test.graphs
  (:use [unit02.graphs])
  (:use [clojure.test]))

(def graph-1 
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

(deftest test-bf-paths
  (is (= (:path (search-breadth graph-1 :buffalo :rochester))
         [:buffalo :rochester])
      "Bad path for breadth-first search.")

  (is (= (:path (search-breadth graph-1 :buffalo :binghamton))
         [:buffalo :rochester :binghamton])
      "Bad path for breadth-first search.")

  (is (= (:path (search-breadth graph-1 :buffalo :albany))
         [:buffalo :rochester :syracuse :albany])
      "Bad path for breadth-first search."))

(deftest test-df-paths
  (is (#{[:buffalo :rochester]}
       (:path (search-depth graph-1 :buffalo :rochester)))
      "Bad path for depth-first search.")

  (is (#{[:binghamton :ithaca]}
         (:path (search-depth graph-1 :binghamton :ithaca)))
      "Bad path for depth-first search.")

  (is (#{[:buffalo :rochester :syracuse :albany]
         [:buffalo :rochester :syracuse :new-york-city :albany]}
         (:path (search-depth graph-1 :buffalo :albany)))
      "Bad path for depth-first search."))
