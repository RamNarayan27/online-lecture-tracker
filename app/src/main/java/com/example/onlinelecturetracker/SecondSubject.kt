package com.example.onlinelecturetracker

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondSubject : Fragment() {

    companion object {
        fun newInstance() = SecondSubject()
    }

    private lateinit var viewModel: SecondSubjectViewModel
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.second_subject_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton = view.findViewById(R.id.fab_cloud)
        floatingActionButton.setOnClickListener {
            val intent = Intent(context, NewClassEntry::class.java).apply {
                putExtra("Subject","Cloud")
            }
            startActivity(intent)
        }
    }

}
