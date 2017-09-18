package sample.a9animeclient

/**
 * Created by keita on 2017/09/18.
 */

import android.support.annotation.IdRes
import android.view.View
import android.widget.Toast
import android.content.Context

fun <T : View>View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById<T>(id)
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}