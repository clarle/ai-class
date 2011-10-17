/*
 * The code contained in this file is licensed as Public Domain.
 */

/*
 * Example of usage of headers Tree.h and TreeSearch.h
 */

#include <string>

#include "Tree.h"
#include "TreeSearch.h"

/*
 * This class represents the information stored in a node of the tree.
 * It may contain lots of information but must implement an equality
 * operator (operator==) to detect when the goal is achieved.
 */
class Info
{
  public:
    std::string Id;
    int Count;

    Info() { }
    Info(std::string Id, int Count) : Id(Id), Count(Count) { }

  // operator== is a must (used for goal)
    bool operator==(const Info &other) const 
    {
        if(this->Id == other.Id) return true;
        return false;
    }
};

// Helpers for less mistakes and easier reading.
typedef TreeNode<Info, 2> myNodeType;
typedef Tree<myNodeType> myTreeType;

int main()
{
  // Build the tree  
    myTreeType* mTree = new myTreeType();
    mTree->setRoot( new myNodeType( Info("Root", 1) ) );
    myNodeType* mNode = mTree->getRootPtr();
    mNode->child[0] = new myNodeType( Info("ChildOne", 1) );
    mTree->addNode(mNode->child[0]);
 
  // Set the starting point for a search.
    TreeSearch<myTreeType, BreadthFirstSearch<myTreeType> >* mSearch = new TreeSearch<myTreeType, BreadthFirstSearch<myTreeType> >( mTree->getRootPtr() );

  /*
   * mPath is guaranteed to be the shortest path between the starting point and
   * the goal point.
   */
    Path<myTreeType>* mPath = mSearch->find( Info("ChildOne", 1) );

    return 0;
}
