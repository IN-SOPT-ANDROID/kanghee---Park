package org.sopt.sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.HeaderBinding
import org.sopt.sample.databinding.LayoutGithubRepoBinding

class RecyclerAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER = 0
    private val TYPE_LIST = 1

    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<RepoData> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = HeaderBinding.inflate(inflater, parent, false)
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
                RepoViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if(holder is RepoViewHolder){
//            holder.onBind(repoList[position - 1])
//        }
        when (holder) {
            is RepoViewHolder -> {
                holder.onBind(repoList[position - 1])
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int {
        return repoList.size + 1
    }

    fun setRepoList(repoList: List<RepoData>) {
        this.repoList = repoList.toList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER
        else TYPE_LIST
    }
}