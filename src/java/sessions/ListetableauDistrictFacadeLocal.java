/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.ListetableauDistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Abdel BEIN-INFO
 */
@Local
public interface ListetableauDistrictFacadeLocal {

    void create(ListetableauDistrict listetableauDistrict);

    void edit(ListetableauDistrict listetableauDistrict);

    void remove(ListetableauDistrict listetableauDistrict);

    ListetableauDistrict find(Object id);

    List<ListetableauDistrict> findAll();

    List<ListetableauDistrict> findRange(int[] range);

    int count();
    
     long nextId();

    public List<ListetableauDistrict> findByDistrict(District district) throws Exception ;

    public List<ListetableauDistrict> findByDistrict(int district);
    
}
