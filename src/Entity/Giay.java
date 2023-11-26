package Entity;

public class Giay {
    private String id,ma,name;

    public Giay() {
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
        return name ;
    }
}
