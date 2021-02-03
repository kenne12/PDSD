/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Morbiditedistrict;
import entities.Morbidite;
import entities.Rubriquemorbidite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hp
 */
@Local
public interface MorbiditedistrictFacadeLocal {

    void create(Morbiditedistrict morbiditedistrict);

    void edit(Morbiditedistrict morbiditedistrict);

    void remove(Morbiditedistrict morbiditedistrict);

    Morbiditedistrict find(Object id);

    List<Morbiditedistrict> findAll();

    List<Morbiditedistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    public List<Morbiditedistrict> find(Morbidite morbidite, Rubriquemorbidite rubriquemorbidite) throws Exception;

    public List<Morbiditedistrict> find(Morbidite morbidite) throws Exception;

    public List<Morbiditedistrict> findByDistrict(int district);

    List<Morbiditedistrict> find(Morbidite morbidite, Rubriquemorbidite rubriquemorbidite, District district) throws Exception;
}
