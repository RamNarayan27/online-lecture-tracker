package com.example.onlinelecturetracker

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ThirdSubject : Fragment() {

    companion object {
        fun newInstance() = ThirdSubject()
    }

    private lateinit var viewModel: ThirdSubjectViewModel
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_subject_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton = view.findViewById(R.id.fab_pds)
        floatingActionButton.setOnClickListener {
            val intent = Intent(context, NewClassEntry::class.java).apply {
                putExtra("Subject","PDS")
            }
            startActivity(intent)
        }
    }

}
