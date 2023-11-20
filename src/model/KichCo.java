package model;

import java.util.UUID;

public class KichCo {

    private String id;
    private Integer size;

    public KichCo() {
    }

    public KichCo(Integer size) {
        this.size = size;
    }

    public KichCo(String id, Integer size) {
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "KichCo{" + "id=" + id + ", size=" + size + '}';
    }

}
