package com.sadmanhasan.githubProfile.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

data class RepoModel(
    var name: String,
    var description: String,
    var language: String,
    var stargazers_count: String,
    var forks_count: String,
    var size: String
) {
    //Repo Array Deserializer

    class ListDeserializer : ResponseDeserializable<List<RepoModel>> {
        override fun deserialize(content: String): List<RepoModel> {
            val type = object : TypeToken<List<RepoModel>>() {}.type
            return Gson().fromJson(content, type)
        }
    }
}

