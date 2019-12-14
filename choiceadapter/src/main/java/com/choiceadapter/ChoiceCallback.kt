package com.choiceadapter

import android.view.View

interface ChoiceCallback {

    /**
     * Call back for choice selection.
     *
     * * @param choice selected choice
     * * @param view selected view
     *
     */
    fun onChoiceSelected(choice: Choice, view: View)

    /**
     * Call back for choice unselected.
     *
     * * @param choice unselected choice
     * * @param view unselected view
     *
     */
    fun onChoiceUnselected(choice: Choice, view: View)

    /**
     * Call back for already selecting specified choices.
     *
     */
    fun alreadySelectedMaxChoices()
}