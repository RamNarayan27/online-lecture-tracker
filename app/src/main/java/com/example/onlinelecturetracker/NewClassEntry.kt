package com.example.onlinelecturetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class NewClassEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_class_entry)
        val message = intent.getStringExtra("Subject")
        when(message) {
            "BDA" -> { Toast.makeText(this,"Hello From BDA",Toast.LENGTH_SHORT).show() }
            "Cloud" -> { Toast.makeText(this,"Hello From Cloud",Toast.LENGTH_SHORT).show() }
            "PDS" -> { Toast.makeText(this,"Hello From PDS",Toast.LENGTH_SHORT).show() }
            "Embedded" -> { Toast.makeText(this,"Hello From Embedded",Toast.LENGTH_SHORT).show() }
            "Elective" -> { Toast.makeText(this,"Hello From Elective",Toast.LENGTH_SHORT).show() }
        }
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
