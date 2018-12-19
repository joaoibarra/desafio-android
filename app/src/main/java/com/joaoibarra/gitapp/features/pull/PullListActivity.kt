package com.joaoibarra.gitapp.features.repository

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.joaoibarra.gitapp.R
import com.joaoibarra.gitapp.extensions.setup
import com.joaoibarra.gitapp.features.pull.PullViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_pull_list.*

class PullListActivity : AppCompatActivity() {
    private val repo = "REPO"
    private val user = "USER"

    private val disposable = CompositeDisposable()

    private val viewModel: PullViewModel by lazy {
        val repo = intent.getStringExtra(repo)
        val user = intent.getStringExtra(user)
        ViewModelProviders.of(this, PullViewModelFactory(user, repo)).get(PullViewModel::class.java)
    }

    private val adapter: PullAdapter by lazy {
        PullAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it?.htmlUrl)))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_list)

        supportActionBar?.let {
            it.setup(intent.getStringExtra(repo))
        }

        val llm = LinearLayoutManager(this)
        recyclerPulls.layoutManager = llm
        recyclerPulls.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        disposable.add(viewModel.pulls
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            adapter.submitList(list)
                        },
                        { e ->
                            Log.e("Error OnLoad", "Error", e)
                        }
                )
        )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}