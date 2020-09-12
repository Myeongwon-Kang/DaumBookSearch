package com.kang6264.daumbooksearch.presentation.ui.search.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.databinding.ItemBookListBinding

class BookListAdapter(private val action: ActionHandler) :
    PagedListAdapter<BookDocument, BookListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(
        val binding: ItemBookListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBookListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.binding.document = item
            holder.binding.actionHander = action
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<BookDocument>() {
    override fun areItemsTheSame(oldItem: BookDocument, newItem: BookDocument): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: BookDocument, newItem: BookDocument): Boolean {
        return oldItem == newItem
    }
}