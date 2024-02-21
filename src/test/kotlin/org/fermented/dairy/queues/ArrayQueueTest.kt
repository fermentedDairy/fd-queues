package org.fermented.dairy.queues

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArrayQueueTest{


    @Test
    fun `offer message, validate depth incremented, poll queue for message`(){
        val arrayQueue = ArrayQueue<Int>(10)
        arrayQueue.offer(12345)
        assertEquals(1, arrayQueue.depth(), "Depth not incremented")

        assertEquals(12345, arrayQueue.peek(), "Peeked value incorrect")
        assertEquals(12345, arrayQueue.poll(), "Polled value incorrect")
        assertEquals(0, arrayQueue.depth(), "Depth not decremented")
    }

    @Test
    fun `offer muliple messages, validate depth incremented, poll queue for messages`(){
        val arrayQueue = ArrayQueue<Int>(10)
        for (i in 1 .. 100) {
            arrayQueue.offer(i)
            assertEquals(i.toLong(), arrayQueue.depth(), "Depth not incremented")

            assertEquals(1, arrayQueue.peek())
        }

        var toRead = 1
        var depth = 100
        while(arrayQueue.depth() > 0) {
            assertEquals(toRead, arrayQueue.peek(), "Peeked value incorrect")
            assertEquals(toRead, arrayQueue.poll(), "Polled value incorrect")
            toRead++
            depth--
            assertEquals(depth.toLong(), arrayQueue.depth(), "Depth not incremented")
        }
    }
}