package com.joaoibarra.gitapp.features.repository

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Visibility
import android.util.Log
import android.view.View
import com.joaoibarra.gitapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_repository_list.*

class RepositoryListActivity : AppCompatActivity() {

    private val repo = "REPO"

    private val user = "USER"

    private val disposable = CompositeDisposable()

    private val viewModel: RepositoryViewModel by lazy {
        ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
    }

    private val adapter: RepositoryAdapter by lazy {
        RepositoryAdapter {
            val intent = Intent(this, PullListActivity::class.java)
            it?.apply {
                intent.putExtra(user, this.owner.login)
                intent.putExtra(repo, this.name)
            }

            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        recyclerRepositories.layoutManager = LinearLayoutManager(this)
        recyclerRepositories.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        disposable.add(setListData())
    }

    fun setListData(): Disposable {
        return viewModel.repositories
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgressBar() }
                .doOnNext { showProgressBar() }
                .subscribe(
                        { list ->
                            adapter.submitList(list)
                            hideProgressBar()
                        },
                        { e ->
                            Log.e("Error OnLoad", "Error", e)
                        }
                )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    fun showProgressBar() {
        progressbar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressbar.visibility = View.GONE
    }

}