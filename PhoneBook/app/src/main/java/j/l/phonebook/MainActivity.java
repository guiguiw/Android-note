package j.l.phonebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<Map<String,Object>>data = new ArrayList<>();
    String[] Name = new  String[] {"Aaron","Elvis","David","Edwin","Frank","Joshua","Ivan","Mark","Joseph",
                                   "Phonebe"};
    String[] Letter = new String[]{"A","E","D","E","F","J","I","M","J", "P"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ListView listview = (ListView)findViewById(R.id.MyList);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,R.layout.item,
                new String[] {"Myletter","Myname"},new int[]{R.id.Myletter,R.id.Myname});

        for(int i=0;i<10;i++){
            Map<String,Object>temp = new LinkedHashMap<>();
            temp.put("Myletter",Letter[i]);
            temp.put("Myname",Name[i]);
            data.add(temp);
        }
        listview.setAdapter(simpleAdapter);

    }
}
