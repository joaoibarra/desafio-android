package com.joaoibarra.gitapp

import android.arch.paging.PagedList
import com.joaoibarra.gitapp.features.repository.RepositoryListActivity
import com.joaoibarra.gitapp.features.repository.RepositoryViewModel
import com.joaoibarra.gitapp.model.Repo
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_repository_list.*
import org.junit.Before
import org.junit.Test

class RepositoryViewModelTest {

    var repositories: Observable<PagedList<Repo>> = mockk()

    private val compositeDisposable = CompositeDisposable()


    lateinit var repositoryViewModel: RepositoryViewModel

    lateinit var repositoryListActivity : RepositoryListActivity


    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun bla() {
        repositoryViewModel = spyk()

        every {
            repositoryViewModel.loadData()
        } returns Observable.empty()

        //compositeDisposable.add(repositoryListActivity.setListData())

        repositoryListActivity = spyk()


        verify {
            repositoryViewModel.loadData()
        }
    }
}