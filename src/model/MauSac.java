package model;

import java.util.UUID;

public class MauSac {

    private String id;
    private String name;

    public MauSac() {
    }

    public MauSac(String name) {
        this.name = name;
    }

    public MauSac(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MauSac{" + "id=" + id + ", name=" + name + '}';
    }

}
