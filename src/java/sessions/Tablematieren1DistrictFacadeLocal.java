/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.TablematiereN1;
import entities.Tablematieren1District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface Tablematieren1DistrictFacadeLocal {

    void create(Tablematieren1District tablematieren1District);

    void edit(Tablematieren1District tablematieren1District);

    void remove(Tablematieren1District tablematieren1District);

    Tablematieren1District find(Object id);

    List<Tablematieren1District> findAll();

    List<Tablematieren1District> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Tablematieren1District> findByDistrictTableniveau1(TablematiereN1 tablematiereN1, District district) throws Exception;

    List<Tablematieren1District> findByTableniveau1(TablematiereN1 tablematiereN1) throws Exception;

    List<Tablematieren1District> findByDistrict(District district) throws Exception;

}
