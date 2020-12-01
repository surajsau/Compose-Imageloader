package `in`.surajsau.dynamic.components

import android.graphics.drawable.Drawable

data class ImageLoader(
    private val adapter: Adapter,
    private val imageUrl: String? = null,
    private val onStartBlock: ((drawable: Drawable?) -> Unit)? = null,
    private val onSuccessBlock: ((drawable: Drawable) -> Unit)? = null,
    private val onErrorBlock: ((placeholder: Drawable?, error: Exception) -> Unit)? = null
) {

    companion object {
        fun get(adapter: Adapter) = ImageLoader(adapter = adapter)
    }

    fun load(imageUrl: String?) = copy(imageUrl = imageUrl)
    fun onStart(block: (Drawable?) -> Unit) = copy(onStartBlock = block)
    fun onSuccess(block: (drawable: Drawable) -> Unit) = copy(onSuccessBlock = block)
    fun onError(block: (Drawable?, Exception) -> Unit) = copy(onErrorBlock = block)

    fun enqueue() {
        adapter.callback = object : Adapter.Callback {
            override fun onStart(drawable: Drawable?) {
                this@ImageLoader.onStartBlock?.invoke(drawable)
            }

            override fun onSuccess(drawable: Drawable) {
                this@ImageLoader.onSuccessBlock?.invoke(drawable)
            }

            override fun onError(drawable: Drawable?, error: Exception) {
                this@ImageLoader.onErrorBlock?.invoke(drawable, error)
            }
        }

        adapter.enqueue(imageUrl = imageUrl)
    }

    fun onDispose() {
        adapter.callback = null
        adapter.dispose()
    }
}

abstract class Adapter {
    var callback: Callback? = null
    interface Callback {
        fun onStart(drawable: Drawable?)
        fun onSuccess(drawable: Drawable)
        fun onError(drawable: Drawable?, error: Exception)
    }
    abstract fun enqueue(imageUrl: String?)
    abstract fun dispose()
}