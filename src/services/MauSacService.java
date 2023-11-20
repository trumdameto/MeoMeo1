package services;

import java.util.List;
import model.MauSac;

public interface MauSacService {

    public List<MauSac> getAll();

    public String add(MauSac mauSac);

    public String update(MauSac mauSac);

    public String delete(String id);
}
