package com.moladin.commom.model

data class RepositoriesResponse(
    val repoList: List<RepositoriesResponseItem?>? = null
)

data class BuiltByItem(

    val avatar: String? = null,

    val url: String? = null,

    val username: String? = null
)

data class RepositoriesResponseItem(

    val forks: Int? = null,

    val starsSince: Int? = null,

    val builtBy: List<BuiltByItem?>? = null,

    val totalStars: Int? = null,

    val rank: Int? = null,

    val description: String? = null,

    val language: String? = null,

    val languageColor: String? = null,

    val repositoryName: String? = null,

    val url: String? = null,

    val username: String? = null,

    val since: String? = null
)

