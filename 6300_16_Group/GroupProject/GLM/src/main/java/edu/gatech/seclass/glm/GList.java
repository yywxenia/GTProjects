package edu.gatech.seclass.glm;

import java.io.Serializable;

/**
 * Created by Chunqing on 10/11/16.
 */
public class GList {
    private String listName;
    private String storeName;
    private int listID;

    public GList() {}
    public GList(String listName, String storeName){
        this.listName = listName;
        this.storeName = storeName;
    }

    public String getListName(){
        return this.listName;
    }

    public String getStoreName(){
        return this.storeName;
    }

    public int getListID(){ return this.listID;}

    public void setListName(String listName){
        this.listName = listName;
    }

    public void setStoreName(String storeName){
        this.storeName = storeName;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

}
