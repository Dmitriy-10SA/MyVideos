package com.andef.myvideos.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.andef.myvideos.R
import com.andef.myvideos.databinding.ActivityMainBinding
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.presentation.adapter.VideoAdapter
import com.andef.myvideos.presentation.application.MyVideosApplication
import com.andef.myvideos.presentation.factory.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as MyVideosApplication).component
    }

    private lateinit var videoAdapter: VideoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapterAndRecyclerView()
        initViewModel()
        initStartQueryButton()
        initSwipeRefreshLayoutVideos()
    }

    private fun initSwipeRefreshLayoutVideos() {
        binding.swipeRefreshLayoutVideos.setOnRefreshListener {
            viewModel.loadVideosBySwipe()
        }
    }

    private fun initStartQueryButton() {
        with(binding) {
            imageViewStartQuery.setOnClickListener {
                viewModel.loadVideosByQuery(editTextVideosQuery.text.toString().trim())
            }
        }
    }

    private fun initViewModel() {
        viewModel.addVideos.observe(this) { videos ->
            if (videos != null && videos.isNotEmpty()) {
                val videosInAdapter = videoAdapter.currentList.toMutableList().apply {
                    addAll(videos)
                }
                videoAdapter.submitList(videosInAdapter)
            }
        }
        viewModel.error.observe(this) {
            Toast.makeText(
                this,
                R.string.error_check_ethernet,
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.replaceVideos.observe(this) { videos ->
            if (videos != null && videos.isNotEmpty()) {
                videoAdapter.submitList(videos)
            } else {
                videoAdapter.submitList(null)
            }
        }
        viewModel.isLoading.observe(this) { isLoading ->
            binding.swipeRefreshLayoutVideos.isRefreshing = isLoading
        }
    }

    private fun initAdapterAndRecyclerView() {
        with(binding) {
            videoAdapter = VideoAdapter().apply {
                setOnReachEndListener {
                    if (!swipeRefreshLayoutVideos.isRefreshing) {
                        viewModel.loadVideosByLastQuery()
                    }
                }
                setOnVideoClickListener { video ->
                    showVideo(video)
                }
            }
            recyclerViewVideos.adapter = videoAdapter
            recyclerViewVideos.itemAnimator = null
        }
    }

    private fun showVideo(video: Video) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(video.videoUrl)
        }
        startActivity(intent)
    }
}