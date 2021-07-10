package com.example.onlinelecturetracker

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FirstSubject : Fragment() {

    companion object {
        fun newInstance() = FirstSubject()
    }

    private lateinit var viewModel: FirstSubjectViewModel
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var bdaRecyclerView: RecyclerView
    private var lectures = mutableListOf<LectureDetails>(LectureDetails(1,1,"Lecture 1","Lecture",256,"Done"),
    LectureDetails(2,1,"Lecture 18","Tutorial",45,"Pending"),
        LectureDetails(3,2,"Lecture 32","Lecture",31,"Pending"),
    LectureDetails(4,3,"Lecture 35","Tutorial",45,"Done"),
        LectureDetails(1,4,"Lecture 23","Lecture",50,"Pending")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.first_subject_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton = view.findViewById(R.id.fab_bda)
        bdaRecyclerView = view.findViewById(R.id.recycler_view_bda)
        bdaRecyclerView.layoutManager = LinearLayoutManager(context)
        bdaRecyclerView.adapter = BDAAdapter(lectures)


        floatingActionButton.setOnClickListener {
            val intent = Intent(context,NewClassEntry::class.java).apply {
                putExtra("Subject","BDA")
            }
            startActivity(intent)
        }
    }
}
