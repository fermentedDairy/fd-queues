package org.fermented.dairy.queues

interface Queue<T> {
    fun offer(offered: T): Boolean
    fun poll(): T?
    fun peek(): T?
    fun depth(): Long
}