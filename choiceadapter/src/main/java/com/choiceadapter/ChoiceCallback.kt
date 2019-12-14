package com.choiceadapter

import android.view.View

interface ChoiceCallback {
    fun onChoiceSelected(choice: com.choiceadapter.Choice, view: View)

    fun onChoiceUnSelected(choice: com.choiceadapter.Choice, view: View)
}