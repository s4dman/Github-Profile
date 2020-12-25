package com.sadmanhasan.githubProfile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.githubProfile.models.UserModel
import com.sadmanhasan.githubProfile.utils.Generics
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.text.SimpleDateFormat

private const val TAG = "UserProfileActivity"

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val userName: String = intent.getStringExtra("userName")!!

        savePref(userName)
        getUserProfile(userName)
        onClick()
    }

    private fun savePref(userName: String) {
        Generics.setSharedPref(this, "userName", userName)
    }

    private fun onClick() {
        text_user_repositories.setOnClickListener {
            val intent = Intent(this, RepositoriesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUserProfile(userName: String) {


        Fuel.get("https://api.github.com/users/$userName")
            .responseObject(UserModel.Deserializer()) { _, _, result ->
                val (userModel, _) = result
                if (userModel != null) {

                    Glide.with(this)
                        .load(userModel.avatar_url)
                        .into(img_profile)

                    text_name.text = userModel.name
                    text_username.text = userModel.login

                    if (userModel.bio.isNullOrEmpty())text_user_bio.visibility = View.GONE
                    else text_user_bio.text = userModel.bio

                    if (userModel.email.isNullOrEmpty()) text_user_email.visibility = View.GONE
                    else text_user_email.text = userModel.email

                    if (userModel.blog.isNullOrEmpty()) text_user_blog.visibility = View.GONE
                    else text_user_blog.text = userModel.blog

                    if (userModel.twitter_username.isNullOrEmpty()) text_user_twitter.visibility =
                        View.GONE
                    else text_user_twitter.text = userModel.twitter_username

                    if (userModel.location.isNullOrEmpty()) text_location.visibility = View.GONE
                    else text_location.text = userModel.location

                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("MMMM d, Y")
                    val output: String = formatter.format(parser.parse(userModel.created_at)!!)
                    text_joined.text = getString(R.string.JOINED, output)
                    text_user_followers.text = userModel.followers
                    text_user_following.text = userModel.following
                    text_user_repositories.text = userModel.public_repos

                }
            }
    }
}

