/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.TablematiereN2;
import entities.Tablematieren2District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface Tablematieren2DistrictFacadeLocal {

    void create(Tablematieren2District tablematieren2District);

    void edit(Tablematieren2District tablematieren2District);

    void remove(Tablematieren2District tablematieren2District);

    Tablematieren2District find(Object id);

    List<Tablematieren2District> findAll();

    List<Tablematieren2District> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Tablematieren2District> findByDistrictTableniveau2(TablematiereN2 tablematiereN2, District district) throws Exception;

    List<Tablematieren2District> findByTableniveau2(TablematiereN2 tablematiereN2) throws Exception;

    List<Tablematieren2District> findByDistrict(District district) throws Exception;

}
