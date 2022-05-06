package com.zerowater.presentation.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zerowater.domain.model.GithubRepo
import com.zerowater.presentation.databinding.ItemGithubBinding


/**
 * 깃허브 리스트 어답터
 *
 * @constructor Create GithubReposAdapter
 */
class GithubReposAdapter :
    ListAdapter<GithubRepo, GithubReposAdapter.GithubRepoViewHolder>(GithubRepoDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder {
        return GithubRepoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GithubRepoViewHolder private constructor(val binding: ItemGithubBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GithubRepo) {
            binding.data = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GithubRepoViewHolder {
                return GithubRepoViewHolder(
                    ItemGithubBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

}

class GithubRepoDiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
    override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem.name == newItem.name
                && oldItem.url == newItem.url
    }
}