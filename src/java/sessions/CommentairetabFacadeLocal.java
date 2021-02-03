/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Annee;
import entities.Commentairetab;
import entities.District;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface CommentairetabFacadeLocal {

    void create(Commentairetab commentairetab);

    void edit(Commentairetab commentairetab);

    void remove(Commentairetab commentairetab);

    Commentairetab find(Object id);

    List<Commentairetab> findAll();

    List<Commentairetab> findRange(int[] range);

    int count();

    Integer nextId() throws Exception;

    List<Commentairetab> find(District district, Integer numero) throws Exception;

    List<Commentairetab> find(District district, Annee annee, Integer numero) throws Exception;

    List<Commentairetab> find(District district) throws Exception;

}
