/*
 * The MIT License (MIT)
 * Copyright (c) 2011 Miguel Bernabeu Diaz <miguel.bernadi[AT]gmail[DOT]com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

#ifndef Tree_H_
#define Tree_H_

#include <list>

/**
 * Class representing a Binary Tree.
 */
template<typename TreeNode>
class Tree
{
  public:
    typedef TreeNode value_type; ///< The type of the nodes.
    typedef std::list< value_type* > TreeNodeList;

    Tree() : _rootNode(0) { }
    ~Tree() { delete _NodeList; }
    value_type* getRootPtr() { return _rootNode; }
    void setRoot(value_type* rootNode) { _rootNode = rootNode; _NodeList.push_back( _rootNode ); }
    void addNode(value_type* Node) { _NodeList.push_back(Node); }

  private:
    TreeNodeList _NodeList;
    value_type* _rootNode;

};

/**
 * Class representing a Node of the Tree.
 * @param _Data is the information structure stored in the node.
 */
template<typename _Data, int nChild = 2>
class TreeNode
{
  private:

  public:
    typedef _Data value_type;
    int child_number;

    /// Node contained information.
    value_type Data;
    /// Pointer to parent node
    TreeNode<_Data>* parent;
    /// Children of the Node
    TreeNode<_Data>* child[nChild];

    TreeNode(value_type Data) : child_number(nChild), Data(Data), parent(0) { }
    ~TreeNode() { for(int i = 0; i < child_number; ++i) delete child[i]; }

    int has_children() { int childs = 0; for(int i = 0; i < child_number; ++i) { if(child[i] != 0) ++childs; } return childs;  }

};// end of BinaryTreeNode definition

#endif

