# Unit testing for graph algorithms
import numpy as np
from u02.search import treeSearch, BFSFrontier, graphSearch

def generateArray(nEdge):
    """ return connection matrix for board of size nEdge*nEdge """
    oArray = np.zeros([nEdge**2, nEdge**2])
    
    for i in range(nEdge**2):
        for j in range(nEdge**2):
            if j == i+nEdge or j == i-nEdge or j == i+1 and i % nEdge != nEdge-1 or j == i-1 and i % nEdge != 0: # sweet...
                oArray[i, j] = 1
                
    return oArray

def BFSTreeTest(goalState):
    oArray = generateArray(4)
    print oArray
    print treeSearch(oArray, goalState, BFSFrontier(0))
    
    
def BFSGraphTest(goalState):
    oArray = generateArray(4)
    print oArray
    print graphSearch(oArray, goalState, BFSFrontier(0))