package com.sadmanhasan.githubProfile.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class UserModel(
    var avatar_url: String,
    var login: String,
    var name: String,
    var html_url: String,
    var public_repos: String,
    var public_gists: String,
    var followers: String,
    var following: String,
    var bio: String,
    var blog: String,
    var email: String,
    var twitter_username: String,
    var location: String,
    var created_at: String,
    var updated_at: String


) {
    //User Deserializer
    class Deserializer : ResponseDeserializable<UserModel> {
        override fun deserialize(content: String): UserModel =
            Gson().fromJson(content, UserModel::class.java)
    }
}
