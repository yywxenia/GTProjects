package edu.gatech.seclass.glm;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import static edu.gatech.seclass.glm.R.layout.content_grocery_list;

/**
 * Created by Jeremy on 10/11/16.
 */
public class GroceryList extends Activity implements AdapterView.OnItemClickListener {

    MyCustomAdapter adapter = null;
    DBHandler db = new DBHandler(this);
    int gListID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        Intent intent = getIntent();
        gListID = intent.getIntExtra("glistID", 0);
        //Generate list View from ArrayList
        displayListView();
        checkButtonClick();
        addItemButtonClick();
        saveButtonClick();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, GroceryList.class);
        startActivity(intent);
    }

    private void displayListView() {
        Intent intent = getIntent();
        ArrayList<Item> items = new ArrayList<Item>();
        GList gList = db.getGListByID(gListID);
        TextView textView = (TextView) findViewById(R.id.listTitle);
        textView.setText(gList.getListName() + " List (" + gList.getStoreName() + ")");

        items = db.getItemsByGListNo(gListID);
        //items.add(new Item("Banana", "Produce", 5, false, gList));

        //create an ArrayAdaptar from the String Array
        adapter = new MyCustomAdapter(this,
                R.layout.content_grocery_list, items);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Item item = (Item) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(),
//                        "Clicked on jRow: " + item.getName(),
//                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private class MyCustomAdapter extends ArrayAdapter<Item> {

        private ArrayList<Item> groceryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Item> groceryList) {
            super(context, textViewResourceId, groceryList);
            this.groceryList = new ArrayList<Item>();
            this.groceryList.addAll(groceryList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
            TextView qty;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(content_grocery_list, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.qty = (TextView) convertView.findViewById(R.id.qty);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Item item = (Item) cb.getTag();
//                        Toast.makeText(getApplicationContext(),
//                                "Clicked on Checkbox: " + cb.getText() +
//                                        " is " + cb.isChecked(),
//                                Toast.LENGTH_LONG).show();
                        item.chgCheck(cb.isChecked());
                        db.updateItemsByGListNo(item);
                    }
                });

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Item item = groceryList.get(position);
            holder.code.setText(" (" +  item.getType() + ")");
            holder.name.setText(item.getName());
            holder.name.setChecked(item.isChecked());
            holder.qty.setText(String.valueOf(item.getQty()));
            holder.name.setTag(item);

            return convertView;

        }
    }

    private void checkButtonClick() {

        Button myButton = (Button) findViewById(R.id.clearAll);

        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean hasChanged = false;
                ArrayList<Item> groceryList = adapter.groceryList;
                for(int i=0;i<groceryList.size();i++){
                    Item item = groceryList.get(i);
                    if(item.isChecked()){
                        item.chgCheck(false);
                        hasChanged = true;
                        db.updateItemsByGListNo(item);
                    }
                }
                if (hasChanged) {
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void addItemButtonClick() {

        Button myButton = (Button) findViewById(R.id.addItemG);

        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroceryList.this, NewList.class);
                intent.putExtra("gListID", gListID);
                intent.putExtra("flag", "EDIT");
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveButtonClick() {

        Button myButton = (Button) findViewById(R.id.save);

        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroceryList.this, ListManager.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
