package org.sopt.sample.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.sample.data.remote.ResponseUserDTO
import org.sopt.sample.databinding.LayoutGithubRepoBinding

class RepoViewHolder(
    private val binding: LayoutGithubRepoBinding
): RecyclerView.ViewHolder(binding.root) {
    fun onBind (data: ResponseUserDTO.UserData){
        binding.imgRepo.load(data.avatar)
        binding.tvGithubRepoName.text = data.first_name + data.last_name
        binding.tvGithubRepoLanguage.text = data.email
    }
}