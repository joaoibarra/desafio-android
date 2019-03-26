package com.joaoibarra.gitapp

import com.joaoibarra.gitapp.features.repository.RepositoryAdapter
import com.joaoibarra.gitapp.features.repository.RepositoryViewModel
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test

class ReposytoryListActivityClass {

    val adapter: RepositoryAdapter = mockk()

    val compositeDisposable = CompositeDisposable()

    var repositoryViewModel: RepositoryViewModel = mockk()

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun checkData_WhenOnCreateActivity_ShouldHasAnyData() {
    }
}