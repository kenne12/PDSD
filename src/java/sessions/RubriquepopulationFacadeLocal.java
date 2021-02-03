/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquepopulation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RubriquepopulationFacadeLocal {

    void create(Rubriquepopulation rubriquepopulation);

    void edit(Rubriquepopulation rubriquepopulation);

    void remove(Rubriquepopulation rubriquepopulation);

    Rubriquepopulation find(Object id);

    List<Rubriquepopulation> findAll();

    List<Rubriquepopulation> findRange(int[] range);

    int count();

    int nextId();

    public List<Rubriquepopulation> findByNom(String nom);

    List<Rubriquepopulation> findAllRange();
}
