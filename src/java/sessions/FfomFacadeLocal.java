/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Ffom;
import entities.Pilier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface FfomFacadeLocal {

    void create(Ffom ffom);

    void edit(Ffom ffom);

    void remove(Ffom ffom);

    Ffom find(Object id);

    List<Ffom> findAll();

    List<Ffom> findRange(int[] range);

    int count();

    int nextId();

    List<Ffom> find(District district, Pilier pilier) throws Exception;
    
    List<Ffom> find(District district) throws Exception;
}
