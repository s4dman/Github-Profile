package com.sadmanhasan.githubProfile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.githubProfile.models.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUserProfile()
        onClick()
    }

    private fun onClick() {
        text_user_repositories.setOnClickListener {
            val intent = Intent(this, RepositoriesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUserProfile() {

        Fuel.get("https://api.github.com/users/afollestad")
                .responseObject(UserModel.Deserializer()) { _, _, result ->
                    val (userModel, error) = result
                    if (userModel != null) {

                        Glide.with(this)
                                .load(userModel.avatar_url)
                                .into(img_profile)

                        text_name.text = userModel.name
                        text_username.text = userModel.login
                        text_location.text = userModel.location
                        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        val formatter = SimpleDateFormat("MMMM d, Y")
                        val output: String = formatter.format(parser.parse(userModel.created_at))
                        text_joined.text = "Joined " + output
                        text_user_followers.text = userModel.followers
                        text_user_following.text = userModel.following
                        text_user_repositories.text = userModel.public_repos

                    }
                }
    }
}

