package com.mycp.mylibrary.utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import com.wanzi.wechatrecord.util.ShellCommand;


public class DouYinUtils {

    public static void getTextViewString(AccessibilityService service, String id) {

        AccessibilityNodeInfo accessibilityNodeInfo = service.getRootInActiveWindow();
        if (accessibilityNodeInfo == null) {

        }
    }


    public static void openDouYinWaitToHomeSearch(AccessibilityService service) {
        HelpUtil.openDouYin(service);
        sleep(1000);
        AccessibilityNodeInfo nodeInfo = WechatUtils.getByIdInfo(service, WeChatIdUtils.DOUYIN_HOME_SEARCH_ID);
        while (nodeInfo == null) {
            sleep(1000);
            WechatUtils.findViewIdAndClick(service, WeChatIdUtils.DOUYIN_UPDATE_IGNORE_ID);
            sleep(1000);
            WechatUtils.findViewIdAndClick(service, WeChatIdUtils.DOUYIN_MY_INFO_IGNORE_ID);
            sleep(1000);
            WechatUtils.findViewIdAndClick(service, WeChatIdUtils.DOUYIN_CHILD_TIP_IGNORE_ID);
            sleep(1000);
            if (WechatUtils.findViewIdAndText(service, WeChatIdUtils.DOUYIN_HOME_BOTTOM_TAB_ID, "首页")) {
                touchScreen(service,1050, 30);
                sleep(3000);
            }
            nodeInfo = WechatUtils.getByIdInfo(service, WeChatIdUtils.DOUYIN_HOME_SEARCH_ID);
            sleep(1000);
        }
    }



    public static void touchScreen(AccessibilityService service,int xAxis, int yAxis) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final int screenWidth = ScreenUtil.getScreenWidth(AppUtil.getContext());
            final int screenHeight = ScreenUtil.getScreenHeight(AppUtil.getContext());
            Point position = new Point(screenWidth - xAxis, screenHeight - yAxis);
            GestureDescription.Builder builder = new GestureDescription.Builder();
            Path p = new Path();
            p.moveTo(position.x, position.y);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.addStroke(new GestureDescription.StrokeDescription(p, 0L, 100L, false));
            }
            GestureDescription gesture = builder.build();
            service.dispatchGesture(gesture, new AccessibilityService.GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);
                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);
                }
            }, null);
        }

    }

    public static void touchScreenBc(AccessibilityService service,int xAxis, int yAxis) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final int screenWidth = ScreenUtil.getScreenWidth(AppUtil.getContext());
            final int screenHeight = ScreenUtil.getScreenHeight(AppUtil.getContext());
            Point position = new Point(xAxis, yAxis);
            GestureDescription.Builder builder = new GestureDescription.Builder();
            Path p = new Path();
            p.moveTo(position.x, position.y);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.addStroke(new GestureDescription.StrokeDescription(p, 0L, 100L, false));
            }
            GestureDescription gesture = builder.build();
            service.dispatchGesture(gesture, new AccessibilityService.GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);
                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);
                }
            }, null);
        }

    }

    public static void touchScreenByViewId(AccessibilityService service,String id,int index) {
        AccessibilityNodeInfo info = WechatUtils.getIdIndex(service,id,index);
        Rect rect = new Rect();
        assert info != null;
        info.getBoundsInScreen(rect);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            final int screenWidth = ScreenUtil.getScreenWidth(AppUtil.getContext());
            final int screenHeight = ScreenUtil.getScreenHeight(AppUtil.getContext());
            Point position = new Point(rect.centerX(), rect.centerY());
            GestureDescription.Builder builder = new GestureDescription.Builder();
            Path p = new Path();
            p.moveTo(position.x, position.y);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.addStroke(new GestureDescription.StrokeDescription(p, 0L, 100L, false));
            }
            GestureDescription gesture = builder.build();
            service.dispatchGesture(gesture, new AccessibilityService.GestureResultCallback() {
                @Override
                public void onCompleted(GestureDescription gestureDescription) {
                    super.onCompleted(gestureDescription);
                }

                @Override
                public void onCancelled(GestureDescription gestureDescription) {
                    super.onCancelled(gestureDescription);
                }
            }, null);
        }

    }
    public static void touchFirstUser(AccessibilityService service,String id) {
        final int screenWidth = ScreenUtil.getScreenWidth(AppUtil.getContext());
        final int screenHeight = ScreenUtil.getScreenHeight(AppUtil.getContext());
        AccessibilityNodeInfo nodeInfo = WechatUtils.getByIdInfo(service, WeChatIdUtils.DOUYIN_SEARCH_FIRST_USER_ID);
        if(nodeInfo!=null){
            Rect rect = new Rect();
            nodeInfo.getBoundsInScreen(rect);
        }
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void shellBackXY() {
        sleep(500);
        ShellCommand.INSTANCE.shellCommand("input tap " + 838 + " " + 1848);
    }

    /**
     * 滑动屏幕的像素位置
     */
    public static void swipeInstrumentation(int xAxis, int yAxis, int xAxis2, int yAxis2) {
        sleep(200);
        ShellCommand.INSTANCE.shellCommand("input swipe " + xAxis + " " + yAxis + " " + xAxis2 + " " + yAxis2 + " 100");//+ " 300"
        sleep(200);
    }
}
