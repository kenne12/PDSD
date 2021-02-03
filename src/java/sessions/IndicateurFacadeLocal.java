/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categorieintervention;
import entities.Indicateur;
import entities.Interventionpnds;
import entities.Niveaucollecte;
import entities.Periodicite;
import entities.Sousaxe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface IndicateurFacadeLocal {

    void create(Indicateur indicateur);

    void edit(Indicateur indicateur);

    void remove(Indicateur indicateur);

    Indicateur find(Object id);

    List<Indicateur> findAll();

    List<Indicateur> findRange(int[] range);

    int count();

    int nextId();

    List<Indicateur> findByNom(String nom);

    List<Indicateur> findByCategorie(int categorie) throws Exception;

    List<Indicateur> find(Periodicite periodicite) throws Exception;

    List<Indicateur> findByCategorieIntervention(Categorieintervention categorieintervention, Niveaucollecte niveaucollecte) throws Exception;

    List<Indicateur> findAllRange();

    List<Indicateur> findBySousAxeNiveauCollecte(Sousaxe sousaxe, Integer niveaucollecte) throws Exception;

    List<Indicateur> findByNiveauCollecte(Integer niveaucollecte) throws Exception;

    List<Indicateur> findByIntervention(Interventionpnds interventionpnds) throws Exception;

}
