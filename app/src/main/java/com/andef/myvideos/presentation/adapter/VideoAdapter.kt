package com.andef.myvideos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.andef.myvideos.R
import com.andef.myvideos.databinding.VideoItemBinding
import com.andef.myvideos.domain.entities.Video

class VideoAdapter : ListAdapter<Video, VideoAdapter.VideoViewHolder>(VideoCallback()) {
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
        holder.imageViewVideoThumbnail.setImageResource(R.drawable.ic_launcher_foreground)
        holder.textViewVideoTitle.text = video.title
        holder.textViewVideoDuration.text = video.duration
    }

    class VideoViewHolder(binding: VideoItemBinding) : ViewHolder(binding.root) {
        val cardViewVideo = binding.cardViewVideo
        val imageViewVideoThumbnail = binding.imageViewVideoThumbnail
        val textViewVideoTitle = binding.textViewVideoTitle
        val textViewVideoDuration = binding.textViewVideoDuration
    }
}