package com.lawlett.youtubeparcer.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.system.Os.bind
import androidx.appcompat.app.AppCompatActivity
import com.lawlett.youtubeparcer.model.PlaylistItem

abstract class BaseActivity(private val layout: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setupUI()
isOnline(this)
        setupLiveData()
    }
abstract fun isOnline(context: Context):Boolean
    abstract fun setupLiveData()

    abstract fun setupUI()


}