package edu.gatech.seclass.glm;

/**
 * Created by Jeremy on 10/11/16.
 */
public class Item {

    private int qty;
    private boolean isChecked;
    private MasterItem masterItem;
    private int gListNo;

    public Item(String name, String type, int qty, boolean isChecked, int gListNo) {
        this.masterItem = new MasterItem(name, type);
        this.qty = qty;
        this.isChecked = isChecked;
        this.gListNo = gListNo;
    }

    public Item(String name, String type, int qty, boolean isChecked) {
        this.masterItem = new MasterItem(name, type);
        this.qty = qty;
        this.isChecked = isChecked;
    }

    public String getName() {
        return this.masterItem.name;
    }

    public int getgListNo() { return this.gListNo; }

    public void setgListNo(int gListNo) { this.gListNo = gListNo; }

    public void changeQty(int qty) {
        this.qty = qty;
    }

    public void chgCheck(boolean state) {
        this.isChecked = state;
    }

    public void changeName(String name) {
        this.masterItem.setName(name);
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public String getType() {
        return this.masterItem.getType();
    }

    public int getQty() { return this.qty; }

    public void setQty(int qty) {this.qty = qty; }

}
