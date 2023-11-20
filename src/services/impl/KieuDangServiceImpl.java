package services.impl;

import java.util.List;
import model.KieuDang;
import repository.KieuDangRepository;
import services.KieuDangService;

public class KieuDangServiceImpl implements KieuDangService {

    private KieuDangRepository repository = new KieuDangRepository();

    @Override
    public List<KieuDang> getAll() {
        return repository.getAll();
    }

    @Override
    public String add(KieuDang kieuDang) {
        if (repository.add(kieuDang)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(KieuDang kieuDang) {
        if (repository.update(kieuDang)) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(String id) {
        if (repository.delete(id)) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }

}
