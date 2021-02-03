/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Analysefinanciere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface AnalysefinanciereFacadeLocal {

    void create(Analysefinanciere analysefinanciere);

    void edit(Analysefinanciere analysefinanciere);

    void remove(Analysefinanciere analysefinanciere);

    Analysefinanciere find(Object id);

    List<Analysefinanciere> findAll();

    List<Analysefinanciere> findRange(int[] range);

    int count();
    
}
