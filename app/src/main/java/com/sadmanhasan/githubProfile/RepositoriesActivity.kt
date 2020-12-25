package com.sadmanhasan.githubProfile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.githubProfile.adapters.RepoAdapter
import com.sadmanhasan.githubProfile.models.RepoModel
import com.sadmanhasan.githubProfile.utils.GenericUtil
import kotlinx.android.synthetic.main.activity_repositories.*
import kotlinx.android.synthetic.main.repo_list.*

class RepositoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        val userName = GenericUtil.getSharedPref(this, "userName")

        val actionBar = supportActionBar
        actionBar!!.title = "Repositories"
        actionBar.setDisplayHomeAsUpEnabled(true)

        getRepo(userName)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getRepo(userName: String) {
        Fuel.get("https://api.github.com/users/$userName/repos")
            .responseObject(RepoModel.ListDeserializer()) { _, _, result ->
                val (repoModel, error) = result
                if (repoModel != null) {
                    repoRecyclerView(repoModel)
                }
            }
    }

    private fun repoRecyclerView(repoModel: List<RepoModel>?) {
        recycler_repo.apply {
            layoutManager = LinearLayoutManager(this@RepositoriesActivity)
            adapter = RepoAdapter(this@RepositoriesActivity, repoModel!!)
        }
    }
}
