package com.choiceview

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.choiceview.fragments.BaseSelectionFragment
import com.choiceview.fragments.MultiSelectionFragment
import com.choiceview.fragments.SingleSelectionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectionAdapter = SelectionPagerAdapter(supportFragmentManager)
        vp.adapter = selectionAdapter

        btn.setOnClickListener {
            val selectedChoice = selectionAdapter.currentFragment
                ?.getSelectedChoices() ?: return@setOnClickListener
            Toast.makeText(
                this, selectedChoice.joinToString { it.toString() }, Toast.LENGTH_LONG
            ).show()
        }
    }

    private inner class SelectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(
        fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        var currentFragment: BaseSelectionFragment? = null

        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            return if (position == 0)
                SingleSelectionFragment()
            else
                MultiSelectionFragment()
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, any: Any) {
            if (any is BaseSelectionFragment) {
                currentFragment = any
            }
            super.setPrimaryItem(container, position, any)
        }
    }
}