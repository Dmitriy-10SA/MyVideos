package com.andef.myvideos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.andef.myvideos.databinding.VideoItemBinding
import com.andef.myvideos.domain.entities.Video
import com.andef.myvideos.presentation.parser.DurationParser
import com.bumptech.glide.Glide

class VideoAdapter : ListAdapter<Video, VideoAdapter.VideoViewHolder>(VideoCallback()) {
    private var onReachEndListener: OnReachEndListener? = null
    private var onVideoClickListener: OnVideoClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.cardViewVideo.setOnClickListener {
            onVideoClickListener?.onClick(video)
        }
        Glide.with(holder.itemView)
            .load(video.thumbnailUrl)
            .into(holder.imageViewVideoThumbnail)
        holder.textViewVideoTitle.text = video.title
        holder.textViewVideoDuration.text = DurationParser.parse(video.duration)
        if (position == itemCount - 3) {
            onReachEndListener?.onReachEnd()
        }
    }

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener) {
        this.onReachEndListener = onReachEndListener
    }

    fun setOnVideoClickListener(onVideoClickListener: OnVideoClickListener) {
        this.onVideoClickListener = onVideoClickListener
    }

    fun interface OnReachEndListener {
        fun onReachEnd()
    }

    fun interface OnVideoClickListener {
        fun onClick(video: Video)
    }

    class VideoViewHolder(binding: VideoItemBinding) : ViewHolder(binding.root) {
        val cardViewVideo = binding.cardViewVideo
        val imageViewVideoThumbnail = binding.imageViewVideoThumbnail
        val textViewVideoTitle = binding.textViewVideoTitle
        val textViewVideoDuration = binding.textViewVideoDuration
    }
}