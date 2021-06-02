package com.amirdaryabak.githubapi.userfollowers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.amirdaryabak.data.entity.userfollowers.UserFollowers
import com.amirdaryabak.githubapi.databinding.FollowersItemBinding

class FollowersAdapter(
    private val clickListener: (UserFollowers, Int) -> Unit,
) : ListAdapter<UserFollowers, FollowersAdapter.MyViewHolder>(DiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = FollowersItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userFollowers: UserFollowers = getItem(position)
        holder.bindData(userFollowers)
    }

    override fun getItemId(position: Int): Long = getItem(position).hashCode().toLong()

    inner class MyViewHolder(private val binding: FollowersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(userFollowers: UserFollowers) {
            binding.apply {
                userFollowers.let {
                    ivAvatar.load(it.avatar_url)
                    txtName.text = it.login
                }

                itemView.setOnClickListener {
                    clickListener.invoke(userFollowers, adapterPosition)
                }
            }

        }

    }

    private class DiffCallback : DiffUtil.ItemCallback<UserFollowers>() {

        override fun areItemsTheSame(oldItem: UserFollowers, newItem: UserFollowers): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserFollowers, newItem: UserFollowers): Boolean {
            return oldItem == newItem
        }

    }

}