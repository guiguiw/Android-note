package j.l.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/10/18.
 */
public class DynamicActivity extends AppCompatActivity{

    private static final String DYNAMICATION = "j.l.broadcast.dynamicreceiver";
    DynamicReceiver dynamicReceiver=new DynamicReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);

        //获得控件
        final EditText text = (EditText)findViewById(R.id.input);
        final Button reg = (Button)findViewById(R.id.register);
        Button send = (Button)findViewById(R.id.send);

        //判断是否进行注册或者注销
        final int[] tag =new  int[] {0};
        //按钮事件监听
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag[0] == 0){
                    IntentFilter dynamic_filter = new IntentFilter();
                    dynamic_filter.addAction(DYNAMICATION);
                    registerReceiver(dynamicReceiver,dynamic_filter);
                    reg.setText("Unregister Broadcast");
                    tag[0] = 1;
                }
                else if(tag[0] == 1){
                    unregisterReceiver(dynamicReceiver);
                    reg.setText("Register Broadcast");
                    tag[0] = 0;
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DYNAMICATION);
                Bundle bundle = new Bundle();
                bundle.putString("msg",text.getText().toString());
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });




    }
}
