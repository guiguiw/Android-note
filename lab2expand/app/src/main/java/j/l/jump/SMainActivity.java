package j.l.jump;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.TextUtils;
import android.view.View;
//import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("用户名");
        passwordWrapper.setHint("密码");

        Button btn = (Button) findViewById(R.id.id4);
        RadioGroup myRadioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group,int checkedId){
                RadioButton checkButton=(RadioButton) findViewById(checkedId);

                Snackbar.make(group, checkButton.getText().toString()
                        + "身份被选中", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SMainActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                    }
                }).setDuration(5000).show();
            }
        });

        Button btn2=(Button)findViewById(R.id.id5);
        final RadioGroup radioSelect=(RadioGroup)findViewById(R.id.radioGroup);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioSelect.getCheckedRadioButtonId();
                RadioButton checkButton=(RadioButton) findViewById(checkedId);
                Snackbar.make(v, checkButton.getText().toString()
                        + "身份注册功能尚未开启", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SMainActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                    }
                }).setDuration(5000).show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();

                if (username.equals("Android") && password.equals("123456")) {

                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);

                    Snackbar.make(v, "登陆成功", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(SMainActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                        }
                    }).setDuration(5000).show();
                }

                else {
                    if (username.length()!= 0 && password.length()!=0) {
                        Snackbar.make(v, "登陆失败", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SMainActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                            }
                        }).setDuration(5000).show();
                    }
                    else {
                        if (username.isEmpty()) {
                            usernameWrapper.setErrorEnabled(true);
                            usernameWrapper.setError("用户名不能为空");
                        }
                        else {
                            usernameWrapper.setErrorEnabled(false);
                        }

                        if (password.isEmpty()) {
                            passwordWrapper.setErrorEnabled(true);
                            passwordWrapper.setError("密码不能为空");
                        }
                        else {
                            passwordWrapper.setErrorEnabled(false);
                        }
                    }
                }
            }
        });
    }
}
