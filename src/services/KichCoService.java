package services;

import java.util.List;
import model.KichCo;

public interface KichCoService {

    public List<KichCo> getAll();

    public String add(KichCo kichCo);

    public String update(KichCo kichCo);

    public String delete(String id);
}
