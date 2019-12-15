package com.choiceview.fragments

import androidx.fragment.app.Fragment

abstract class BaseSelectionFragment : Fragment() {

    abstract fun getSelectedChoices(): Array<Any>
}