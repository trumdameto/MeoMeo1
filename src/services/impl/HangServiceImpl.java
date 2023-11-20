package services.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Hang;
import repository.HangRepository;
import services.HangService;

/**
 *
 * @author HÙNG
 */
public class HangServiceImpl implements HangService {

    private HangRepository repository = new HangRepository();

    @Override
    public List<Hang> getAll() {
        return repository.getAll();
    }

    @Override
    public String add(Hang hang) {
        if (repository.add(hang)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(Hang hang) {
        if (repository.update(hang)) {
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

//    @Override
//    public Hang search(String name) {
//        if(repository.search(name)){
//            return "Tìm thấy"''
//        }
//        return "Ko thấy";
//    }

}
