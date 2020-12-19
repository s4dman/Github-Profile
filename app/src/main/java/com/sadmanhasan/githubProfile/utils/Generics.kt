package com.sadmanhasan.githubProfile.utils

import android.content.Context
import com.sadmanhasan.githubProfile.R

class Generics {

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
    }

}