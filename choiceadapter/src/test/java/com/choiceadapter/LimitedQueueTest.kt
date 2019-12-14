package com.choiceadapter

import org.junit.Assert
import org.junit.Test

class LimitedQueueTest {

    @Test
    fun testAdd() {
        val queue = LimitedQueue<Int>(maxSize = 3)

        // Case 1: Adding two elements
        queue.add(1)
        queue.add(2)
        Assert.assertArrayEquals(queue.toArray(), arrayOf(1, 2))

        // Case 2: Added third element so list is full now
        queue.add(3)
        Assert.assertArrayEquals(queue.toArray(), arrayOf(1, 2, 3))

        // Case 3: Added one more element so first inserted element popped
        queue.add(4)
        Assert.assertArrayEquals(queue.toArray(), arrayOf(2, 3, 4))
    }

    @Test
    fun testRemove() {
        val queue = LimitedQueue<String>(maxSize = 3)

        queue.add("Elf")
        queue.add("Unicorn")
        queue.add("Wizard")
        Assert.assertArrayEquals(queue.toArray(), arrayOf("Elf", "Unicorn", "Wizard"))

        // Removing an element
        queue.remove("Unicorn")
        Assert.assertArrayEquals(queue.toArray(), arrayOf("Elf", "Wizard"))
    }

    @Test
    fun testContains() {
        val queue = LimitedQueue<String>(maxSize = 4)

        queue.add("Elf")
        queue.add("Unicorn")
        queue.add("Wizard")

        // Contains check
        Assert.assertEquals(queue.contains("Elf"), true)
        Assert.assertEquals(queue.contains("Witch"), false)
    }
}

