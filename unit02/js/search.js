// To implement:
// BFS, DFS, UCS, A*

function tree_search(tree, source, dest, frontier) {
    frontier.push(source);
    var node;
    while (!frontier.empty()) {
        node = frontier.pop();
        if (node == dest) {
            return node;
        }
        frontier.expand(tree[node]);
    return null;
}

function graph_search(graph, source, dest, frontier) {
    frontier.push(source);
    var explored = [];
    var node;
    while (!frontier.empty()) {
        node = frontier.pop();
        if (node == dest) {
            return node;
        }
        explored.push(node);
        var successors = [];
        for (neighbor in graph[node]) {
            if (!frontier.indexOf(neighbor) && !explored.indexOf(neighbor)) {
                successors.push(neighbor);
            }
        }
        frontier.expand(successors);
    }
    return null;     
}

function bf_tree_search(tree, source, dest) {
    var queue = new Queue();
    return tree_search(tree, source, dest, queue);
}

function df_tree_search(tree, source, dest) {
    var stack = new Stack();
    return tree_search(tree, source, dest, stack);
}

function bf_graph_search(graph, source, dest) {
    var queue = new Queue();
    return graph_search(graph, source, dest, queue);
}

function df_graph_search(graph, source, dest) {
    var stack = new Stack();
    return graph_search(graph, source, dest, stack);
}

function uc_graph_search(graph, source, dest) {
    var p_queue = new PriorityQueue();
    return graph_search(graph, source, dest, p_queue);
}

function a_star(graph, source, dest) {

}
