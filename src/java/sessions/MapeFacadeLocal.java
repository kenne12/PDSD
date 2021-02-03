/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mape;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface MapeFacadeLocal {

    void create(Mape mape);

    void edit(Mape mape);

    void remove(Mape mape);

    Mape find(Object id);

    List<Mape> findAll();

    List<Mape> findRange(int[] range);

    int count();

    int nextId();

    public List<Mape> findByNom(String nom);

    public List<Mape> findAllCode();
            
    public List<Mape> findByDistrict(int district);

    List<Mape> findAllRange();

    List<Mape> findAllRangeId();

}
