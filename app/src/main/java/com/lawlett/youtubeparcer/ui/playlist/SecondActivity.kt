package com.lawlett.youtubeparcer.ui.playlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lawlett.youtubeparcer.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
showId()
    }
    fun showId(){
        var id=intent.getStringExtra("id")
        Toast.makeText(this,"$id",Toast.LENGTH_LONG).show()
    }
}