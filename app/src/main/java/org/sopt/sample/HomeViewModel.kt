package org.sopt.sample

import androidx.lifecycle.ViewModel
import org.sopt.sample.adapter.RepoData

class HomeViewModel : ViewModel() {
    val mockRepoList = listOf<RepoData>(
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "da eun",
            language = "Kotlin"
        ),
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "min young",
            language = "Kotlin"
        ),
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "yoon jung",
            language = "Java"
        ),
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "kang hee",
            language = "Java"
        ),
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "da eun",
            language = "Kotlin"
        ),
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "min young",
            language = "Kotlin"
        ),
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "yoon jung",
            language = "Java"
        ),
        RepoData(
            image = R.drawable.ic_github_icon,
            name = "kang hee",
            language = "Java"
        )
    )
}