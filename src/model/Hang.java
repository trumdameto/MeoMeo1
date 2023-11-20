package model;

import java.util.UUID;

public class Hang {

    private String id;
    private String name;

    public Hang() {
    }

    public Hang(String name) {
        this.name = name;
    }

    public Hang(String id, String name) {
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
        return "Hang{" + "id=" + id + ", name=" + name + '}';
    }

}
