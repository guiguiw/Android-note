package j.l.lab2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        
        RadioGroup myRadioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group,int checkedId){
                RadioButton checkButton=(RadioButton) findViewById(checkedId);

                Toast.makeText(MainActivity.this,checkButton.getText().toString()+ "身份被选中", Toast.LENGTH_SHORT).show();
        }
        });

        Button btn2=(Button)findViewById(R.id.id5);
        final RadioGroup radioSelect=(RadioGroup)findViewById(R.id.radioGroup);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioSelect.getCheckedRadioButtonId();
                RadioButton checkButton=(RadioButton) findViewById(checkedId);

                Toast.makeText(MainActivity.this, checkButton.getText().toString() + "身份注册功能尚未开启", Toast.LENGTH_SHORT).show();

            }
        });


        Button btn = (Button) findViewById(R.id.id4);
        final EditText username = (EditText) findViewById(R.id.inputname);
        final EditText password = (EditText) findViewById(R.id.inputpw);

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示");
        builder.setMessage("登录成功");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(MainActivity.this, "对话框“确定”按钮被点击" , Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(MainActivity.this, "对话框“取消”按钮被点击", Toast.LENGTH_SHORT).show();
            }
        }).create();

        final AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
        builder2.setTitle("提示");
        builder2.setMessage("登录失败");
        builder2.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(MainActivity.this, "对话框“确定”按钮被点击" , Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(MainActivity.this, "对话框“取消”按钮被点击", Toast.LENGTH_SHORT).show();
            }
        }).create();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(username.getText().toString())) {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (TextUtils.isEmpty(password.getText().toString())) {
                        Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    else{
                        if(username.getText().toString().equals("Android")&& password.getText().toString().equals("123456")){
                            builder.show();
                        }
                        else{
                            builder2.show();
                        }
                    }
                    }
            }
        });
    }
}