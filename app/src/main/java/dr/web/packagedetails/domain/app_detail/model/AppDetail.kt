package dr.web.packagedetails.domain.app_detail.model

import android.graphics.drawable.Drawable

data class AppDetail(
    val name: String,
    val icon: Drawable,
    val packageName: String,
    val versionName: String,
    val apkHash: String? = null
)
