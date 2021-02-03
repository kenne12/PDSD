/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.District;
import entities.Facteur;
import entities.Groupefacteur;
import entities.Typefacteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface FacteurFacadeLocal {

    void create(Facteur facteur);

    void edit(Facteur facteur);

    void remove(Facteur facteur);

    Facteur find(Object id);

    List<Facteur> findAll();

    List<Facteur> findRange(int[] range);

    int count();

    int nextId();

    public List<Facteur> findByNom(String nom);

    public List<Facteur> find(Facteur facteur, Typefacteur typefacteur) throws Exception;

    public List<Facteur> findByDistrict(int district);

    public List<Facteur> findByGroupefacteur(int groupefacteur);

    public List<Facteur> findfacteur(District district) throws Exception;

    public List<Facteur> find(Groupefacteur groupefacteur, Typefacteur typefacteur) throws Exception;

    public List<Facteur> findByGroupefacteurTypefacteur(Facteur facteur, Groupefacteur groupefacteur, Typefacteur typefacteur) throws Exception;
}
