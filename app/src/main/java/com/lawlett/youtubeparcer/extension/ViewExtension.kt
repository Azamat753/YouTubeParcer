package com.lawlett.youtubeparcer.extension

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url:String){
Picasso.get().load(url).into(this)
}
fun Context.showToast(context: Context,message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}