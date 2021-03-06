package com.sadmanhasan.githubProfile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.sadmanhasan.githubProfile.models.UserModel
import com.sadmanhasan.githubProfile.utils.GenericUtil
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.text.SimpleDateFormat

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val userName: String = intent.getStringExtra("userName")!!

        val actionBar = supportActionBar
        actionBar!!.title = userName
        actionBar.setDisplayHomeAsUpEnabled(true)

        savePref(userName)
        getUserProfile(userName)
        onClick()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun savePref(userName: String) {
        GenericUtil.setSharedPref(this, "userName", userName)
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

                    GenericUtil.checkNullEmptyString(userModel.bio, text_user_bio)
                    GenericUtil.checkNullEmptyString(userModel.email, text_user_email)
                    GenericUtil.checkNullEmptyString(userModel.blog, text_user_blog)
                    GenericUtil.checkNullEmptyString(userModel.twitter_username, text_user_twitter)
                    GenericUtil.checkNullEmptyString(userModel.location, text_location)

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

