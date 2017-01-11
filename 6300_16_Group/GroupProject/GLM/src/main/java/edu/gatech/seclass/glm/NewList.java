package edu.gatech.seclass.glm;

/**
 * Created by Chunqing on 10/15/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewList extends Activity implements AdapterView.OnItemClickListener {
    ListView lv;
    Context mContext;
    MyListAdapter adapter;
    DBHandler db = new DBHandler(this);
    List<Item> lists;
    EditText listname;
    EditText storename;
    int listID;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        mContext = getApplicationContext();
        lv = (ListView)findViewById(R.id.itemlistview);
        lists = new ArrayList<>();
        Intent intent = getIntent();
        listID = intent.getIntExtra("gListID", 0);
        flag = intent.getStringExtra("flag");
        lists = db.getItemsByGListNo(listID);
        System.out.println(flag);

        listname = (EditText)findViewById(R.id.editText);
        storename = (EditText)findViewById(R.id.editText2);
        if (flag.equals("EDIT")){
            GList gList = db.getGListByID(listID);
            listname.setText(gList.getListName());
            storename.setText(gList.getStoreName());
        }

        adapter = new MyListAdapter(this, R.layout.activity_new_list, lists);
        lv.setAdapter(adapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(NewList.this, TypeSelector.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2000 && resultCode == 2001){
            String itemName = data.getStringExtra("itemName");
            String itemType = data.getStringExtra("itemType");
            adapter.addItemToList(itemName, itemType, 1, false, listID);
        }
        if (requestCode == 2100 && resultCode == 2101){
            String itemName = data.getStringExtra("itemName");
            String itemType = data.getStringExtra("itemType");
            adapter.addItemToList(itemName, itemType, 1, false, listID);
        }
    }

    public void add(View view){
        Intent intent = new Intent(NewList.this, TypeSelector.class);
        startActivityForResult(intent, 2000);
    }

    public void delete(View view){
        adapter.deleteItem();
    }

    public void done(View view){
        String lname = listname.getText().toString().trim();
        String sname = storename.getText().toString().trim();
        if (lname.length() == 0 || sname.length() == 0){
            Toast.makeText(getApplicationContext(), "Please enter list name and store name", Toast.LENGTH_LONG).show();
        } else {
            for (Item newItem : adapter.newItemList) {
                db.addItemsByGListNo(newItem);
            }
            if (flag.equals("NEW")) {
                Intent intent = new Intent();
                intent.putExtra("listname", lname);
                intent.putExtra("storename", sname);
                intent.putExtra("listID", listID);
                setResult(1001, intent);
                finish();
            } else if (flag.equals("EDIT")) {
                db.update_gList(listID, lname, sname);
                Intent intent = new Intent(NewList.this, GroceryList.class);
                intent.putExtra("glistID", listID);
                startActivity(intent);
                finish();
            }
        }
    }

    public void search(View view){
        Intent intent = new Intent(NewList.this, SearchItem.class);
        startActivityForResult(intent, 2100);
    }

    class MyListAdapter extends ArrayAdapter<Item> {
        List<Boolean> iChecked;
        List<Item> groceryList;
        List<Item> newItemList = new ArrayList<>();
        HashMap<Integer,View> map = new HashMap<Integer,View>();


        public MyListAdapter(Context context, int textViewResourceId, List<Item> groceryList){
            super(context, textViewResourceId, groceryList);
            this.groceryList = new ArrayList<Item>();
            this.groceryList.addAll(groceryList);

            iChecked = new ArrayList<Boolean>();
            for(int i=0;i<groceryList.size();i++){
                iChecked.add(false);
            }
        }

        @Override
        public int getCount() {
            return groceryList.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItemToList(String name, String type, int qty, boolean isChecked, int gListID){
            Item newItem = new Item(name, type, qty, isChecked, gListID);
            ArrayList<Item> items = db.getItemsByGListNo(gListID);
            boolean isAlreadyOnList = false;
            for (int i = 0; i < items.size(); i++) {
                if ((name.trim().equals(items.get(i).getName().trim())) &&
                        (type.trim().equals(items.get(i).getType().trim()))) {
                    isAlreadyOnList = true;
                }
            }
            if (isAlreadyOnList) {
                Toast.makeText(getApplicationContext(),
                        "Item already on list.",
                        Toast.LENGTH_SHORT).show();
            } else {
                groceryList.add(newItem);
                newItemList.add(newItem);
                iChecked.add(false);
                notifyDataSetChanged();
            }
        }

        public void deleteItem(){
            int i = 0;
            while (i < iChecked.size()){
                if (iChecked.get(i)){
                    iChecked.remove(i);
                    db.deleteItemByGListNo(groceryList.get(i));
                    groceryList.remove(i);
                    notifyDataSetChanged();
                } else {
                    i++;
                }
            }
        }



        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
//            View view;
            ViewHolder holder = null;

            if (map.get(position) == null) {
                Log.e("listManager","position1 = "+position);

                LayoutInflater mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.newlistitemview, null);
                holder = new ViewHolder();
                holder.selected = (CheckBox)convertView.findViewById(R.id.itemlist_select);
                holder.name = (TextView)convertView.findViewById(R.id.itemlist_name);
                holder.type = (TextView)convertView.findViewById(R.id.itemlist_type);
                holder.qty = (TextView) convertView.findViewById(R.id.itemlist_qty);
                final int p = position;
                map.put(position, convertView);
                holder.selected.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox)v;
                        iChecked.set(p, cb.isChecked());
                    }
                });
                convertView.setTag(holder);
            }else{
                Log.e("NewList","position2 = "+position);
                convertView = map.get(position);
                holder = (ViewHolder)convertView.getTag();
            }

            holder.selected.setChecked(iChecked.get(position));
            holder.name.setText(groceryList.get(position).getName());
            holder.type.setText(groceryList.get(position).getType());
            holder.qty.setText(String.valueOf(groceryList.get(position).getQty()));

            holder.add1 = (Button) convertView.findViewById(R.id.add1);
            holder.sub1 = (Button) convertView.findViewById(R.id.sub1);

            holder.add1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Item item = groceryList.get(position);
                int num = item.getQty();
                num++;
                item.setQty(num);
                db.updateItemsByGListNo(item);
                notifyDataSetChanged();
                }
            });

            holder.sub1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                Item item = groceryList.get(position);
                int num = item.getQty();
                if (num > 1){
                    num--;
                    item.setQty(num);
                    db.updateItemsByGListNo(item);
                    notifyDataSetChanged();
                } else if (num == 1){
                    Toast.makeText(getApplicationContext(), "Item qty cannot be 0. Please delete the item.", Toast.LENGTH_LONG).show();
                }

                }
            });

            return convertView;
        }

    }

    static class ViewHolder {
        CheckBox selected;
        TextView name;
        TextView type;
        TextView qty;
        Button add1;
        Button sub1;
    }
}
