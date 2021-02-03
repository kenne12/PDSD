/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import controllers.LoginController;
import entities.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Properties;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessions.PrivilegeFacadeLocal;

/**
 *
 * @author kenne
 */
@WebFilter(filterName = "FilterPrivellege", urlPatterns = {"/faces/pays/*","/faces/region/*","/faces/departement/*"
        ,"/faces/arrondissement/*","/faces/district/*","/faces/airesante/*","/faces/categoriestructurecom/*","/faces/quartier/*","/faces/rue/*","/faces/annee/*","/faces/annee/*","/faces/periodicite/*"
        ,"/faces/periode/*","/faces/unite/*","/faces/typestructure/*","/faces/structure/*","/faces/statutstructure/*",
        "/faces/axe/*","/faces/sousaxe/*","/faces/categorieintervention/*","/faces/interventionpnds/*","/faces/categorieindicateur/*","/faces/niveaucollecte/*",
        "/faces/periodicite/*","/faces/unite/*","/faces/indicateur/*","/faces/rubriquemortalite/*","/faces/rubriquemorbidite/*","/faces/rubriquehospitalisation/",
        "/faces/profilpersonnel/*","/faces/typestructurecommunautaire/*","/faces/etatfonctstructurecommunautaire/*","/faces/parametrecout-infrastructure/*",
        "/faces/typeinfrastructuretypestructure/*","/faces/typeinfrastructure/*","/faces/etatinfrastructure/*","/faces/parametrecout-equipement/*","/faces/typestructuretypeequipement/*"
        ,"/faces/typeequipementtraceur/*","/faces/etatequipement/*","/faces/typerecette/*","/faces/sourcefinancement/*","/faces/naturedepense/*","/faces/sourceapprovisionnement/*"
        ,"/faces/medicamenttraceur/*","/faces/rubriqueinfosanitaire/*","/faces/rubriquegouvernance/*",
        "/faces/facteur/*","/faces/typefacteur/*","/faces/groupefacteur/*","/faces/acteur/*","/faces/groupeacteur/*","/faces/typeacteur/*"
        ,"/faces/rubriquepopulation/*","/faces/annee/*","/faces/typeactivite/","/faces/sousrubriquenotationprobleme/","/rubriquenotationprobleme/"
        ,"/faces/tablematiere_n1/*","/faces/tablematiere_n2/*","/faces/tablematiere_n3/*","/faces/tablematiere_n1_district/*","/faces/tablematiere_n2_district/*","/faces/tablematiere_n3_district/*"
        ,"/faces/partiehaute/*","/faces/etatlieux-population/index.xhtml","/faces/populationfosa/index.xhtml","/faces/acteurdistrict/*","/faces/facteurdistrict/*"
        ,"/faces/etatlieux-profilepidemiologique/index.xhtml","/faces/etatlieux-profilepidemiologique/index2.xhtml","/faces/etatlieux-profilepidemiologique/index3.xhtml"
        ,"/faces/etatlieux-profilepidemiologique/index1.xhtml","/faces/etatlieuxgouvernance/*","/faces/etatlieuxinfosanitaire/*","/faces/etatlieux-ressources/index5.xhtml","/faces/etatlieux-ressources/index6.xhtml"
        ,"/faces/etatlieux-ressources/index2.xhtml","/faces/etatlieux-ressources/List.xhtml","/faces/etatlieux-ressources/index7.xhtml","/faces/etatlieux-ressources/index.xhtml"
        ,"/faces/ffom-rh/*","/faces/ffom-equipement/*","/faces/ffom-finance/*","/faces/ffom-is/*","/faces/ffom-medicament/*","/faces/ffom-gouvernance/*"
        ,"/faces/performance-district/*","/PDSD/faces/notationprobleme/*","/PDSD/faces/cible/*","/PDSD/faces/activitecorrectrice/*","/PDSD/faces/activiteconsolidation/*","/PDSD/faces/chronogramme/","/PDSD/faces/imprimer-pdsd/"
        ,"/PDSD/faces/imprimer-besoininvestissement/*","/PDSD/faces/imprimer-besoinequipement/*"
        ,"/faces/indicateur/*","/faces/activiteconsolidation/*","/faces/activitecorrectrice/*","/faces/chronogramme/*"
        ,"/faces/partenaireinnovation/*","/faces/privilegedistrict/*","/faces/activercompte/*","/faces/desactivercompte/*","/PDSD/faces/mouchard/*"
        ,"/faces/actioncorrectrice/*","/faces/groupeutilisateur/*","/faces/utilisateur/*","/faces/compteutilisateur/*","/faces/privileg/*","/faces/mouchard/*"
        })
public class FilterPrivellege implements Filter, Serializable {

    Properties properties;
    private static final boolean debug = true;

    Utilisateur utilisateur = new Utilisateur();

    @EJB
    private PrivilegeFacadeLocal privilegeFacadeLocal;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterPrivellege() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterPrivellege:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterPrivellege:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest hRequest = (HttpServletRequest) request;
        HttpServletResponse hResponse = (HttpServletResponse) response;
        HttpSession session = hRequest.getSession();
        System.out.println("====>>>>>> url = " + hRequest.getRequestURI());
        if (session.getAttribute("login") != null) {

            if (session.getAttribute("user") != null) {
                utilisateur = (Utilisateur) session.getAttribute("user");
            }
            if (LoginController.privilegeTotal.contains(hRequest.getRequestURI())) {
                if (!LoginController.privilegeUser.isEmpty()) {
                    if (LoginController.privilegeUser.contains(hRequest.getRequestURI())) {
                        System.err.println("accès réussie");
                        chain.doFilter(request, response);
                    } else {
                        System.err.println("vous n avez pas l'acces");
                        request.getRequestDispatcher("/faces/erreuracces.xhtml").forward(request, response);
                    }
                } else {
                    System.err.println("utilsateur sans privilege");
                    request.getRequestDispatcher("/faces/erreuracces.xhtml").forward(request, response);
                }
            } else {
                System.err.println("ressource sans privilege");
                chain.doFilter(request, response);
            }
        } else {
            request.getRequestDispatcher("/faces/login.xhtml").forward(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterPrivellege:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterPrivellege()");
        }
        StringBuilder sb = new StringBuilder("FilterPrivellege(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {

    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    public String getPropertyValue(String key) {
        try {
            // get the value of the property
            if (key == null) {
                System.out.println("=============== key null  ++++++++++++++++++++");
            }
            if (key == "") {
                System.out.println("=============== key empty  ++++++++++++++++++++");
            }
            if (properties == null) {
                System.out.println("=============== properties empty  ++++++++++++++++++++");
            }
            String propValue = properties.getProperty(key);

            System.out.println("key is: " + key);
            System.out.println("Property value is: " + propValue);
            return propValue;
        } catch (Exception ex) {
            return null;
        }
    }
}
