/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Pilier;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PilierFacadeLocal {

    void create(Pilier pilier);

    void edit(Pilier pilier);

    void remove(Pilier pilier);

    Pilier find(Object id);

    List<Pilier> findAll();

    List<Pilier> findRange(int[] range);

    int count();
    
}
