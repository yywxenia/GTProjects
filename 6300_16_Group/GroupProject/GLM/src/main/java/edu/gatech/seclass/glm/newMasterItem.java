package edu.gatech.seclass.glm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class newMasterItem extends Activity {
    EditText itemName;
    EditText itemType;
    DBHandler db = new DBHandler(this);
    List<MasterItem> allItemlists;
    ListView lv;
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_master_item);
        lv = (ListView)findViewById(R.id.allitemlistview);
        allItemlists = new ArrayList<>();
        allItemlists = db.searchItems("");
        adapter = new MyListAdapter(this, R.layout.activity_new_master_item, allItemlists);
        lv.setAdapter(adapter);

        itemName = (EditText)findViewById(R.id.editText3);
        itemType = (EditText)findViewById(R.id.editText4);
    }

    public void addtoDB(View view){
        if (itemName.getText() != null && itemType.getText() != null){
            MasterItem item = new MasterItem(itemName.getText().toString().trim(), itemType.getText().toString().trim());
            db.newMasterItem(item);
            finish();
        }
    }

    class MyListAdapter extends ArrayAdapter<MasterItem> {
        List<MasterItem> itemList;
        HashMap<Integer,View> map = new HashMap<Integer,View>();

        public MyListAdapter(Context context, int textViewResourceId, List<MasterItem> itemList){
            super(context, textViewResourceId, itemList);

            this.itemList = itemList;

        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItemToList(List<MasterItem> searchList){

//            db.add_gList(newItem);
            this.itemList = searchList;
            notifyDataSetChanged();
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View view;
            SearchItem.ViewHolder holder = null;

            if (map.get(position) == null) {
                Log.e("listManager","position1 = "+position);

                LayoutInflater mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.searchresultitemview, null);
                holder = new SearchItem.ViewHolder();
                holder.name = (TextView)convertView.findViewById(R.id.searchresult_name);
                holder.type = (TextView)convertView.findViewById(R.id.searchresult_type);

                final int p = position;
                map.put(position, convertView);

                convertView.setTag(holder);
            }else{
                Log.e("NewList","position2 = "+position);
                convertView = map.get(position);
                holder = (SearchItem.ViewHolder)convertView.getTag();
            }

            holder.name.setText(itemList.get(position).getName());
            holder.type.setText(itemList.get(position).getType());
            return convertView;
        }

    }

    static class ViewHolder {
        TextView name;
        TextView type;

    }
}
