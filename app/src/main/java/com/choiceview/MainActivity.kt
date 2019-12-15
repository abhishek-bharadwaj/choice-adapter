package com.choiceview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.choiceview.fragments.BaseSelectionFragment
import com.choiceview.fragments.MultiSelectionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var currentTag: String

    companion object {
        const val SINGLE_SELECTION_FRAGMENT = "single_selection"
        const val MULTI_SELECTION_FRAGMENT = "multi_selection"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        addFragment(SingleSelectionFragment(), SINGLE_SELECTION_FRAGMENT)
        addFragment(MultiSelectionFragment(), MULTI_SELECTION_FRAGMENT)

        btn.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentByTag(
                currentTag
            ) as BaseSelectionFragment
            val selectedChoice = currentFragment.getSelectedChoices()
            Toast.makeText(
                this, selectedChoice.joinToString { it.toString() }, Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_container, fragment, tag)
        fragmentTransaction.commit()
        currentTag = tag
    }
}