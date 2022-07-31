package com.msharibahmed.closedpullrequests.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.msharibahmed.closedpullrequests.databinding.PrItemLayoutBinding
import com.msharibahmed.closedpullrequests.models.Item
import java.text.SimpleDateFormat
import java.util.*

class ClosedPrAdapter :
    ListAdapter<Item, ClosedPrAdapter.MyViewHolder>(closedPrAdapterDiffer) {

    companion object {
        val closedPrAdapterDiffer = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding =
            PrItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class MyViewHolder(private val binding: PrItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private fun dateFormatter(date: String): String {
            val parsedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse(date)!!
            return SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH).format(parsedDate)

        }
        fun bind(item: Item) {
            Log.d("MY_TAG", item.toString())
            binding.apply {
                Glide.with(binding.root.context).load(item.user.avatarUrl).into(binding.IvAvatar)
                TvAuthor.text = "By: ${item.user.login}"
                TvTitle.text = "PR: ${item.title}"
                TvCreatedAt.text = "Created: ${dateFormatter(item.createdAt)}"
                TvClosedAt.text = "Closed: ${dateFormatter(item.closedAt)}"
            }

        }

    }

}