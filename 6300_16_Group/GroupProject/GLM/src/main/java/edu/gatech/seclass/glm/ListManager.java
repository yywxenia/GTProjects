package edu.gatech.seclass.glm;

/**
 * Created by Chunqing on 10/10/16.
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
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ListManager extends Activity implements AdapterView.OnItemClickListener {

    ListView lv;
    List<GList> lists;
    Context mContext;
    MyListAdapter adapter;
    //List<Integer> listItemID = new ArrayList<Integer>();
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_manager);
        Intent intent = getIntent();
        mContext = getApplicationContext();
        lv = (ListView)findViewById(R.id.list_test);

        lists = getAllLists();
        Collections.sort(lists, new Comparator<GList>() {
            @Override
            public int compare(GList o1, GList o2) {
                int res = o1.getStoreName().compareTo(o2.getStoreName());
                if (res == 0){
                    return o1.getListName().compareTo(o2.getListName());
                }
                return res;
            }
        });
        adapter = new MyListAdapter(this, R.layout.activity_list_manager, lists);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListManager.this, GroceryList.class);
                //intent.putExtra("glist", adapter.glist.get((int)id).getListID());
                intent.putExtra("glistID", adapter.glist.get((int)id).getListID());
                startActivity(intent);
            }
        });

    }

    private List<GList> getAllLists(){
        List<GList> allLists;
        allLists = db.getGLists();
        return allLists;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ListManager.this, GroceryList.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && resultCode == 1001){
            String listname = data.getStringExtra("listname");
            String storename = data.getStringExtra("storename");
            int listID = data.getIntExtra("listID", 0);
            adapter.addList(listname, storename, listID);
        }
    }


    public void newList(View view){
        int newID = adapter.firstAvailableID();
        Intent intent = new Intent(ListManager.this, NewList.class);
        intent.putExtra("gListID", newID);
        intent.putExtra("flag", "NEW");
        startActivityForResult(intent, 1000);

    }

    public void deleteList(View view){
        adapter.deleteList();
    }

    public void item(View view){
        Intent intent = new Intent(ListManager.this, newMasterItem.class);
        startActivity(intent);
    }


    class MyListAdapter extends ArrayAdapter<GList> {
        List<Boolean> mChecked;
        List<GList> glist;
        HashMap<Integer,View> map = new HashMap<Integer,View>();
        boolean[] listIDTable = new boolean[100];

        public MyListAdapter(Context context, int textViewResourceId, List<GList> lists){
            super(context, textViewResourceId, lists);
            glist = lists;

            mChecked = new ArrayList<Boolean>();
            for(int i=0;i<lists.size();i++){
                mChecked.add(false);
                listIDTable[glist.get(i).getListID()-1] = true;
            }
        }

        @Override
        public int getCount() {
            return glist.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addList(String listname, String storename, int listID){
            GList newlist = new GList(listname, storename);
            newlist.setListID(listID);
            db.add_gList(newlist);
            glist.add(newlist);
            mChecked.add(false);
            notifyDataSetChanged();
        }

        public void deleteList(){
            int i = 0;
            while (i < mChecked.size()){
                if (mChecked.get(i)){
                    mChecked.remove(i);
                    listIDTable[glist.get(i).getListID() - 1] = false;
                    db.delete_glist(glist.get(i));
                    glist.remove(i);
                    notifyDataSetChanged();
                } else {
                    i++;
                }
            }
        }

        public int firstAvailableID(){
            int i = 0;
            while (listIDTable[i]){
                i++;
                continue;
            }
            listIDTable[i] = true;
            return i+1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View view;
            ViewHolder holder = null;

            if (map.get(position) == null) {
                Log.e("listManager","position1 = "+position);

                LayoutInflater mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.listnames, null);
                holder = new ViewHolder();
                holder.selected = (CheckBox)convertView.findViewById(R.id.list_select);
                holder.listname = (TextView)convertView.findViewById(R.id.list_name);
                holder.storename = (TextView)convertView.findViewById(R.id.list_store);
                final int p = position;
                map.put(position, convertView);
                holder.selected.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox)v;
                        mChecked.set(p, cb.isChecked());
                    }
                });
                convertView.setTag(holder);
            }else{
                Log.e("listManager","position2 = "+position);
                convertView = map.get(position);
                holder = (ViewHolder)convertView.getTag();
            }

            holder.selected.setChecked(mChecked.get(position));
            holder.listname.setText(glist.get(position).getListName());
            holder.storename.setText(glist.get(position).getStoreName());

            return convertView;
        }

    }

    static class ViewHolder{
        CheckBox selected;
        TextView listname;
        TextView storename;
    }

}
