package com.project.daggermultibindingmvvmsample.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.project.daggermultibindingmvvmsample.SingleDataModel

class UserItemDiffCallback : DiffUtil.ItemCallback<SingleDataModel>() {

    override fun areItemsTheSame
                (oldItem: SingleDataModel, newItem: SingleDataModel): Boolean
            = oldItem.id == newItem.id

    override fun areContentsTheSame
                (oldItem: SingleDataModel, newItem: SingleDataModel): Boolean
            = oldItem == newItem
}