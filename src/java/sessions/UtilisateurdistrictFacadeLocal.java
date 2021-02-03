/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateurdistrict;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface UtilisateurdistrictFacadeLocal {

    void create(Utilisateurdistrict utilisateurdistrict);

    void edit(Utilisateurdistrict utilisateurdistrict);

    void remove(Utilisateurdistrict utilisateurdistrict);

    Utilisateurdistrict find(Object id);

    List<Utilisateurdistrict> findAll();

    List<Utilisateurdistrict> findRange(int[] range);

    int count();
    
    
    Long nextId();

    Utilisateurdistrict findByUtilisateurDistrict(int utilisateur, int district);

    List<Utilisateurdistrict> get();
    
    List<Utilisateurdistrict>findByUtilisateur(int utilisateur);

    List<Utilisateurdistrict> findAllRange();
}
