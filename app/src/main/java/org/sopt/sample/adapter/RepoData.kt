package org.sopt.sample.adapter

import androidx.annotation.DrawableRes

data class RepoData(
    @DrawableRes val image: Int,
    val name: String,
    val language: String
)
