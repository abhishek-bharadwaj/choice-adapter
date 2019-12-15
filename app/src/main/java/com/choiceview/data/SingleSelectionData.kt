package com.choiceview.data

import com.choiceadapter.Choice

class SingleSelectionData(val id: Int) : Choice {

    override fun equals(other: Any?): Boolean {
        if (other !is SingleSelectionData) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "Option: $id"
    }
}