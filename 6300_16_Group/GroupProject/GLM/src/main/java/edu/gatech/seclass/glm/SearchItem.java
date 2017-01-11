package edu.gatech.seclass.glm;
/**
 * Created by Chunqing on 10/16/16.
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchItem extends Activity {
    ListView lv;
    MyListAdapter adapter;
    DBHandler db = new DBHandler(this);
    List<MasterItem> searchlists;
    EditText searchName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        searchName = (EditText)findViewById(R.id.searchName);
        lv = (ListView)findViewById(R.id.searchitemlistview);
        searchlists = new ArrayList<>();
        searchlists = db.searchItems("");

        adapter = new MyListAdapter(this, R.layout.activity_search_item, searchlists);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("itemName", adapter.searchList.get((int) id).getName());
                intent.putExtra("itemType", adapter.searchList.get((int) id).getType());
                setResult(2101, intent);
                finish();
            }
        });
    }

    public void search(View view){
        if (searchName.getText() != null){
            searchlists = db.searchItems(searchName.getText().toString());
            if (searchlists.size() == 0){
                Toast.makeText(getApplicationContext(), "Cannot find item. Please use \"Add New\" to add it to the database.", Toast.LENGTH_LONG).show();
            } else {
                adapter.addItemToList(searchlists);
            }
        }
    }

    public void addNew(View view){
        Intent intent = new Intent(SearchItem.this, newMasterItem.class);
        startActivity(intent);
    }


    class MyListAdapter extends ArrayAdapter<MasterItem> {
        List<MasterItem> searchList;
        HashMap<Integer,View> map = new HashMap<Integer,View>();

        public MyListAdapter(Context context, int textViewResourceId, List<MasterItem> searchList){
            super(context, textViewResourceId, searchList);

            this.searchList = searchList;

        }

        @Override
        public int getCount() {
            return searchList.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItemToList(List<MasterItem> searchList){

//            db.add_gList(newItem);
            this.searchList = searchList;
            notifyDataSetChanged();
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View view;
            ViewHolder holder = null;

            if (map.get(position) == null) {
                Log.e("listManager","position1 = "+position);

                LayoutInflater mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.searchresultitemview, null);
                holder = new ViewHolder();
                holder.name = (TextView)convertView.findViewById(R.id.searchresult_name);
                holder.type = (TextView)convertView.findViewById(R.id.searchresult_type);

                final int p = position;
                map.put(position, convertView);

                convertView.setTag(holder);
            }else{
                Log.e("NewList","position2 = "+position);
                convertView = map.get(position);
                holder = (ViewHolder)convertView.getTag();
            }

            holder.name.setText(searchList.get(position).getName());
            holder.type.setText(searchList.get(position).getType());
            return convertView;
        }

    }

    static class ViewHolder {
        TextView name;
        TextView type;

    }

}
