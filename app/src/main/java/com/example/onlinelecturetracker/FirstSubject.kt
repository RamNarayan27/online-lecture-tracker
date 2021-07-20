package com.example.onlinelecturetracker

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
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
    private lateinit var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback
    private lateinit var itemTouchHelper: ItemTouchHelper
//    private var lectures = mutableListOf<LectureDetails>(LectureDetails(101,1,1,"Lecture 1","Lecture",256,"Done","BDA"),
//    LectureDetails(102,2,1,"Lecture 18","Tutorial",45,"Pending","BDA"),
//        LectureDetails(103,3,2,"Lecture 32","Lecture",31,"Pending","BDA"),
//    LectureDetails(104,4,3,"Lecture 35","Tutorial",45,"Done","BDA"),
//        LectureDetails(105,1,4,"Lecture 24","Lecture",50,"Pending","BDA"),
//        LectureDetails(106,1,4,"Lecture 25","Lecture",50,"Pending","BDA"),
//        LectureDetails(107,1,4,"Lecture 26","Lecture",50,"Pending","BDA"),
//        LectureDetails(108,1,4,"Lecture 27","Lecture",50,"Pending","BDA"),
//        LectureDetails(109,1,4,"Lecture 28","Lecture",50,"Pending","BDA"),
//        LectureDetails(110,1,4,"Lecture 28","Lecture",50,"Pending","BDA")
//    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(FirstSubjectViewModel::class.java)

        val view = inflater.inflate(R.layout.first_subject_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bdaAdapter = BDAAdapter()
        floatingActionButton = view.findViewById(R.id.fab_bda)
        bdaRecyclerView = view.findViewById(R.id.recycler_view_bda)
        bdaRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { lectures ->
            bdaAdapter.setData(lectures)
        })
        bdaRecyclerView.adapter = bdaAdapter
        bdaRecyclerView.setItemViewCacheSize(0) //this is the savior

//        itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//            var swipeBack: Boolean = false
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
//                if(swipeBack) {
//                    /**
//                     * update recycler view here
//                     * whichever view is being swiped
//                     * */
//                    Toast.makeText(context,"convert to abs",Toast.LENGTH_SHORT).show()
//                    swipeBack = false
//                    return 0;
//                }
//                return super.convertToAbsoluteDirection(flags, layoutDirection)
//            }
//
//            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
//                if (actionState == ACTION_STATE_SWIPE) {
////                    Toast.makeText(context,"onchild draw",Toast.LENGTH_SHORT).show()
//                    setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                }
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//            }
//
//            @SuppressLint("ClickableViewAccessibility")
//            private fun setTouchListener(
//                c: Canvas,
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                dX: Float, dY: Float,
//                actionState: Int, isCurrentlyActive: Boolean
//            ) {
//                recyclerView.setOnTouchListener { v, event ->
////                    Toast.makeText(context,"on touch listener",Toast.LENGTH_SHORT).show()
//                    swipeBack =
//                        event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP
//                    false
//                }
//                Log.i("set touch listener before",lectures[viewHolder.adapterPosition].status + viewHolder.adapterPosition.toString())
//                lectures[viewHolder.adapterPosition].status = "Done"
//                val tempLectureList = lectures
//                bdaAdapter = BDAAdapter(tempLectureList)
//                bdaAdapter.notifyDataSetChanged()
////                Log.i("set touch listener after",lectures[viewHolder.adapterPosition].status + viewHolder.adapterPosition.toString())
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
////                lectures.removeAt(viewHolder.adapterPosition)
////                bdaRecyclerView.recycledViewPool.clear()
////                bdaAdapter.notifyDataSetChanged()
////                Log.i("onSwiped",lectures[viewHolder.adapterPosition].status)
////                Toast.makeText(context,"onSwiped",Toast.LENGTH_SHORT).show()
////                lectures[viewHolder.adapterPosition].status = "Done"
////                bdaAdapter.notifyDataSetChanged()
////                Log.i("onSwiped",lectures[viewHolder.adapterPosition].status)
//            }
//
//        }
//
//        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
//        itemTouchHelper.attachToRecyclerView(bdaRecyclerView)

        floatingActionButton.setOnClickListener {
            val intent = Intent(context,NewClassEntry::class.java).apply {
                putExtra("Subject","BDA")
            }
            startActivity(intent)
        }
    }
}
