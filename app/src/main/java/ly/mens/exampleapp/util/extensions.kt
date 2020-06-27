package ly.mens.exampleapp.util

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

fun Context.appCompatActivity(): AppCompatActivity? {
    var curContext = this
    var maxDepth = 20
    while (--maxDepth > 0 && curContext !is AppCompatActivity) {
        curContext = (curContext as ContextWrapper).baseContext
    }
    return if(curContext is AppCompatActivity)
        curContext
    else
        null
}

fun Context.lifecycleOwner(): LifecycleOwner? {
    var curContext = this
    var maxDepth = 20
    while (maxDepth-- > 0 && curContext !is LifecycleOwner) {
        curContext = (curContext as ContextWrapper).baseContext
    }
    return if (curContext is LifecycleOwner) {
        curContext
    } else {
        null
    }
}