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

#ifndef TreeSearch_H_
#define TreeSearch_H_

#include <list>
#include <set>
#include <exception>

#include "Tree.h"
#include "algorithms/Algorithms.h"

template <typename TreeType, typename SearchAlgorithm/* = BreadthFirstSearch<TreeType>*/ >
class TreeSearch
{
  public:
    typedef std::list< Path<TreeType>* > PathList;
    typedef typename TreeType::value_type::value_type GoalType;


    PathList _frontier;
    std::set<typename TreeType::value_type*> _explored;
    typename TreeType::value_type* TreeRoot;
    PathList LPath;

    TreeSearch(typename TreeType::value_type* mNode) : TreeRoot( mNode ) { _frontier.push_back( new Path<TreeType>(TreeRoot) ); }
    Path<TreeType>* find(GoalType Goal)
    {
    /*
     Notes: A compare function must be specified to the set container.

     Pseudocode:
     Function TreeSearch(problem):
        frontier = {[initial]}
        loop:
            if frontier is empty: FAIL
            path = remove_choice(frontier)
            s = path.end
            if s is a goal: return path
            for a in actions:
                add [path + a -> Result(s, a)] to frontier
     */

        Path<TreeType>* path;
        typename TreeType::value_type* Node;
// Tries using SearchAlgorithm as holder of the actual algorithm implementation.
//        SearchAlgorithm sa();
//        return sa.find(Goal);
        while(true)
        {
            if(_frontier.empty()) throw( std::exception() );
            path = _frontier.front();
            _frontier.remove(path);
            Node = path->_path.back();
            _explored.insert(Node);
            if (Node->Data == Goal) return path;

       // Add the checked Nodes children to a copy of the present path
       // and add this path to _frontier.
            if( Node->has_children() != 0 )
            {
                 for(int i = 0; i < Node->child_number; ++i)
                 {
                // Check if the child to be attached has been explored already
                     if( _explored.find(Node->child[i]) == _explored.end() )
                     {
                         Path<TreeType>* newpath = new Path<TreeType>(*path);
                         newpath->_path.push_back(Node->child[i]);
                         _frontier.push_back(newpath);
                     }
                 }
                _frontier.sort();     
            }
        }
    }
};

#endif

