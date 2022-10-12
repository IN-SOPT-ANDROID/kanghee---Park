package org.sopt.sample.adapter

import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.LayoutGithubRepoBinding

class RepoViewHolder(
    private val binding: LayoutGithubRepoBinding
): RecyclerView.ViewHolder(binding.root) {
    fun onBind (data: RepoData){
        binding.imgRepo.setImageDrawable(binding.root.context.getDrawable(data.image))
        binding.tvGithubRepoName.text = data.name
        binding.tvGithubRepoLanguage.text = data.language
    }
}