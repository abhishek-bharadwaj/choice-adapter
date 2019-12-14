package com.choiceview

import com.choiceadapter.Choice

class Option(val id: Int) : Choice {

    override fun equals(other: Any?): Boolean {
        if (other !is Option) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "Option: $id"
    }
}