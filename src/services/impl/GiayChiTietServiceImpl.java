package services.impl;

import java.util.List;
import model.GiayChiTiet;
import repository.GiayChiTietRepository;
import services.GiayChiTietService;

public class GiayChiTietServiceImpl implements GiayChiTietService {
    
    private GiayChiTietRepository repository = new GiayChiTietRepository();
    
    @Override
    public List<GiayChiTiet> getAll() {
        return repository.getAll();
    }
    
    @Override
    public String add(GiayChiTiet giayChiTiet) {
        if (repository.add(giayChiTiet)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }
    
    @Override
    public String update(GiayChiTiet giayChiTiet) {
        if (repository.update(giayChiTiet)) {
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
    
    @Override
    public List<GiayChiTiet> getAllByGiay(String idGiay) {
        return repository.getAllByGiay(idGiay);
    }
    
}
