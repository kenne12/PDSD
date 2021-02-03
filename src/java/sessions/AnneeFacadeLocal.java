/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface AnneeFacadeLocal {

    void create(Annee annee);

    void edit(Annee annee);

    void remove(Annee annee);

    Annee find(Object id);

    List<Annee> findAll();

    List<Annee> findRange(int[] range);

    int count();

    int nextId();

    List<Annee> findByNom(String nom);

    List<Annee> findByEtatRecette(Boolean etat) throws Exception;

    public List<Annee> findByEtatPlanification(Boolean etat) throws Exception;

    List<Annee> findByEtatDepense(Boolean etat) throws Exception;

    List<Annee> findAllRange();

    public List<Annee> findByEtatChronogramme(Boolean etat) throws Exception;

    List<Annee> findByEtatCibles(Boolean etat);

    List<Annee> findByEtatMape(Boolean etat) throws Exception;

    List<Annee> findByEtatPopulationfosa(Boolean etat) throws Exception;

    List<Annee> findByEtatprojection(Boolean etat);

}
