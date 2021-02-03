/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Acteursfacteurs;
import entities.District;
import entities.Ffom;
import entities.Opportunite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface OpportuniteFacadeLocal {

    void create(Opportunite opportunite);

    void edit(Opportunite opportunite);

    void remove(Opportunite opportunite);

    Opportunite find(Object id);

    List<Opportunite> findAll();

    List<Opportunite> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    public List<Opportunite> find(Opportunite opportunite, Acteursfacteurs acteursfacteurs, District district) throws Exception;

    public List<Opportunite> findByNom(String nom);

    public List<Opportunite> findByDistrict(int district);

    List<Opportunite> findByFfom(Ffom ffom) throws Exception;
}
