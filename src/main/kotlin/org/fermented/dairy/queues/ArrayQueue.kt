package org.fermented.dairy.queues

const val GROWTH_FACTOR = 0.75

/**
 * Unbounded ("in theory") array backed queue implementation
 */
class ArrayQueue<T>(initialCapacity: Int) : Queue<T>  {


    private var queueArray: Array<T?> = arrayOfNulls<Any?>(initialCapacity) as Array<T?>

    private var nextIndex = 0
    private var lastIndex = 0


    override fun offer(offered: T): Boolean { //O(n) if shift or new array allocation required, O(1) otherwise
        if (lastIndex == queueArray.size - 1) {
            if (nextIndex > 0) {
                shiftToStartOfArray() //O(n)
            } else {
                allocateNewArray() //O(n)
            }
        }
        lastIndex++
        queueArray[lastIndex] = offered
        return true
    }

    private fun allocateNewArray() {
        val newArray: Array<T?> = arrayOfNulls<Any?>((queueArray.size * (1 + GROWTH_FACTOR)).toInt())
                as Array<T?>
        for (index in queueArray.indices) {
            newArray[index] = queueArray[index]
        }
    }

    private fun shiftToStartOfArray() {
        for (index in nextIndex .. lastIndex)

    }

    override fun poll(): T? {
        val value = queueArray[nextIndex]
        queueArray[nextIndex] = null //allow value memory freeing
        if (value != null) //Not an open slot
            nextIndex++
        return value
    }

    override fun peek(): T? {
        return queueArray[nextIndex]
    }

    override fun depth(): Long {
        return (lastIndex - nextIndex).toLong()
    }
}