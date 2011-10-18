// Stack acts as a wrapper for the Array object, adding empty() and extend()
// Can probably be written in a better fashion, rather than wrapping like this

function Stack() {
    this.array = [];
    this.empty = function() {
        return (this.array.length == 0);
    };
    this.length = function() {
        return this.array.length;
    };
    this.indexOf = function(item) {
        return this.array.indexOf(item);
    };
    this.push = function(item) {
        return this.array.push(item);
    };
    this.pop = function() {
        return this.array.pop();
    };
    this.extend = function(items) {
        for (item in items) { this.push(item); }
    };
    this.toString = function() {
        return this.array.toString();
    };
}

function Queue() { }

Queue.prototype = new Stack();

Queue.prototype.pop = function(item) {
    return this.array.shift(item);
}

function PriorityQueue(order) {
    this.sorted = false;
    this.order = order;
    var sort_lo = function(a, b) { return b.pri - a.pri; }
    var sort_hi = function(a, b) { return a.pri - b.pri; }
    var style;
    if (order.lo) {
        style = sort_lo;
    } else {
        style = sort_hi;
    }

    this.sort = function() {
        this.array.sort(style);
        this.sorted = true;
    }
}

PriorityQueue.prototype = new Stack();

PriorityQueue.prototype.push = function(item) {
    if (!item.pri || !item.obj) { throw "TypeError: Not a valid priority queue object"; }
    this.sorted = false;
    return this.array.push(item);
}   

PriorityQueue.prototype.pop = function() {
    if (!this.sorted) this.sort();
    return this.array.pop();
}
