/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Mouchard;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sessions.MouchardFacadeLocal;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class MouchardController {

    /**
     * Creates a new instance of MouchardController
     */
    
    @EJB
    private MouchardFacadeLocal mouchardFacadeLocal;
    private List<Mouchard> mouchards = new ArrayList<>();

    public MouchardController() {

    }

    public List<Mouchard> getMouchards() {
        mouchards = mouchardFacadeLocal.findAll();
        return mouchards;
    }

    public void setMouchards(List<Mouchard> mouchards) {
        this.mouchards = mouchards;
    }

}
