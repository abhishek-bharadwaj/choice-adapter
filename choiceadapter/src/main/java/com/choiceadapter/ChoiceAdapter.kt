package com.choiceadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChoiceAdapter(
    context: Context, private val layoutRes: Int,
    private val choiceCallback: ChoiceCallback, minSelection: Int = 1
) : RecyclerView.Adapter<ChoiceAdapter.ChoiceVH>() {

    private val choices = mutableListOf<Choice>()
    private val selectedChoices = LimitedQueue<Choice>(minSelection)

    private val layoutInflater = LayoutInflater.from(context)

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
        if (selectedChoices.contains(choice)) {
            choiceCallback.onChoiceSelected(choice, itemView)
        } else {
            choiceCallback.onChoiceUnSelected(choice, itemView)
        }
    }

    fun updateData(choices: List<Choice>) {
        this.choices.clear()
        this.choices.addAll(choices)
        notifyDataSetChanged()
    }

    private val onChoiceClick = View.OnClickListener { view ->
        val choice = view.getTag(R.id.choice_data) as Choice
        if (selectedChoices.contains(choice)) {
            selectedChoices.remove(choice)
        } else {
            selectedChoices.add(choice)
        }
        notifyDataSetChanged()
    }

    class ChoiceVH(view: View) : RecyclerView.ViewHolder(view)
}