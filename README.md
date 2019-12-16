# choice-adapter
A flexible implementation of RecylerView to manage single and multi select custom views

### Usage
```
// Creating adapter
val choiceAdapter = ChoiceAdapter(
    context = requireContext(), layoutRes = R.layout.item_single_choice,
    choiceCallback = this, minSelection = 1
)

// Updating with options. Option should be a instance of [Choice](https://github.com/abhishek-bharadwaj/choice-adapter/blob/master/choiceadapter/src/main/java/com/choiceadapter/Choice.kt)
choiceAdapter.updateData(options)
rv_single_select.layoutManager = LinearLayoutManager(requireContext())
rv_single_select.adapter = choiceAdapter

```