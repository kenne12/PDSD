/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Hospitalisationdistrict;
import entities.Hospitalisation;
import entities.Rubriquehospitalisation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface HospitalisationdistrictFacadeLocal {

    void create(Hospitalisationdistrict hospitalisationdistrict);

    void edit(Hospitalisationdistrict hospitalisationdistrict);

    void remove(Hospitalisationdistrict hospitalisationdistrict);

    Hospitalisationdistrict find(Object id);

    List<Hospitalisationdistrict> findAll();

    List<Hospitalisationdistrict> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    public List<Hospitalisationdistrict> findByDistrict(int district);

    public List<Hospitalisationdistrict> find(Hospitalisation hospitalisation, Rubriquehospitalisation rubriquehospitalisation) throws Exception;

    public List<Hospitalisationdistrict> find(Hospitalisation hospitalisation) throws Exception;

    List<Hospitalisationdistrict> find(Hospitalisation hospitalisation, Rubriquehospitalisation rubriquehospitalisation, District district) throws Exception;
}
