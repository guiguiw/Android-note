package j.l.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Created by Administrator on 2016/10/18.
 */
public class DynamicReceiver extends BroadcastReceiver {
    private static final String DYNAMICACTION="j.l.broadcast.dynamicreceiver";

    @Override
    public void onReceive(Context context,Intent intent){

        //super.onReceive(context,intent);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(DYNAMICACTION)){
            rv.setTextViewText(R.id.appwidget_text,bundle.getString("msg"));
            rv.setImageViewResource(R.id.widget_image,R.mipmap.icdynamic);

            Intent clickInt = new Intent(context,MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, 0, clickInt, 0);
            rv.setOnClickPendingIntent(R.id.widget_image,pi);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(),
                    NewAppWidget.class), rv);
        }

    }
}
