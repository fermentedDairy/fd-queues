package org.fermented.dairy.queues

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LinkedQueueTest {

    @Test
    fun `offer message, validate depth incremented, poll queue for message`(){
        val linkedQueue = LinkedQueue<Int>()
        linkedQueue.offer(12345)
        assertEquals(1, linkedQueue.depth(), "Depth not incremented")

        assertEquals(12345, linkedQueue.peek(), "Peeked value incorrect")
        assertEquals(12345, linkedQueue.poll(), "Polled value incorrect")
        assertEquals(0, linkedQueue.depth(), "Depth not decremented")
    }

    @Test
    fun `offer muliple messages, validate depth incremented, poll queue for messages`(){
        val linkedQueue: LinkedQueue<Int> = LinkedQueue()
        for (i in 1 .. 100) {
            linkedQueue.offer(i)
            assertEquals(i.toLong(), linkedQueue.depth(), "Depth not incremented")

            assertEquals(1, linkedQueue.peek())
        }

        var toRead = 1
        var depth = 100
        while(linkedQueue.depth() > 0) {
            assertEquals(toRead, linkedQueue.peek() , "Peeked value incorrect")
            assertEquals(toRead, linkedQueue.poll() , "Polled value incorrect")
            toRead++
            depth--
            assertEquals(depth.toLong(), linkedQueue.depth(), "Depth not incremented")
        }
    }
}