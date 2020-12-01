package `in`.surajsau.dynamic.util

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset

fun Drawable.toImageAsset(): ImageAsset
        = (this as? BitmapDrawable)?.bitmap?.asImageAsset() ?: throw Exception("Cannot cast to BitmapDrawable")