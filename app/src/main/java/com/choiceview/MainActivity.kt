package com.choiceview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.choiceadapter.Choice
import com.choiceadapter.ChoiceAdapter
import com.choiceadapter.ChoiceCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_test_choice.view.*

class MainActivity : AppCompatActivity(), ChoiceCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = mutableListOf<Option>()
        for (i in 0..10) {
            options.add(Option(i))
        }

        val choiceAdapter = ChoiceAdapter(this, R.layout.layout_test_choice, this, 2)
        choiceAdapter.updateData(options)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = choiceAdapter
    }

    override fun onChoiceSelected(choice: Choice, view: View) {
        view.tv.apply {
            text = context.getString(R.string.select_text)
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    android.R.color.holo_blue_light
                )
            )
        }
    }

    override fun onChoiceUnselected(choice: Choice, view: View) {
        view.tv.apply {
            text = context.getString(R.string.unselect_text)
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    android.R.color.holo_green_light
                )
            )
        }
    }
}
