package com.sadmanhasan.githubProfile.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import com.sadmanhasan.githubProfile.R

class GenericUtil {


    companion object {

        fun setSharedPref(context: Context, key: String, value: String) {
            context.getSharedPreferences(R.string.PREF_NAME.toString(), Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply()
        }

        fun getSharedPref(context: Context, key: String): String {
            return context.getSharedPreferences(R.string.PREF_NAME.toString(), Context.MODE_PRIVATE)
                .getString(key, "")!!
        }

        fun checkNullEmptyString(data: String?, textView: TextView) {
            if (data.isNullOrEmpty()) textView.visibility = View.GONE
            else textView.text = data
        }
    }
}
