package services;

import java.util.List;
import model.KieuDang;

public interface KieuDangService {

    public List<KieuDang> getAll();

    public String add(KieuDang kieuDang);

    public String update(KieuDang kieuDang);

    public String delete(String id);
}
