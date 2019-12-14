package com.choiceadapter

import android.view.View

interface ChoiceCallback {
    fun onChoiceSelected(choice: Choice, view: View)

    fun onChoiceUnSelected(choice: Choice, view: View)
}