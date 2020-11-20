package com.wanzi.wechatrecord.util

import android.content.Context
import android.content.Intent
import android.provider.Settings

/**
 * Created by WZ on 2018-01-30.
 */
object CommentUtils {

    val TIME_STYLE = "yyyy-MM-dd HH:mm:ss"

    /**
     * 前往开启辅助服务界面
     */
    fun goAccess(context:Context) {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}