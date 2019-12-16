# choice-adapter
A flexible implementation of RecylerView to manage single and multi select custom views

### Usage
`
val choiceAdapter = ChoiceAdapter(context = requireContext(), layoutRes = R.layout.item_single_choice, choiceCallback = this, minSelection = 1)
`