package j.l.lab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LayoutMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Button btn1 = (Button)findViewById(R.id.Static);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Static = new Intent(LayoutMainActivity.this,StaticActivity.class);
                startActivity(Static);
            }
        });

        /*Button btn2 = (Button)findViewById(R.id.Dynamic);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dynamic = new Intent(LayoutMainActivity.this,DynamicActivity.class);
                startActivity(dynamic);
            }
        });*/
    }
}
