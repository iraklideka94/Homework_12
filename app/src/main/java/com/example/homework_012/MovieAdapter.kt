package com.example.Homework_12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_012.R
import com.example.homework_012.databinding.MovieItemBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.GameViewHolder>(GameItemCallback()) {

    var onItemClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GameViewHolder(
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind()
    }

    inner class GameViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val game = getItem(adapterPosition)
            Glide.with(binding.root)
                .load(game.image).
                placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.root)
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(game)
            }
        }
    }

    private class GameItemCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

}