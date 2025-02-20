package com.andef.myvideos.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.andef.myvideos.domain.entities.Video

class VideoCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}