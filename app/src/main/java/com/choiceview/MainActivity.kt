package com.choiceview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_test_choice.view.*

class MainActivity : AppCompatActivity(), ChoiceAdapter.ChoiceCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = listOf(Option(1), Option(2), Option(3), Option(4))

        val choiceAdapter = ChoiceAdapter(this, R.layout.layout_test_choice, this)
        choiceAdapter.updateData(options, 1)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = choiceAdapter
    }

    override fun onChoiceSelected(choice: Choice, view: View) {
        view.tv.text = "This is selected now"
    }

    override fun onChoiceUnSelected(choice: Choice, view: View) {
        view.tv.text = "This is unselected now"
    }

    class Option(val id: Int) : Choice {

        override fun equals(other: Any?): Boolean {
            if (other !is Option) return false
            return this.id == other.id
        }

        override fun hashCode(): Int {
            return id
        }
    }
}
