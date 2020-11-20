package com.mycp.mylibrary.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class HelpUtil {

    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.i("URL", "错误信息为：" + e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }
        return false;
    }

    //微信首页
    public static final String WECHAT_CLASS_LAUNCHUI = "com.tencent.mm.ui.LauncherUI";
    //抖音首页
    public static final String DOUYIN_CLASS_LAUNCHUI = "com.ss.android.ugc.aweme.splash.SplashActivity";

    /**
     * 打开微信界面
     */
    public static void openWChart(Context context) {
        Intent intent = new Intent();
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.tencent.mm", WECHAT_CLASS_LAUNCHUI);
        context.startActivity(intent);
    }
    /**
     * 打开抖音界面
     */
    public static void openDouYin(Context context) {
        Intent intent = new Intent();
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.ss.android.ugc.aweme", DOUYIN_CLASS_LAUNCHUI);
        context.startActivity(intent);
    }
    /**
     * 扫描文件
     */
    public static void scanFile(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }


    //系统剪贴板-获取:
    public static String getCopy(Context context) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 返回数据
        ClipData clipData = clipboard.getPrimaryClip();
        if (clipData != null && clipData.getItemCount() > 0) {
            // 从数据集中获取（粘贴）第一条文本数据
            return clipData.getItemAt(0).getText().toString();
        }
        return null;
    }

    public static int getRandom40To70(){
        return  (int) (Math.random() *30+40);
    }

    public static int getRandom20To40(){
        return  (int) (Math.random() *20+20);
    }

    public static int getRandom0To60(){
        return  (int) (Math.random() *60);
    }

    public static int getRandom1To10(){
        return  (int) (Math.random() *10+1);
    }
    public static int getRandom0ToX(int x){
        return  (int) (Math.random() *x);
    }

    public static void putTextIntoClip(Context context, String text){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建ClipData对象
        ClipData clipData = ClipData.newPlainText("simple text copy", text);
        //添加ClipData对象到剪切板中
        clipboardManager.setPrimaryClip(clipData);
    }

    public static String getTextFromClip(Context context){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //判断剪切版时候有内容
        if(!clipboardManager.hasPrimaryClip())
            return "";
        ClipData clipData = clipboardManager.getPrimaryClip();
        //获取 ClipDescription
        ClipDescription clipDescription = clipboardManager.getPrimaryClipDescription();
        //获取 lable
        String lable = clipDescription.getLabel().toString();
        //获取 text
        return clipData.getItemAt(0).getText().toString();
    }

    public static  void postData(Context context) {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("appId","0");//传递键值对参数
        formBody.add("packageName",context.getPackageName());//传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url("")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    if(TextUtils.equals(dataObject.getString("switchStatus"),"1")){
                        System.exit(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
