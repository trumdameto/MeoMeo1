/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import model.GiayChiTiet;

/**
 *
 * @author HÙNG
 */
public interface GiayChiTietService {

    public List<GiayChiTiet> getAll();
    
    public List<GiayChiTiet> getAllByGiay(String idGiay);

    public String add(GiayChiTiet giayChiTiet);

    public String update(GiayChiTiet giayChiTiet);

    public String delete(String id);
}
