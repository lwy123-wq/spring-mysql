package entity;

import java.io.Serializable;

public class Goods implements Serializable {
    private static final long serialVersionUID = 994946692859371555L;
    private int id;
    private String goodsName;
    private String warehouse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
}
