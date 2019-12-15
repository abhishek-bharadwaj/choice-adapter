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
import com.choiceview.data.Option
import kotlinx.android.synthetic.main.fragment_single_selection.*
import kotlinx.android.synthetic.main.layout_test_choice.view.*

class SingleSelectionFragment : BaseSelectionFragment(), ChoiceCallback {

    private lateinit var choiceAdapter: ChoiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_single_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = mutableListOf<Option>()
        for (i in 0..10) {
            options.add(Option(i))
        }

        choiceAdapter = ChoiceAdapter(
            requireContext(), R.layout.layout_test_choice, this, 1
        )
        choiceAdapter.updateData(options)
        rv_single_select.layoutManager = LinearLayoutManager(requireContext())
        rv_single_select.adapter = choiceAdapter
    }

    override fun onChoiceSelected(choice: Choice, view: View) {
        updateItemUI(choice, view, android.R.color.holo_blue_light, true)
    }

    override fun onChoiceUnselected(choice: Choice, view: View) {
        updateItemUI(choice, view, android.R.color.holo_green_light, false)
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

    private fun updateItemUI(choice: Choice, view: View, bgColor: Int, shouldCheck: Boolean) {
        view.tv.text = choice.toString()
        view.container.setCardBackgroundColor(ContextCompat.getColor(requireContext(), bgColor))
        view.rb.apply {
            isChecked = shouldCheck
            setTag(R.id.choice_data, choice)
        }
    }
}