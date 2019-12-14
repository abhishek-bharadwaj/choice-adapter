package com.choiceadapter

import java.util.*

class LimitedQueue<T>(private val maxSize: Int) {

    private val queue = ArrayDeque<T>(maxSize)

    /**
     * Inserts the specified element at the end of this queue.
     *
     *  <p>In case queue is full it will pop out the first element and push new element.
     *
     * @param t the element to add
     * @return {@code true}
     * @throws NullPointerException if the specified element is null
     */
    fun add(t: T): Boolean {
        if (queue.size == maxSize) {
            queue.removeFirst()
        }
        return queue.add(t)
    }

    /**
     * Removes a single instance (ie. first occurrence) of the specified element from this queue.
     * If the queue does not contain the element, it is unchanged.
     * Returns {@code true} if this queue contained the specified element
     * (or equivalently, if this queue changed as a result of the call).
     *
     * @param t element to be removed from this queue, if present
     * @return {@code true} if this queue contained the specified element
     */
    fun remove(t: T) = queue.remove(t)

    /**
     * Returns {@code true} if this queue contains the specified element.
     * More formally, returns {@code true} if and only if this queue contains
     * at least one element {@code e} such that {@code o.equals(e)}.
     *
     * @param t object to be checked for containment in this queue
     * @return {@code true} if this queue contains the specified element
     */
    fun contains(t: T) = queue.contains(t)

    /**
     * Returns first index of [t], or -1 if the collection does not contain element.
     *
     * * @param t object to be checked for containment in this queue
     * * @return {@code int}
     */
    fun indexOf(t: T) = queue.indexOf(t)

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    fun size() = queue.size

    /**
     * Returns an array containing all of the elements in this queue
     * in proper sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this queue.  (In other words, this method must allocate
     * a new array).  The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this queue
     */
    fun toArray(): Array<Any> = queue.toArray()

    /**
     * Returns a string representation of this queue.  The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings as
     * by {@link String#valueOf(Object)}.
     *
     * @return a string representation of this collection
     */
    override fun toString(): String {
        return queue.toString()
    }
}