package services;

import java.util.List;
import model.Giay;

public interface GiayService {

    public List<Giay> getAll();

    public String add(Giay giay);

    public String update(Giay giay);

    public String delete(String id);
}
