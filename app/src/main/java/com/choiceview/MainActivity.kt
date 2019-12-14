package com.choiceview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.choiceadapter.ChoiceAdapter
import com.choiceadapter.ChoiceCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_test_choice.view.*

class MainActivity : AppCompatActivity(), ChoiceCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = listOf(Option(1), Option(2), Option(3), Option(4))

        val choiceAdapter = ChoiceAdapter(this, R.layout.layout_test_choice, this, 2)
        choiceAdapter.updateData(options)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = choiceAdapter
    }

    override fun onChoiceSelected(choice: com.choiceadapter.Choice, view: View) {
        view.tv.apply {
            text = "This is selected now"
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    android.R.color.holo_blue_light
                )
            )
        }
    }

    override fun onChoiceUnSelected(choice: com.choiceadapter.Choice, view: View) {
        view.tv.apply {
            text = "This is unselected now"
            setBackgroundColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    android.R.color.holo_green_light
                )
            )
        }
    }

    class Option(val id: Int) : com.choiceadapter.Choice {

        override fun equals(other: Any?): Boolean {
            if (other !is Option) return false
            return this.id == other.id
        }

        override fun hashCode(): Int {
            return id
        }

        override fun toString(): String {
            return "Option: $id"
        }
    }
}
