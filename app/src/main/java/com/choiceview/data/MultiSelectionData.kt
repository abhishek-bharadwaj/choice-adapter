package com.choiceview.data

import com.choiceadapter.Choice

class MultiSelectionData(private val id: Int, private val text: String) : Choice {

    override fun equals(other: Any?): Boolean {
        if (other !is MultiSelectionData) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id + text.hashCode()
    }

    override fun toString(): String {
        return "$id. $text"
    }
}