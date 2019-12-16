package com.choiceview.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.choiceadapter.Choice
import com.choiceadapter.ChoiceAdapter
import com.choiceadapter.ChoiceCallback
import com.choiceview.R
import com.choiceview.data.MultiSelectionData
import kotlinx.android.synthetic.main.fragment_single_selection.*
import kotlinx.android.synthetic.main.item_single_choice.view.*

class SingleSelectionFragment : BaseSelectionFragment(), ChoiceCallback {

    private lateinit var choiceAdapter: ChoiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_single_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = mutableListOf<MultiSelectionData>()
        val randomTexts = resources.getStringArray(R.array.random_multi_select_texts)
        for (i in randomTexts.indices) {
            options.add(MultiSelectionData(i, randomTexts[i]))
        }

        choiceAdapter = ChoiceAdapter(
            context = requireContext(), layoutRes = R.layout.item_single_choice,
            choiceCallback = this, minSelection = 1, flexible = false
        )
        choiceAdapter.updateData(options)
        rv_single_select.layoutManager = LinearLayoutManager(requireContext())
        rv_single_select.adapter = choiceAdapter
    }

    override fun onChoiceSelected(choice: Choice, view: View) {
        updateItemUI(choice, view, R.color.single_select_2, android.R.color.white, true)
    }

    override fun onChoiceUnselected(choice: Choice, view: View) {
        updateItemUI(choice, view, R.color.single_select_1, android.R.color.black, false)
    }

    override fun alreadySelectedMaxChoices() {
        Toast.makeText(
            requireContext(),
            getString(R.string.max_choices_selected),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun getSelectedChoices(): Array<Any> {
        return choiceAdapter.getSelectedChoices()
    }

    private fun updateItemUI(
        choice: Choice, view: View, bgColor: Int,
        textColor: Int, shouldCheck: Boolean
    ) {
        view.tv.apply {
            text = choice.toString()
            setTextColor(ContextCompat.getColor(requireContext(), textColor))
        }
        view.container.setCardBackgroundColor(ContextCompat.getColor(requireContext(), bgColor))
        view.rb.apply {
            isChecked = shouldCheck
            setTag(R.id.choice_data, choice)
        }
    }
}