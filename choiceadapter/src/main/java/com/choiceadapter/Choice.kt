package com.choiceadapter

/**
 * Model class which we are passing to ChoiceAdapter should implement this interface.
 *
 */
interface Choice {

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}