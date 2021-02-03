/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteurdistrict;
import entities.District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ActeurdistrictFacadeLocal {

    void create(Acteurdistrict acteurdistrict);

    void edit(Acteurdistrict acteurdistrict);

    void remove(Acteurdistrict acteurdistrict);

    Acteurdistrict find(Object id);

    List<Acteurdistrict> findAll();

    List<Acteurdistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Acteurdistrict> findByDistrict(District district) throws Exception;

    List<Acteurdistrict> findByDistrict(int district);

}
