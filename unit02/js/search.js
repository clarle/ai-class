// To implement:
// BFS, DFS, UCS, A*

function tree_search(graph, source, dest, frontier) {
    frontier.push(source);
    var node;
    while (!frontier.empty()) {
        node = frontier.pop();
        if (node == dest) {
            return node;
        }
        frontier.expand(graph[node]);
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

function bf_search(graph, source, dest) {

}

function df_search(graph, source, dest) {

}

function uc_search(graph, source, dest) {

}

function a_star(graph, source, dest) {

}
