/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.TablematiereN3;
import entities.Tablematieren3District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface Tablematieren3DistrictFacadeLocal {

    void create(Tablematieren3District tablematieren3District);

    void edit(Tablematieren3District tablematieren3District);

    void remove(Tablematieren3District tablematieren3District);

    Tablematieren3District find(Object id);

    List<Tablematieren3District> findAll();

    List<Tablematieren3District> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Tablematieren3District> findByDistrictTableniveau3(TablematiereN3 tablematiereN3, District district) throws Exception;

    List<Tablematieren3District> findByTableniveau3(TablematiereN3 tablematiereN3) throws Exception;

    List<Tablematieren3District> findByDistrict(District district) throws Exception;

}
