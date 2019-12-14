package com.choiceview

import java.util.*

class LimitedArrayDeque<T>(private val maxSize: Int) {

    private val queue = ArrayDeque<T>(maxSize)

    fun add(t: T) {
        if (queue.size == maxSize) {
            queue.removeFirst()
        }
        queue.add(t)
    }

    fun remove(t: T) = queue.remove(t)

    fun contains(t: T) = queue.contains(t)

    override fun toString(): String {
        return queue.toString()
    }
}