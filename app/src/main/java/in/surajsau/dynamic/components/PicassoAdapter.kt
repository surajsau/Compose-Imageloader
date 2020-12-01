package `in`.surajsau.dynamic.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class PicassoAdapter(private val context: Context) : Adapter() {
    private val picasso = Picasso.get()

    private var target = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmap?.let {
                callback?.onSuccess(it.toDrawable(context.resources))
            }
        }

        override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
            callback?.onError(drawable = errorDrawable, error = Exception(e))
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            callback?.onStart(placeHolderDrawable)
        }

    }

    override fun enqueue(imageUrl: String?) {
        picasso.load(imageUrl).into(target)
    }

    override fun dispose() {
        picasso.cancelRequest(target)
    }

}