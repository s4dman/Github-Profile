package com.sadmanhasan.githubProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_search.*

private const val TAG = "SearchActivity"

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        onClick()
    }

    private fun onClick() {
        btn_find.setOnClickListener {
            val userName: String = edit_search.text.toString()

            if (userName.isNotEmpty()) {
                val intent = Intent(this, UserProfileActivity::class.java)
                intent.putExtra("userName", userName)
                startActivity(intent)
            }
        }
    }
}