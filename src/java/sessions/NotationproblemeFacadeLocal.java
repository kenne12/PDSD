/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Medicamenttraceur;
import entities.Notationprobleme;
import entities.Probleme;
import entities.Sousrubriquenotationprobleme;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface NotationproblemeFacadeLocal {

    void create(Notationprobleme notationprobleme);

    void edit(Notationprobleme notationprobleme);

    void remove(Notationprobleme notationprobleme);

    Notationprobleme find(Object id);

    List<Notationprobleme> findAll();

    List<Notationprobleme> findRange(int[] range);

    int count();
    
    Long nextId() throws Exception;

    List<Notationprobleme> find(Probleme probleme) throws Exception;
    
    List<Notationprobleme> find(Probleme probleme,Sousrubriquenotationprobleme sousrubriquenotationprobleme) throws Exception;

    List<Notationprobleme> find(Structure structure, Annee annee) throws Exception;

    List<Notationprobleme> find(Structure structure, Medicamenttraceur medicamenttraceur, Annee annee) throws Exception;

}
