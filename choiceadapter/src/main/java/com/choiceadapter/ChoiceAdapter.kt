package com.choiceadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * ChoiceAdapter is helpful in implementing single and multi-select choices.
 *
 * * @param context.
 * * @param layoutRes Layout resource which needs to be inflated.
 * * @param choiceCallback ChoicesCallback which gets triggered on item select and un-select.
 * * @param maxSelection Number of selection allowed.
 * * @param flexible Should allow selection more than allowed by min selection. If allowed
 * * it will pop first selection out and add new one.
 *
 * Make sure if your layoutResource contains any clickable items like RadioButton, CheckBox
 * set them as clickable false, so that Choice view holder can handle clicks.
 *
 */
class ChoiceAdapter(
    context: Context, private val layoutRes: Int,
    private val choiceCallback: ChoiceCallback, maxSelection: Int = 1, flexible: Boolean = true
) : RecyclerView.Adapter<ChoiceAdapter.ChoiceVH>() {

    private val choices = mutableListOf<Choice>()
    private val selectedChoices = LimitedQueue<Choice>(maxSelection)

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
            choiceCallback.onChoiceUnselected(choice, itemView)
        }
    }

    /**
     * To pass choices.
     *
     * * @param choices List of choice
     *
     */
    fun updateData(choices: List<Choice>) {
        this.choices.clear()
        this.choices.addAll(choices)
        notifyDataSetChanged()
    }

    fun getSelectedChoices() = selectedChoices.toArray()

    private val onChoiceClick = View.OnClickListener { view ->
        val choice = view.getTag(R.id.choice_data) as Choice
        if (selectedChoices.contains(choice)) {
            val indexOfItemToRemoved = selectedChoices.indexOf(choice)
            selectedChoices.remove(choice)
            notifyItemChanged(indexOfItemToRemoved)
        } else {
            if (maxSelection > 1 && !flexible && maxSelection == selectedChoices.size()) {
                choiceCallback.alreadySelectedMaxChoices()
                return@OnClickListener
            }
            selectedChoices.add(choice)
            notifyItemChanged(choices.size - 1)
        }
        notifyDataSetChanged()
    }

    inner class ChoiceVH(view: View) : RecyclerView.ViewHolder(view)
}