package com.choiceview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.choiceadapter.Choice
import com.choiceadapter.ChoiceAdapter
import com.choiceadapter.ChoiceCallback
import com.choiceview.R
import com.choiceview.data.MultiSelectionData
import kotlinx.android.synthetic.main.fragment_multi_selection.*
import kotlinx.android.synthetic.main.item_multi_choice.view.*

class MultiSelectionFragment : BaseSelectionFragment(), ChoiceCallback {

    private lateinit var choiceAdapter: ChoiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_multi_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = mutableListOf<MultiSelectionData>()
        val randomTexts = resources.getStringArray(R.array.random_multi_select_texts)
        for (i in randomTexts.indices) {
            options.add(MultiSelectionData(i, randomTexts[i]))
        }

        choiceAdapter = ChoiceAdapter(
            requireContext(), R.layout.item_multi_choice, this, options.size
        )
        choiceAdapter.updateData(options)
        rv_multi_select.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rv_multi_select.adapter = choiceAdapter
    }

    override fun onChoiceSelected(choice: Choice, view: View) {
        updateItemUI(choice, view, android.R.color.holo_purple)
    }

    override fun onChoiceUnselected(choice: Choice, view: View) {
        updateItemUI(choice, view, android.R.color.white)
    }

    override fun alreadySelectedMaxChoices() {

    }

    override fun getSelectedChoices(): Array<Any> {
        return choiceAdapter.getSelectedChoices()
    }

    private fun updateItemUI(choice: Choice, view: View, bgColor: Int) {
        view.tv.text = choice.toString()
        view.container.setCardBackgroundColor(ContextCompat.getColor(requireContext(), bgColor))
    }
}