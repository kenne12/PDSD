/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Morbidite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hp
 */
@Local
public interface MorbiditeFacadeLocal {

    void create(Morbidite morbidite);

    void edit(Morbidite morbidite);

    void remove(Morbidite morbidite);

    Morbidite find(Object id);

    List<Morbidite> findAll();

    List<Morbidite> findRange(int[] range);

    int count();

    int nextId();

    public List<Morbidite> findByNom(String nom);

    public List<Morbidite> findByDistrict(int district);

    List<Morbidite> findAllRange();

    public List<Morbidite> findAllRangeCode();

    List<Morbidite> findAllRangeId();
}
