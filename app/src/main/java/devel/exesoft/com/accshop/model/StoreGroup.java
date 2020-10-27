package devel.exesoft.com.accshop.model;

public class StoreGroup {
    private String name;
    private int amount;
    private  String unit_string;

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit_string() {
        return unit_string;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit_string(String unit_string) {
        this.unit_string = unit_string;
    }
}
