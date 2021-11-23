package com.e.condetestassignment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.e.condetestassignment.R
import com.e.condetestassignment.model.Todo
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val modelResult = intent.getSerializableExtra("USER_DETAILS") as Todo
        val title = "Name : "+modelResult.title
        tvTitle.text = title
        val userId = "User Id : "+modelResult.title
        tvUserId.text = userId
        val completed = "Completed : ${modelResult.completed}"
        tvCompleted.text = completed
    }
}
