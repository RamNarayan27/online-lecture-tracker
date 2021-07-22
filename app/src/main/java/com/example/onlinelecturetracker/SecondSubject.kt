package com.example.onlinelecturetracker

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondSubject : Fragment() {

    private lateinit var secondSubjectViewModel: SecondSubjectViewModel
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var cloudRecyclerView: RecyclerView
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

        secondSubjectViewModel = ViewModelProvider(this).get(SecondSubjectViewModel::class.java)

        val view = inflater.inflate(R.layout.second_subject_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cloudAdapter = CloudAdapter(requireContext(), secondSubjectViewModel)
        floatingActionButton = view.findViewById(R.id.fab_cloud)
        cloudRecyclerView = view.findViewById(R.id.recycler_view_cloud)
        totalLectureDurationHours = view.findViewById(R.id.duration_hours_value_second)
        totalLectureDurationMins = view.findViewById(R.id.duration_mins_value_second)
        cloudRecyclerView.layoutManager = LinearLayoutManager(context)
        secondSubjectViewModel.readAllData.observe(viewLifecycleOwner, { lectures ->
            cloudAdapter.setData(lectures)
            lectureDetails = lectures
        })
        cloudRecyclerView.adapter = cloudAdapter
        secondSubjectViewModel.totalLectureDuration.observe(viewLifecycleOwner, {
            val hours = secondSubjectViewModel.totalLectureDuration.value?.div(60) ?: zero
            val mins = secondSubjectViewModel.totalLectureDuration.value?.rem(60) ?: zero
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
                        secondSubjectViewModel.updateLecture(updatedLecture)
                        cloudAdapter.notifyItemChanged(viewHolder.layoutPosition)
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
                        secondSubjectViewModel.updateLecture(updatedLecture)
                        cloudAdapter.notifyItemChanged(viewHolder.layoutPosition)
                    }
                }
            }

        }

        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(cloudRecyclerView)

        setHasOptionsMenu(true)

        floatingActionButton.setOnClickListener {
            val intent = Intent(context, NewClassEntry::class.java).apply {
                putExtra("FragmentName", "SecondSubject")
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllLectures()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllLectures() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            secondSubjectViewModel.deleteAllLectures()
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Delete All Lectures")
        builder.setMessage("Do you want to delete all the lectures?")
        builder.create().show()
    }

}
