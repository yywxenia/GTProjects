package edu.gatech.seclass.glm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Jeremy on 10/14/16.
 */

public class ItemSelector extends Activity{

    DBHandler db = new DBHandler(this);
    ArrayList<MasterItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();
        final String intentID = i.getExtras().getString("typeID");
        items = db.getItemsByTypeName(intentID);

        final ArrayList<String> itemNames = new ArrayList<String>();
        for (int j = 0; j < items.size(); j++) {
            itemNames.add(items.get(j).getName());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_select);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_listview, itemNames);

        ListView listView = (ListView) findViewById(R.id.itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("itemName",itemNames.get((int)id));
                intent.putExtra("itemType", intentID);
                setResult(2001, intent);
                finish();
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(),
//                        "Clicked on kRow: " + id,
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2002 && resultCode == 2001){
            setResult(2001, data);
            finish();
        }
    }
}
