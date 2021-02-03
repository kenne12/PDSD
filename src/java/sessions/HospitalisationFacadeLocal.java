/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Hospitalisation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface HospitalisationFacadeLocal {

    void create(Hospitalisation hospitalisation);

    void edit(Hospitalisation hospitalisation);

    void remove(Hospitalisation hospitalisation);

    Hospitalisation find(Object id);

    List<Hospitalisation> findAll();

    List<Hospitalisation> findRange(int[] range);

    int count();

    int nextId();

    public List<Hospitalisation> findAllCode();

    public List<Hospitalisation> findByNom(String nom);

    List<Hospitalisation> findAllRange();

    List<Hospitalisation> findAllRangeId();
}
