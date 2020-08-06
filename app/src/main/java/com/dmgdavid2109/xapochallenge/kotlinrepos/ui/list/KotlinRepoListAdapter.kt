package com.dmgdavid2109.xapochallenge.kotlinrepos.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dmgdavid2109.xapochallenge.R
import com.dmgdavid2109.xapochallenge.databinding.RepositoryListItemBinding
import com.dmgdavid2109.xapochallenge.kotlinrepos.domain.model.KotlinRepo

class KotlinRepoListAdapter(
    private val onItemTapped: (repoItem: KotlinRepo) -> Unit
) : ListAdapter<KotlinRepo, KotlinRepoItemViewHolder>(object :
    DiffUtil.ItemCallback<KotlinRepo>() {
    override fun areItemsTheSame(oldItem: KotlinRepo, newItem: KotlinRepo) = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: KotlinRepo, newItem: KotlinRepo) = oldItem.name == newItem.name
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KotlinRepoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepositoryListItemBinding.inflate(inflater, parent, false)
        return KotlinRepoItemViewHolder(
            parent.context,
            binding,
            onItemTapped
        )
    }

    override fun onBindViewHolder(holder: KotlinRepoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class KotlinRepoItemViewHolder(
    private val context: Context,
    private val binding: RepositoryListItemBinding,
    private val onItemTapped: (repoItem: KotlinRepo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: KotlinRepo) {
        binding.repositoryName.text = item.name
        binding.repositoryDescription.text = item.description

        Glide.with(context)
            .load(item.avatar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.repositoryLogo)

        binding.cardView.setOnClickListener {
            onItemTapped(item)
        }
    }
}
