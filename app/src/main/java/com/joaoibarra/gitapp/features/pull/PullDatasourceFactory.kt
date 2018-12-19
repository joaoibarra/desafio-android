package com.joaoibarra.gitapp.features.pull

import android.arch.paging.DataSource
import com.joaoibarra.gitapp.model.api.GitApi
import com.joaoibarra.gitapp.model.Pull
import io.reactivex.disposables.CompositeDisposable

class PullDatasourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val gitApi: GitApi,
    private val user: String,
    private val repo: String
) : DataSource.Factory<Int, Pull>() {

    override fun create(): DataSource<Int, Pull> {
        return PullDataSource(gitApi, compositeDisposable, user, repo)
    }
}