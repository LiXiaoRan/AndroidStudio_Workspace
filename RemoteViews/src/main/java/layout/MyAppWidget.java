package layout;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.liran.remoteviews.R;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

    private static final String TAG = "MyAppWidget";

    public static final String CLICK_ACTION = "com.liran.remoteviews.action.CLICK";

    /**
     * 桌面小部件更新
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetId
     */
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        Intent intentclick=new Intent();
        intentclick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intentclick,0);
        views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /**
     * 每次桌面小部件更新时都会回调一次该方法
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    @Override
    public void onReceive(final Context context, final Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, "onReceive: action = " + intent.getAction());

        if (intent.getAction().equals(CLICK_ACTION)) {
            Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
                    RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.my_app_widget);
                    remoteViews.setTextViewText(R.id.appwidget_text, "点击时间：" + System.currentTimeMillis());
//                    Intent intentClick=new Intent(context, MainActivity.class);
                    Intent intentClick=new Intent();
                    intentClick.setAction(CLICK_ACTION);
                    PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intentClick,0);
                    remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

//                    appWidgetManager.updateAppWidget(R.layout.my_app_widget,remoteViews);
                    appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidget.class), remoteViews);

                }
            }).start();

        }


    }
}

