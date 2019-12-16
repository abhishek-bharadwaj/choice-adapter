# choice-adapter
A flexible implementation of RecylerView to manage single and multi select custom views

### Gradle
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file. Add this in your root build.gradle at the end of repositories.
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    implementation 'com.github.abhishek-bharadwaj:choice-adapter:v1.1.1'
}
```

### Usage

**Creating adapter**
```
val choiceAdapter = ChoiceAdapter(
    context = requireContext(), layoutRes = R.layout.item_single_choice,
    choiceCallback = this, maxSelection = 1, flexible = false
)
```

**Updating with options. Option should be a instance of [Choice](https://github.com/abhishek-bharadwaj/choice-adapter/blob/master/choiceadapter/src/main/java/com/choiceadapter/Choice.kt)**
```
choiceAdapter.updateData(options)
recylerview.layoutManager = LinearLayoutManager(requireContext())
recylerview.adapter = choiceAdapter
```

**Adapter callbacks**
```
override fun onChoiceSelected(choice: Choice, view: View) {
    // This method will be called when option item is selected.
}

override fun onChoiceUnselected(choice: Choice, view: View) {
    // This method will be called when option item is unselected.
}

override fun alreadySelectedMaxChoices() {
    // This method will be called when user is trying to select more than maxSelection
}
```

**ChoiceAdapter has these public methods**
- To update data in ChoiceAdapter
    ```
    fun updateData(choices: List<Choice>)
    ```
- To get selected choices
    ```
    fun getSelectedChoices() = Array<Choice>
    ```