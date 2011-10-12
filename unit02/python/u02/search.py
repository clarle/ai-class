# To implement:
# Frontiers for DFS, UCS, A*

import heapq as hq


def treeSearch(pArray, goalState, frontier, debug=True):
    """ TreeSearch algorithm - returns path as list of visited states
        pArray -- numpy array - connection matrix
        goalState -- self explanatory ;)
        frontier -- one of implementations of frontiers
                    defines which kind of search will be performed
        debug -- show/don't show visited paths
    """
    pathCounter = 0
    while True:
#        print 'frontier: ', frontier
        if not frontier:
            return None
        
        path = frontier.pop()
        pathCounter += 1
        if debug:
            print 'path: ', path
        state = path[-1]
        
        if state == goalState:
            print 'Unfolded paths: ', pathCounter
            return path
        
        for i in range(len(pArray)):
            if pArray[state, i] > 0:
                nPath = list(path)
                nPath.append(i)
                frontier.push(nPath)
                

def graphSearch(pArray, goalState, frontier, debug=True):
    """ GraphSearch algorithm - returns path as list of visited states
        pArray -- numpy array - connection matrix
        goalState -- self explanatory ;)
        frontier -- one of implementations of frontiers
                    defines which kind of search will be performed
        debug -- show/don't show visited paths
    """
    pathCounter = 0
    explored = []
    while True:
#        print 'frontier: ', frontier
        if not frontier:
            return None
        
        path = frontier.pop()
        pathCounter += 1
        if debug:
            print 'path: ', path
        state = path[-1]
        explored.append(state)
        
        if state == goalState:
            print 'Unfolded paths: ', pathCounter
            return path
        
        for i in range(len(pArray)):
            if pArray[state, i] > 0 and i not in explored:
                nPath = list(path)
                nPath.append(i)
                frontier.push(nPath)
        

class BFSFrontier:
    """ Frontier for BFS - priority queue favorizing shorter paths """
    storage = []
    
    def __init__(self, initState):
        hq.heappush(self.storage, (1, [initState]))
        
    def push(self, path):
        hq.heappush(self.storage, (len(path), path))
        
    def pop(self):
        return hq.heappop(self.storage)[1]
    
    def __repr__(self):
        return str(self.storage)
    
    def ___str___(self):
        return str(self.storage)
    