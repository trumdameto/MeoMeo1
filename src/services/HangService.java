/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import model.Hang;

/**
 *
 * @author HÙNG
 */
public interface HangService {

    public List<Hang> getAll();

    public String add(Hang hang);

    public String update(Hang hang);

    public String delete(String id);
    
//    public Hang search(String name);

}
