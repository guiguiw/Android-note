package j.l.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


/**
 * Created by Administrator on 2016/10/18.
 */
/*public class StaticReceiver extends BroadcastReceiver{

    private static final String STATICATION = "j.l.broadcast.staticreceiver";

    @Override
    public void onReceive(Context context, Intent intent){
        if (intent.getAction().equals(STATICATION)){
            Bundle bundle = intent.getExtras();
            //获取状态栏通知管理
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //实例化通知栏构造器
            Notification.Builder builder = new Notification.Builder(context);

            //bm设定
            Bitmap bm  = BitmapFactory.decodeResource(context.getResources(),
                    bundle.getInt("fruit_image"));

            //对Builder进行设置
            builder .setContentTitle("静态广播")
                    .setContentText(bundle.getString("fruit_name"))
                    .setTicker("您有一条新消息")
                    //大图标
                    .setLargeIcon(bm)
                    //小图标,与“您有一条新消息”一起弹出
                    .setSmallIcon(bundle.getInt("fruit_image"))
                    //用户单击面板，通知栏消失
                    .setAutoCancel(true)
                   //设置通知栏点击意图
                   .setContentIntent(PendingIntent.getActivity(context,0,new Intent(context,MainActivity.class),0));

            Notification notify = builder.build();
            manager.notify(0,notify);
        }
    }
}*/
