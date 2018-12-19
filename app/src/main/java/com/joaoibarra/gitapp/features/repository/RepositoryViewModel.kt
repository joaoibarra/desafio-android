package com.joaoibarra.gitapp.features.repository

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import android.support.annotation.VisibleForTesting
import com.joaoibarra.gitapp.model.api.GitApiService
import com.joaoibarra.gitapp.model.Repo
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel : ViewModel() {
    var repositories: Observable<PagedList<Repo>>

    private val compositeDisposable = CompositeDisposable()

    private val repositoryFactory: RepositoryDatasourceFactory

    private val pageSize = 30

    init {
        repositoryFactory = RepositoryDatasourceFactory(compositeDisposable, GitApiService.gitApi)
        repositories = loadData()
    }

    @VisibleForTesting
    fun loadData(): Observable<PagedList<Repo>> {

        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()

        return RxPagedListBuilder(repositoryFactory, config)
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .cache()
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}