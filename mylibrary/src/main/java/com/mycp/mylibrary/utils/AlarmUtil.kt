package com.myf.douyintool.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.mycp.mylibrary.utils.HelpUtil
import com.mycp.mylibrary.utils.LogUtils
import java.util.*


object AlarmUtil {

    const val ACTION_ALARM_MORNING = "morning.douyin.brush.action"
    const val ACTION_ALARM_NOON = "noon.douyin.brush.action"
    const val ACTION_ALARM_NIGHT = "night.douyin.brush.action"
    private const val ALARM_MORNING_CODE = 11
    private const val ALARM_NOON_CODE = 12
    private const val ALARM_NIGHT_CODE = 13

    //9:01-10:00、12:01-13:00，20:01-21:00，随机，不能是整点
    /**
     * 启动定时器（使用系统闹铃服务）
     */
    fun startAlarm(context: Context,flag:Int) {
        when (flag) {
            1 -> {
                //早上
                setAlarm(AlarmManager.RTC_WAKEUP, 9, HelpUtil.getRandom0To60(),
                        AlarmManager.INTERVAL_DAY, ALARM_MORNING_CODE, ACTION_ALARM_MORNING, context)
            }
            2 -> {
                //中午
                setAlarm(AlarmManager.RTC_WAKEUP, 12, HelpUtil.getRandom0To60(),
                        AlarmManager.INTERVAL_DAY, ALARM_NOON_CODE, ACTION_ALARM_NOON, context)
            }
            3 -> {
                //晚上
                setAlarm(AlarmManager.RTC_WAKEUP, 20, HelpUtil.getRandom0To60(),
                        AlarmManager.INTERVAL_DAY, ALARM_NIGHT_CODE, ACTION_ALARM_NIGHT, context)
            }
        }

    }

    /**
     * 取消闹钟
     */
    fun cancelAlarm(requestCode: Int, action: String, context: Context) {
        var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(
                PendingIntent.getBroadcast(context, requestCode, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT))
    }

    fun setAlarm(type: Int, hour: Int, minute: Int, intervalMillis: Long, requestCode: Int, action: String, context: Context) {
        var now = Calendar.getInstance()
        var targetTime = now.clone() as Calendar
        targetTime.set(Calendar.HOUR_OF_DAY, hour)
        targetTime.set(Calendar.MINUTE, minute)
        targetTime.set(Calendar.SECOND, 0)
        targetTime.set(Calendar.MILLISECOND, 0)
        if (targetTime.before(now))
            targetTime.add(Calendar.DATE, 1)
        // 方便测试，这里指定即时启动，每20秒执行一次
//        setAlarm(type, 0, 20 * 1000, requestCode, action,context)
        setAlarm(type, targetTime.timeInMillis, intervalMillis, requestCode, action, context)
    }

    /**
     * 设置闹钟
     */
    fun setAlarm(type: Int, triggerAtMillis: Long, intervalMillis: Long, requestCode: Int, action: String, context: Context) {
        LogUtils.loge("设置成功了")
        var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(type, triggerAtMillis, intervalMillis, PendingIntent.getBroadcast(context,
                requestCode, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT))
    }
}