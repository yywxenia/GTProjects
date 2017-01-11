package edu.gatech.seclass.glm;

/**
 * Created by Jeremy on 10/14/16.
 */

public class MasterItem {
    String name;
    String itemType;

    public MasterItem(String name, String itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.itemType = type;
    }

    public String getName() { return this.name; }
    public String getType() { return this.itemType; }
}
