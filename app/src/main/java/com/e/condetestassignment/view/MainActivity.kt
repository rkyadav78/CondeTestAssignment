package com.e.condetestassignment.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.condetestassignment.R
import com.e.condetestassignment.viewmodel.MainViewModel
import com.example.assignment.adapter.MainAdapter
import com.example.assignment.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewmodel()
        setRecyclerView()
        shimmer_text.visibility = View.VISIBLE
        shimmer_text.startShimmerAnimation()
        btnReload.setOnClickListener {
            setViewmodel()
            setRecyclerView()
            shimmer_text.visibility = View.VISIBLE
            shimmer_text.startShimmerAnimation()
        }
    }

    private fun setRecyclerView() {
        adapter = MainAdapter(this, viewModel.listData.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerView.adapter = adapter
    }

    private fun setViewmodel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.successfulLiveData.observe(this, Observer {
            todoList ->
            todoList?.let {
                Log.e(TAG, "data Updated = $it")
                shimmer_text.visibility = View.GONE
                shimmer_text.stopShimmerAnimation()
                adapter.update(it)
            }
        })

        viewModel.failureLiveData.observe(this, Observer {
            isFailed->
            isFailed?.let {
                successconstraintlayout.visibility = View.GONE
                errorconstratintlayout.visibility = View.VISIBLE
            }
        })
        if (Utils.isNetworkAvailable(this)) {
            successconstraintlayout.visibility = View.VISIBLE
            errorconstratintlayout.visibility = View.GONE
            viewModel.getTodoList()
        } else {
            successconstraintlayout.visibility = View.GONE
            errorconstratintlayout.visibility = View.VISIBLE
        }
    }
}
