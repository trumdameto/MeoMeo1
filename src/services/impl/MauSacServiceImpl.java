/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.impl;

import java.util.List;
import model.MauSac;
import repository.MauSacRepository;
import services.MauSacService;

/**
 *
 * @author HÙNG
 */
public class MauSacServiceImpl implements MauSacService{
    
    private MauSacRepository repository = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
        return repository.getAll();
    }

    @Override
    public String add(MauSac mauSac) {
        if (repository.add(mauSac)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(MauSac mauSac) {
        if (repository.update(mauSac)) {
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
