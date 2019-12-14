package com.choiceview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.choiceadapter.Choice
import com.choiceadapter.ChoiceAdapter
import com.choiceadapter.ChoiceCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_test_choice.view.*

class MainActivity : AppCompatActivity(), ChoiceCallback {

    private lateinit var choiceAdapter: ChoiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = mutableListOf<Option>()
        for (i in 0..10) {
            options.add(Option(i))
        }

        choiceAdapter = ChoiceAdapter(this, R.layout.layout_test_choice, this, 2, flexible = false)
        choiceAdapter.updateData(options)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = choiceAdapter
    }

    override fun onChoiceSelected(choice: Choice, view: View) {
        updateItemUI(choice, view, android.R.color.holo_blue_light, true)
    }

    override fun onChoiceUnselected(choice: Choice, view: View) {
        updateItemUI(choice, view, android.R.color.holo_green_light, false)
    }

    override fun alreadySelectedMaxChoices() {
        Toast.makeText(this, getString(R.string.max_choices_selected), Toast.LENGTH_SHORT).show()
    }

    private fun updateItemUI(choice: Choice, view: View, bgColor: Int, shouldCheck: Boolean) {
        view.tv.text = choice.toString()
        view.container.setCardBackgroundColor(ContextCompat.getColor(this@MainActivity, bgColor))
        view.rb.apply {
            isChecked = shouldCheck
            setTag(R.id.choice_data, choice)
        }
    }
}