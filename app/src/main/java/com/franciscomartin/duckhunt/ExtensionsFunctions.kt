package com.franciscomartin.duckhunt

import android.app.Activity
import android.content.Intent

inline fun <reified T: Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}){
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}