/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import java.util.List;
import model.Giay;
import repository.GiayRepository;
import services.GiayService;

/**
 *
 * @author HÙNG
 */
public class GiayServiceImpl implements GiayService {

    private GiayRepository repository = new GiayRepository();

    @Override
    public List<Giay> getAll() {
        return repository.getAll();
    }

    @Override
    public String add(Giay giay) {
        if (repository.add(giay)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(Giay giay) {
        if (repository.update(giay)) {
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
