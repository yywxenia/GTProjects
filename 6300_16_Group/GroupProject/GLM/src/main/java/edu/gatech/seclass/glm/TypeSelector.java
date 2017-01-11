package edu.gatech.seclass.glm;


    import android.content.Intent;
    import android.os.Bundle;
    import android.app.Activity;
    import android.view.Menu;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;
    import android.widget.Toast;

    import java.util.ArrayList;

public class TypeSelector extends Activity {

    DBHandler db = new DBHandler(this);
    ArrayList<String> types = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ArrayList<ItemType> mI = new ArrayList<ItemType>();
/*
        mI.add(new ItemType("Dairy"));
        mI.add(new ItemType("Produce"));
        mI.add(new ItemType("Dry Goods"));
        mI.add(new ItemType("Meat"));
        mI.add(new ItemType("Frozen"));
*/

        types = db.getTypeList();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_select);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.type_listview, types);

        ListView listView = (ListView) findViewById(R.id.typeList1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TypeSelector.this, ItemSelector.class);
                intent.putExtra("typeID",TypeSelector.this.types.get((int)id));
                startActivityForResult(intent, 2002);
                //Toast.makeText(getApplicationContext(),
                //        "Clicked on Row: " + id,
                //        Toast.LENGTH_SHORT).show();
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