package com.zerowater.presentation.github

import androidx.fragment.app.viewModels
import com.zerowater.presentation.R
import com.zerowater.presentation.base.BaseFragment
import com.zerowater.presentation.custom.VerticalItemDecoration
import com.zerowater.presentation.databinding.FragmentGithubReposBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubReposFragment :
    BaseFragment<FragmentGithubReposBinding>(R.layout.fragment_github_repos) {

    private val githubReposViewModel: GithubReposViewModel by viewModels()

    override fun binding(binding: FragmentGithubReposBinding) {
        binding.run {
            viewModel = githubReposViewModel
            adapter = GithubReposAdapter()
            itemDecoration = VerticalItemDecoration(15)
        }
    }

    override fun observeViewMode() {
    }
}