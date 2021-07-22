package com.example.onlinelecturetracker.firstsubject

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinelecturetracker.lecturedata.LectureDetails
import com.example.onlinelecturetracker.NewClassEntry
import com.example.onlinelecturetracker.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FirstSubject : Fragment() {

    private lateinit var firstSubjectViewModel: FirstSubjectViewModel
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var bdaRecyclerView: RecyclerView
    private lateinit var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var lectureDetails: List<LectureDetails>
    private lateinit var totalLectureDurationHours: TextView
    private lateinit var totalLectureDurationMins: TextView
    private val zero: String = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        firstSubjectViewModel = ViewModelProvider(this).get(FirstSubjectViewModel::class.java)

        val view = inflater.inflate(R.layout.first_subject_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bdaAdapter = BDAAdapter(requireContext(),firstSubjectViewModel)
        floatingActionButton = view.findViewById(R.id.fab_bda)
        bdaRecyclerView = view.findViewById(R.id.recycler_view_bda)
        totalLectureDurationHours = view.findViewById(R.id.duration_hours_value_first)
        totalLectureDurationMins = view.findViewById(R.id.duration_mins_value_first)
        bdaRecyclerView.layoutManager = LinearLayoutManager(context)
        firstSubjectViewModel.readAllData.observe(viewLifecycleOwner, { lectures ->
            bdaAdapter.setData(lectures)
            lectureDetails = lectures
        })
        bdaRecyclerView.adapter = bdaAdapter
        firstSubjectViewModel.totalLectureDuration.observe(viewLifecycleOwner, {
            val hours = firstSubjectViewModel.totalLectureDuration.value?.div(60) ?: zero
            val mins = firstSubjectViewModel.totalLectureDuration.value?.rem(60) ?: zero
            totalLectureDurationHours.text = hours.toString()
            totalLectureDurationMins.text = mins.toString()
        })

        itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        val updatedLecture = LectureDetails(
                            lectureDetails[viewHolder.layoutPosition].id,
                            lectureDetails[viewHolder.layoutPosition].unitNumber,
                            lectureDetails[viewHolder.layoutPosition].lectureNumber,
                            lectureDetails[viewHolder.layoutPosition].lectureName,
                            lectureDetails[viewHolder.layoutPosition].type,
                            lectureDetails[viewHolder.layoutPosition].duration,
                            "Done",
                            lectureDetails[viewHolder.layoutPosition].subject
                        )
                        firstSubjectViewModel.updateLecture(updatedLecture)
                        bdaAdapter.notifyItemChanged(viewHolder.layoutPosition)
                    }
                    ItemTouchHelper.LEFT -> {
                        val updatedLecture = LectureDetails(
                            lectureDetails[viewHolder.layoutPosition].id,
                            lectureDetails[viewHolder.layoutPosition].unitNumber,
                            lectureDetails[viewHolder.layoutPosition].lectureNumber,
                            lectureDetails[viewHolder.layoutPosition].lectureName,
                            lectureDetails[viewHolder.layoutPosition].type,
                            lectureDetails[viewHolder.layoutPosition].duration,
                            "Pending",
                            lectureDetails[viewHolder.layoutPosition].subject
                        )
                        firstSubjectViewModel.updateLecture(updatedLecture)
                        bdaAdapter.notifyItemChanged(viewHolder.layoutPosition)
                    }
                }
            }

        }

        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(bdaRecyclerView)

        setHasOptionsMenu(true)

        floatingActionButton.setOnClickListener {
            val intent = Intent(context, NewClassEntry::class.java).apply {
                putExtra("FragmentName","FirstSubject")
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllLectures()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllLectures(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            firstSubjectViewModel.deleteAllLectures()
            Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ ->
            Toast.makeText(context,"Cancelled",Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Delete All Lectures")
        builder.setMessage("Do you want to delete all the lectures?")
        builder.create().show()
    }

}
