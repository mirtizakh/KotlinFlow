package com.project.daggermultibindingmvvmsample.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.project.daggermultibindingmvvmsample.Users

class UserItemDiffCallback : DiffUtil.ItemCallback<Users>() {

    override fun areItemsTheSame
                (oldItem: Users, newItem: Users): Boolean
            = oldItem.id == newItem.id

    override fun areContentsTheSame
                (oldItem: Users, newItem: Users): Boolean
            = oldItem == newItem
}