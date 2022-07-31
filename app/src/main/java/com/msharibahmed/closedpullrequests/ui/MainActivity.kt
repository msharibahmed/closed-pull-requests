package com.msharibahmed.closedpullrequests.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.HttpException
import com.msharibahmed.closedpullrequests.R
import com.msharibahmed.closedpullrequests.databinding.ActivityMainBinding
import com.msharibahmed.closedpullrequests.models.Item
import com.msharibahmed.closedpullrequests.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private lateinit var rvAdapter: ClosedPrAdapter //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()
        subscribeObservers()
        viewModel.callApi()

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.callApi()
        }

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<Item>> -> {
                    displayLoading(false)
                    populateRecyclerView(dataState.data)
                }
                is DataState.Loading -> {
                    displayLoading(true)
                }
                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception)
                }
            }
        }
    }


    private fun displayError(exception: Exception) {
        when (exception) {
            is IOException -> {
                Toast.makeText(
                    this,
                    "No Internet Connection!\nSwipe down to refresh",
                    Toast.LENGTH_LONG
                ).show()


            }
            is HttpException -> Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()

            else -> Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun populateRecyclerView(closedPrs: List<Item>) {
        if (closedPrs.isNotEmpty()) rvAdapter.submitList(closedPrs)
    }

    private fun setupRecyclerView() {
        rvAdapter = ClosedPrAdapter()
        binding.RvPullRequest.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }
    }


}