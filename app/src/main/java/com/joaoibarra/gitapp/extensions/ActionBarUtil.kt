package com.joaoibarra.gitapp.extensions

import android.support.v7.app.ActionBar

fun ActionBar.setup(title: String, isDisplayEnable: Boolean = true) {
    this.title =  title
    this.setDisplayHomeAsUpEnabled(isDisplayEnable)
}