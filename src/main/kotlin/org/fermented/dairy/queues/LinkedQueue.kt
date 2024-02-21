package org.fermented.dairy.queues

/**
 * Doubly linked list queue implementation
 */
class LinkedQueue<T> : Queue<T> {

    private var depth: Long = 0

    private var next: LinkedQueueNode<T>? = null

    private var last: LinkedQueueNode<T>? = null

    override fun offer(offered: T): Boolean { //Always O(1)
        val node: LinkedQueueNode<T> = LinkedQueueNode(offered)
        node.next = last
        if(next == null) {
            next = node
        }
        last?.previous = node
        last = node
        depth++
        return true
    }

    override fun poll(): T? {
        val value = next?.value
        next = next?.previous
        next?.next = null

        if(depth > 0)
            depth--

        return value
    }

    override fun peek(): T? {
        return next?.value
    }

    override fun depth(): Long {
        return depth
    }

    class LinkedQueueNode<T>(val value: T) {
        var previous: LinkedQueueNode<T>? = null
        var next: LinkedQueueNode<T>? = null
    }
}