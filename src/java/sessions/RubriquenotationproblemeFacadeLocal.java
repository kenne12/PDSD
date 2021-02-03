/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquenotationprobleme;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RubriquenotationproblemeFacadeLocal {

    void create(Rubriquenotationprobleme rubriquenotationprobleme);

    void edit(Rubriquenotationprobleme rubriquenotationprobleme);

    void remove(Rubriquenotationprobleme rubriquenotationprobleme);

    Rubriquenotationprobleme find(Object id);

    List<Rubriquenotationprobleme> findAll();

    List<Rubriquenotationprobleme> findRange(int[] range);

    int count();
    
    int nextId();
    
    public List<Rubriquenotationprobleme> findByNom(String nom);
    
}
