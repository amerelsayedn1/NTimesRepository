package com.task.nytimeaplpication.presentation.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.task.nytimeaplpication.R
import com.task.nytimeaplpication.databinding.ItemPostArticleBinding
import com.task.nytimeaplpication.domain.data.model.news.NewsItem

/**
 * Created by Amer Elsayed.
 *12/10/2021
 *dev.amer.elsayed@gmail.com
 */

class ArticlesAdapter : ListAdapter<NewsItem, RecyclerView.ViewHolder>(differCallback) {


    private var clickListener: ((clickedView: View, item: NewsItem?, position: Int) -> Unit)? =
        null

    fun setOnClickListener(clickListener: (clickedView: View, item: NewsItem?, position: Int) -> Unit) {
        this.clickListener = clickListener
    }

    fun onViewClicked(view: View, item: NewsItem?, position: Int) {
        clickListener?.invoke(view, item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemPostArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsVH(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NewsVH -> {
                getItem(position)?.let { holder.bind(it) }
            }
        }


    }

    inner class NewsVH(private val binding: ItemPostArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsItem) = with(binding) {

            binding.root.setOnClickListener {
                onViewClicked(it, item, adapterPosition)
            }

            itemArticleTitle.text = item.title
            itemPostDescription.text = item.abstract
            itemPostAuthor.text = item.byline.ifBlank { "Unknown" }


            itemArticleIv.load(item.image) {
                placeholder(R.drawable.place_holder)
                error(R.drawable.place_holder)
                transformations(
                    RoundedCornersTransformation(
                        12f,
                        12f,
                        12f,
                        12f
                    )
                )
            }
        }
    }


}

private val differCallback = object : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem.source == newItem.source
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }
}