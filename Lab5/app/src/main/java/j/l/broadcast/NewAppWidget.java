package j.l.broadcast;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String STATICATION = "j.l.broadcast.staticreceiver";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        super.onUpdate(context,appWidgetManager,appWidgetIds);
        //添加图片点击事件，点击widget的图标后返回主界面
        Intent clickInt = new Intent(context,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, clickInt, 0);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        //设置事件监听，点击图片有效
        rv.setOnClickPendingIntent(R.id.widget_image,pi);
        //更新Appwidget
        appWidgetManager.updateAppWidget(appWidgetIds,rv);



    }

    public void onReceive(Context context,Intent intent){

        super.onReceive(context,intent);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(STATICATION)){
            rv.setTextViewText(R.id.appwidget_text,bundle.getString("fruit_name"));
            rv.setImageViewResource(R.id.widget_image,bundle.getInt("fruit_image"));

            Intent clickInt = new Intent(context,MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, 0, clickInt, 0);
            rv.setOnClickPendingIntent(R.id.widget_image,pi);

            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(),
                    NewAppWidget.class), rv);
        }


    }

}

