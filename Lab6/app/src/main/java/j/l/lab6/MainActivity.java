package j.l.lab6;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.icu.text.SimpleDateFormat;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private  MusicService musicService = new MusicService();
    private TextView musicStatu;
    private ImageView imageView;
    private TextView musicTime,musicTime2;
    private Button btnplay, btnstop,btnquit;
    private SeekBar seekBar;
    //创建ObjectAnimator实例
    private ObjectAnimator objectAnimator = new ObjectAnimator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
          //引入控件
          imageView = (ImageView)findViewById(R.id.pic);
          btnplay = (Button)findViewById(R.id.play);
          btnstop = (Button)findViewById(R.id.stop);
          btnquit = (Button)findViewById(R.id.quit);
          musicStatu = (TextView)findViewById(R.id.musicstatus);
          musicTime = (TextView)findViewById(R.id.musictime);
          musicTime2 = (TextView)findViewById(R.id.musictime2);
          //绑定按钮监听事件函数
          addButtonListener();
          //引入SeekBar控件，通过该控件显示歌曲播放进度
          seekBar = (SeekBar)findViewById(R.id.seekbar);
          seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
          seekBar.setMax(musicService.mediaPlayer.getDuration());
          //创建启动Service的Intent
          Intent intent = new Intent(this,MusicService.class);
          //调用StatrService函数启动Service
          startService(intent);
          //绑定指定的Service
          bindService(intent, sc, BIND_AUTO_CREATE);
          //图片旋转函数
          rotate();
          //更新UI
          mHandler.post(mRunnable);
    }

    //绑定各按钮的监听事件
    private void addButtonListener() {
        btnplay.setOnClickListener(this);
        btnstop.setOnClickListener(this);
        btnquit.setOnClickListener(this);
    }

    //重载onResume函数
    @Override
    protected void onResume() {
        //根据音乐是否播放来设置不同歌曲状态
        if(musicService.mediaPlayer.isPlaying()) {
            musicStatu.setText(getResources().getString(R.string.playing));
        } else {
            musicStatu.setText(getResources().getString(R.string.pause));
        }
        //获取进度条的状态，调用下面两个函数获得歌曲总时长和当前位置
        seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
        seekBar.setMax(musicService.mediaPlayer.getDuration());
        //进行UI的更新
        mHandler.post(mRunnable);
        super.onResume();
    }


    //定义一个ServiceConnection对象sc
    private ServiceConnection sc = new ServiceConnection() {
        //当Activity和Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MyBinder)service).getService();
            setDuration();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };


    public android.os.Handler mHandler = new android.os.Handler();
    public Runnable mRunnable = new Runnable(){
        @Override
        public void run(){
            if(musicService.mediaPlayer.isPlaying()){
                musicStatu.setText(getResources().getString(R.string.playing));
                btnplay.setText(getResources().getString(R.string.pause));
            }else{
                musicStatu.setText(getResources().getString(R.string.pause).toLowerCase());
                btnplay.setText(getResources().getString(R.string.play));
            }
            //获取当前进度条位置
            seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());

            //对进度条进行监听
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                /*
                 * 重载onProgressChanged函数
                 * progress用于获取当前数值的大小，单位为毫秒
                 */
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //更新播放时间
                    musicTime.setText(getPlayingTime(progress));
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }
                /*
                 seekTo函数接收当前进度条的位置作为参数
                 用户停止拖动进度条时，直接跳到当前位置
                 */
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    musicService.mediaPlayer.seekTo(seekBar.getProgress());
                }
            });
            /*
            延迟100ms，将Runable插入到消息队列
            等待被处理
             */
            mHandler.postDelayed(mRunnable, 100);
        }
    };

    /*
     使用switch语句进行事件监听
     按钮的ID作为选择
     其中会调用相应的动画函数
     用于实现图片的动画显示效果
     */
    public void onClick(View v){
        switch (v.getId()){
            case R.id.play:
                if(!musicService.mediaPlayer.isPlaying()){
                    musicService.playandpause();
                    if (objectAnimator.isRunning())
                        objectAnimator.resume();
                    else
                        objectAnimator.start();
                }
                else{
                    musicService.pause();
                    objectAnimator.pause();
                }
                break;
            case R.id.stop:
                musicService.stop();
                objectAnimator.end();
                break;
            case R.id.quit:
                mHandler.removeCallbacks(mRunnable);
                unbindService(sc);
                try {
                    MainActivity.this.finish();
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    //将与MediaPlayer相关的资源释放
    @Override
    public void onDestroy() {
        unbindService(sc);
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
    }
    //图片旋转函数的实现
    private void rotate() {
        objectAnimator = ObjectAnimator .ofFloat(imageView, "rotation", 0f, 360f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(5000);
        objectAnimator.setRepeatCount(-1);
    }

    /*
      播放时间变化
      利用onProgressChanged中的progress参数作为变量
      progress单位为ms,转化为s需要除以1000
     */
    private String getPlayingTime(int progress) {
        int second = progress/1000;
        int min = second / 60;
        second %= 60;
        if (second < 10)
            return "0"+min+" : 0"+ second;
        return"0"+min+" : "+ second;
    }
    //获取歌曲总时长，并在界面进行显示
    private void setDuration() {
        TextView duration = (TextView) findViewById(R.id.musictime2);
        duration.setText(getPlayingTime(musicService.mediaPlayer.getDuration()));
    }


}
