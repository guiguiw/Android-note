package j.l.lab6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

public class MusicService extends Service {
    @Override
    //绑定Service时回调该方法
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //返回IBinder对象
        return binder;
    }

    //创建MediaPlayer实例对象
    public MediaPlayer   mediaPlayer = new MediaPlayer();
    public MusicService() {
        try {
            //真机测试时歌曲路径
            //mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/music/K.Will-Melt.mp3");
            //模拟器测试时歌曲路径
            mediaPlayer.setDataSource("/data/K.Will-Melt.mp3");
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //歌曲播放和暂停函数的实现
    public void playandpause(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
        }
    }
    //按钮“STOP”的实现函数
    public void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            try{
                mediaPlayer.prepare();
                //按钮事件被触发后，进度条回到最开始的位置
                mediaPlayer.seekTo(0);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //歌曲暂停函数
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    //Service被关闭之前回调该方法
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    //定义onBind方法所返回对象
    public final IBinder binder = new MyBinder();
    //继承Binder实现IBinder类
    public class MyBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    //Service被创建时调用该方法
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
