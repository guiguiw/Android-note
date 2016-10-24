package j.l.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/23.
 */
public class StaticActivity extends AppCompatActivity {

    private static final String STATICATION = "j.l.broadcast.staticreceiver";
    ArrayList<Map<String,Object>> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_layout);

        ListView fruitlistview = (ListView)this.findViewById(R.id.fruitlistview);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,R.layout.widget_demo,
                new String[] {"fruit_image","fruit_name"},new int[]{R.id.fruit_image,R.id.fruit_name});
        String[] fruit_name = new String[]{"apple","banana","cherry","coco","kiwi","orange","pear",
                "strawberry", "watermelon"};
        final int[] fruit_image = new int[]{R.mipmap.icapple,R.mipmap.icbanana,R.mipmap.iccherry,
                R.mipmap.iccoco, R.mipmap.ickiwi,R.mipmap.icorange,R.mipmap.icpear,R.mipmap.icstrawberry,
                R.mipmap.icwatermelon};

        for(int i=0;i<9;i++){
            Map<String,Object> temp = new HashMap<>();
            temp.put("fruit_image",fruit_image[i]);
            temp.put("fruit_name",fruit_name[i]);
            data.add(temp);
        }
        fruitlistview.setAdapter(simpleAdapter);

        fruitlistview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id){
                TextView tv2 = (TextView)view.findViewById(R.id.fruit_name);
                Intent intent = new Intent(STATICATION);
                Bundle bundle = new Bundle();
                bundle.putString("fruit_name",tv2.getText().toString());
                bundle.putInt("fruit_image",fruit_image[position]);
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }

        });
    }
}
