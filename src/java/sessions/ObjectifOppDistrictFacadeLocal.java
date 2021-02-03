/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Interventionpnds;
import entities.ObjectifOppDistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ObjectifOppDistrictFacadeLocal {

    void create(ObjectifOppDistrict objectifOppDistrict);

    void edit(ObjectifOppDistrict objectifOppDistrict);

    void remove(ObjectifOppDistrict objectifOppDistrict);

    ObjectifOppDistrict find(Object id);

    List<ObjectifOppDistrict> findAll();

    List<ObjectifOppDistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<ObjectifOppDistrict> findByDistrict(District district) throws Exception;

    List<ObjectifOppDistrict> findByDistrict(District district, Interventionpnds interventionpnds) throws Exception;

}
