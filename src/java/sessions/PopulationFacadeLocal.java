/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.District;
import entities.Population;
import entities.Rubriquepopulation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PopulationFacadeLocal {

    void create(Population population);

    void edit(Population population);

    void remove(Population population);

    Population find(Object id);

    List<Population> findAll();

    List<Population> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<Population> find(District district, Rubriquepopulation rubriquepopulation, Annee annee) throws Exception;
    
    List<Population> find(District district) throws Exception;

    List<Population> find(District district, Annee annee) throws Exception;

}
