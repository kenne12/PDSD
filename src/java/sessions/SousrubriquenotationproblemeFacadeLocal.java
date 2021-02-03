/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sousrubriquenotationprobleme;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface SousrubriquenotationproblemeFacadeLocal {

    void create(Sousrubriquenotationprobleme sousrubriquenotationprobleme);

    void edit(Sousrubriquenotationprobleme sousrubriquenotationprobleme);

    void remove(Sousrubriquenotationprobleme sousrubriquenotationprobleme);

    Sousrubriquenotationprobleme find(Object id);

    List<Sousrubriquenotationprobleme> findAll();

    List<Sousrubriquenotationprobleme> findRange(int[] range);

    int count();

    int nextId();

    public List<Sousrubriquenotationprobleme> findByNom(String nom);
}
