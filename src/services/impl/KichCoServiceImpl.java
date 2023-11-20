package services.impl;

import java.util.List;
import model.DanhMuc;
import model.KichCo;
import repository.DanhMucRepository;
import repository.KichCoRepository;
import services.DanhMucService;
import services.KichCoService;

public class KichCoServiceImpl implements KichCoService {

    private KichCoRepository repository = new KichCoRepository();

    @Override
    public List<KichCo> getAll() {
        return repository.getAll();
    }

    @Override
    public String delete(String id) {
        if (repository.delete(id)) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }

    @Override
    public String add(KichCo kichCo) {
        if (repository.add(kichCo)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(KichCo kichCo) {
        if (repository.update(kichCo)) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

}
