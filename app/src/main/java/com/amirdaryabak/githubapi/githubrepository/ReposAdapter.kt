package com.amirdaryabak.githubapi.githubrepository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amirdaryabak.data.entity.getrepository.Repos
import com.amirdaryabak.githubapi.databinding.ReposItemBinding

class ReposAdapter(
    private val clickListener: (Repos, Int) -> Unit,
) : ListAdapter<Repos, ReposAdapter.MyViewHolder>(DiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ReposItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repos: Repos = getItem(position)
        holder.bindData(repos)
    }

    override fun getItemId(position: Int): Long = getItem(position).hashCode().toLong()

    inner class MyViewHolder(private val binding: ReposItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(repos: Repos) {
            binding.apply {
                repos.let {
                    txtRepoName.text = it.name
                }

                itemView.setOnClickListener {
                    clickListener.invoke(repos, adapterPosition)
                }
            }

        }

    }

    private class DiffCallback : DiffUtil.ItemCallback<Repos>() {

        override fun areItemsTheSame(oldItem: Repos, newItem: Repos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repos, newItem: Repos): Boolean {
            return oldItem == newItem
        }

    }

}