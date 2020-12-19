package com.sadmanhasan.githubProfile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadmanhasan.githubProfile.R
import com.sadmanhasan.githubProfile.models.RepoModel
import kotlinx.android.synthetic.main.repo_list.view.*

class RepoAdapter(private val context: Context, private val repos: List<RepoModel>) :
    RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.repo_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repo = repos[position]
        holder.setData(repo)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(repo: RepoModel?) {
            itemView.text_repo_name.text = repo!!.name
            itemView.text_repo_desc.text = repo.description
            itemView.text_repo_lang.text = repo.language
            itemView.text_repo_stars.text = repo.stargazers_count
            itemView.text_repo_forks.text = repo.forks_count
            itemView.text_repo_size.text = repo.size + " KB"
        }

    }
}