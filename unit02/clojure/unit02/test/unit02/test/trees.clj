(ns unit02.test.trees
  (:use [unit02.trees])
  (:use [clojure.test]))


(def tree-1 (node :a [(node :b [(node :d [])
                                (node :e [])])
                      (node :c [(node :f [])
                                (node :g [])])]))

(def tree-2 (node :a [(node :b [(node :d [])
                                (node :e [])])
                      (node :q [])
                      (node :c [(node :f [(node :x [])
                                          (node :y [(node :z [])])])
                                (node :g [])])]))


(deftest test-bf-binary-paths
  (is (= (:path (search-breadth tree-1 :a))
         [:a])
      "Breadth-first search didn't find the path to the root node")

  (is (= (:path (search-breadth tree-1 :b))
         [:a :b])
      "Breadth-first search didn't find the path to a child node")

  (is (= (:path (search-breadth tree-1 :c))
         [:a :c])
      "Breadth-first search didn't find the path to a child node")

  (is (= (:path (search-breadth tree-1 :d))
         [:a :b :d])
      "Breadth-first search didn't find the path to a leaf node")
  
  (is (= (:path (search-breadth tree-1 :g))
         [:a :c :g])
      "Breadth-first search didn't find the path to a leaf node"))

(deftest test-df-binary-paths
  (is (= (:path (search-depth tree-1 :a))
         [:a])
      "Depth-first search didn't find the path to the root node")

  (is (= (:path (search-depth tree-1 :b))
         [:a :b])
      "Depth-first search didn't find the path to a child node")

  (is (= (:path (search-depth tree-1 :c))
         [:a :c])
      "Depth-first search didn't find the path to a child node")

  (is (= (:path (search-depth tree-1 :d))
         [:a :b :d])
      "Depth-first search didn't find the path to a leaf node")
  
  (is (= (:path (search-depth tree-1 :g))
         [:a :c :g])
      "Depth-first search didn't find the path to a leaf node"))

(deftest test-bf-nonbinary-paths
  (is (= (:path (search-breadth tree-2 :a))
         [:a])
      "Breadth-first search didn't find the path to the root node")

  (is (= (:path (search-breadth tree-2 :q))
         [:a :q])
      "Breadth-first search didn't find the path to a leaf node")

  (is (= (:path (search-breadth tree-2 :c))
         [:a :c])
      "Breadth-first search didn't find the path to a child node")

  (is (= (:path (search-breadth tree-2 :y))
         [:a :c :f :y])
      "Breadth-first search didn't find the path to a child node")
  
  (is (= (:path (search-breadth tree-2 :z))
         [:a :c :f :y :z])
      "Breadth-first search didn't find the path to a leaf node"))

(deftest test-df-nonbinary-paths
  (is (= (:path (search-depth tree-2 :a))
         [:a])
      "Depth-first search didn't find the path to the root node")

  (is (= (:path (search-depth tree-2 :q))
         [:a :q])
      "Depth-first search didn't find the path to a child node")

  (is (= (:path (search-depth tree-2 :c))
         [:a :c])
      "Depth-first search didn't find the path to a child node")

  (is (= (:path (search-depth tree-2 :y))
         [:a :c :f :y])
      "Depth-first search didn't find the path to a leaf node")
  
  (is (= (:path (search-depth tree-2 :z))
         [:a :c :f :y :z])
      "Depth-first search didn't find the path to a leaf node"))
(deftest test-bf-binary-costs
  (is (= (:examined (search-breadth tree-1 :a)) 1)
      "Breadth-first search didn't examine the correct number of nodes to find the root node")

  (is (= (:examined (search-breadth tree-1 :b)) 2)
      "Breadth-first search didn't examine the correct number of nodes to find a child node")

  (is (= (:examined (search-breadth tree-1 :c)) 3)
      "Breadth-first search didn't examine the correct number of nodes to find a child node")

  (is (= (:examined (search-breadth tree-1 :d)) 4)
      "Breadth-first search didn't examine the correct number of nodes to find a leaf node")
  
  (is (= (:examined (search-breadth tree-1 :g)) 7)
      "Breadth-first search didn't examine the correct number of nodes to find a leaf node"))

(deftest test-df-binary-costs
  (is (= (:examined (search-depth tree-1 :a)) 1)
      "Depth-first search didn't examine the correct number of nodes to find the root node")

  (is (= (:examined (search-depth tree-1 :b)) 2)
      "Depth-first search didn't examine the correct number of nodes to find a child node")

  (is (= (:examined (search-depth tree-1 :c)) 5)
      "Depth-first search didn't examine the correct number of nodes to find a child node")

  (is (= (:examined (search-depth tree-1 :d)) 3)
      "Depth-first search didn't examine the correct number of nodes to find a leaf node")
  
  (is (= (:examined (search-depth tree-1 :g)) 7)
      "Depth-first search didn't examine the correct number of nodes to find a leaf node"))

(deftest test-bf-nonbinary-costs
  (is (= (:examined (search-breadth tree-2 :a)) 1)
      "Breadth-first search didn't examine the correct number of nodes to find the root node")

  (is (= (:examined (search-breadth tree-2 :q)) 3)
      "Breadth-first search didn't examine the correct number of nodes to find a leaf node")

  (is (= (:examined (search-breadth tree-2 :c)) 4)
      "Breadth-first search didn't examine the correct number of nodes to find a child node")

  (is (= (:examined (search-breadth tree-2 :y)) 10)
      "Breadth-first search didn't examine the correct number of nodes to find a child node")
  
  (is (= (:examined (search-breadth tree-2 :z)) 11)
      "Breadth-first search didn't examine the correct number of nodes to find a leaf node"))

(deftest test-df-nonbinary-costs
  (is (= (:examined (search-depth tree-2 :a)) 1)
      "Depth-first search didn't examine the correct number of nodes to find the root node")

  (is (= (:examined (search-depth tree-2 :q)) 5)
      "Depth-first search didn't examine the correct number of nodes to find a child node")

  (is (= (:examined (search-depth tree-2 :c)) 6)
      "Depth-first search didn't examine the correct number of nodes to find a child node")

  (is (= (:examined (search-depth tree-2 :y)) 9)
      "Depth-first search didn't examine the correct number of nodes to find a leaf node")
  
  (is (= (:examined (search-depth tree-2 :z)) 10)
      "Depth-first search didn't examine the correct number of nodes to find a leaf node"))
