package com.choiceview

interface Choice {
    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}