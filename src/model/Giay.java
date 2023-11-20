package model;

import java.util.UUID;

public class Giay {

    private String id;
    private String ma;
    private String name;

    public Giay() {
    }

    public Giay(String ma, String name) {
        this.ma = ma;
        this.name = name;
    }

    public Giay(String id, String ma, String name) {
        this.id = id;
        this.ma = ma;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Giay{" + "id=" + id + ", ma=" + ma + ", name=" + name + '}';
    }

}
