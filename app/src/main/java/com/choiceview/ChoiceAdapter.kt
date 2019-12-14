package com.choiceview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChoiceAdapter(
    context: Context,
    private val layoutRes: Int,
    private val choiceCallback: ChoiceCallback
) :
    RecyclerView.Adapter<ChoiceAdapter.ChoiceVH>() {

    private var minSelection: Int = 1
    private var maxSelection: Int = 0

    private val layoutInflater = LayoutInflater.from(context)

    private val choices = mutableListOf<Choice>()
    private val selectedChoices = mutableListOf<Choice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoiceVH {
        return ChoiceVH(layoutInflater.inflate(layoutRes, parent, false)).apply {
            itemView.setOnClickListener(onChoiceClick)
        }
    }

    override fun getItemCount(): Int = choices.size

    override fun onBindViewHolder(holder: ChoiceVH, position: Int) {
        val choice = choices[position]
        val itemView = holder.itemView
        itemView.setTag(R.id.choice_data, choice)
    }

    fun updateData(
        choices: List<Choice>, minSelection: Int = 1, maxSelection: Int = choices.size
    ) {
        this.minSelection = minSelection
        this.maxSelection = maxSelection
        this.choices.clear()
        this.choices.addAll(choices)
        notifyDataSetChanged()
    }

    private val onChoiceClick = View.OnClickListener { view ->
        val choice = view.getTag(R.id.choice_data) as Choice
        if (selectedChoices.contains(choice)) {
            selectedChoices.remove(choice)
            choiceCallback.onChoiceUnSelected(choice, view)
        } else {
            selectedChoices.add(choice)
            choiceCallback.onChoiceSelected(choice, view)
        }
    }

    interface ChoiceCallback {
        fun onChoiceSelected(choice: Choice, view: View)

        fun onChoiceUnSelected(choice: Choice, view: View)
    }

    class ChoiceVH(view: View) : RecyclerView.ViewHolder(view)
}