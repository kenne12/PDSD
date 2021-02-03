/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Activite;
import entities.ActiviteStructure;
import entities.Annee;
import entities.Probleme;
import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ActiviteStructureFacadeLocal {

    void create(ActiviteStructure activiteStructure);

    void edit(ActiviteStructure activiteStructure);

    void remove(ActiviteStructure activiteStructure);

    ActiviteStructure find(Object id);

    List<ActiviteStructure> findAll();

    List<ActiviteStructure> findRange(int[] range);

    int count();

    Long nextId() throws Exception;

    List<ActiviteStructure> find(Probleme probleme) throws Exception;

    List<ActiviteStructure> find(Activite activite) throws Exception;

    List<ActiviteStructure> find(Structure structure, Annee annee) throws Exception;

    List<ActiviteStructure> find(Structure structure, Boolean etat) throws Exception;

    List<ActiviteStructure> find(Structure structure, Activite activite) throws Exception;

}
