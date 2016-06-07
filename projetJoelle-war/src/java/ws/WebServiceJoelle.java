/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entite.Admin;
import entite.Classroom;
import entite.OptionClass;
import entite.Student;
import entite.SuperAdmin;
import entite.TheClass;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import session.SessionBean;

/**
 *
 * @author KEF10
 */
@WebService(serviceName = "WebServiceJoelle")
public class WebServiceJoelle {
  
    @EJB
    private SessionBean sessionBean;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        //sessionBean.initialisation();

        
        String resultat = "";
        resultat = "\n*****Super Admin= " + creerCompteSuperAdministrateur("ATANGANA", "Romain", "06/05/1980", "atanganaromain@yahoo.fr", "0000", "c:");
        resultat += "\n*****Super Admin= " + creerCompteSuperAdministrateur("KEMEKONG", "Francois", "23/05/1988", "KEF@", "0000", "c:");

        resultat += "\n****Admin= " + creerCompteAdministrateur("MBIETHIEU", "Cezar", "23/05/1982", "mbiethieucezar@yahoo.fr", "atanganaromain@yahoo.fr", "1111", "d:");
        resultat += "\n****Admin2= " + creerCompteAdministrateur("KEMEKONG", "François", "23/05/1988", "kemekongfranois@yahoo.fr", "atanganaromain@yahoo.fr", "2222", "d:");

        resultat += "\n**** creer option = " + creerOption("Fabrication mécanique", "mbiethieucezar@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Electronique", "mbiethieucezar@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Génie-civil Bâtiment", "mbiethieucezar@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Mécanique Automobile", "mbiethieucezar@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Menuiserie ébénisterie", "mbiethieucezar@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Electrotechnique", "mbiethieucezar@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Maintenance Electromécanique", "mbiethieucezar@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("action commerciale administrative", "kemekongfranois@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Action commerciale de communication", "kemekongfranois@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Comptabilité et gestion", "kemekongfranois@yahoo.fr");
        resultat += "\n**** creer option = " + creerOption("Industrie d'habillement", "kemekongfranois@yahoo.fr");

        resultat += "\n**** creer Classe= " + creerClasse("6", 55000);
        resultat += "\n**** creer Classe= " + creerClasse("5", 45000);
        resultat += "\n**** creer Classe= " + creerClasse("4", 55000);
        resultat += "\n**** creer Classe= " + creerClasse("3", 75000);
        resultat += "\n**** creer Classe= " + creerClasse("2nde", 45000);
        resultat += "\n**** creer Classe= " + creerClasse("P", 90000);
        resultat += "\n**** creer Classe= " + creerClasse("Tle", 90000);

        resultat += "\n**** creer salle de classe(6ième F1)= " + creerSalleDeClasse("6", "Fabrication mécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(6ième F2)= " + creerSalleDeClasse("6", "Electronique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(6ième F4)= " + creerSalleDeClasse("6", "Génie-civil Bâtiment", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(6ième MA)= " + creerSalleDeClasse("6", "Mécanique Automobile", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(6ième MEB)= " + creerSalleDeClasse("6", "Menuiserie ébénisterie", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(6ième F3)= " + creerSalleDeClasse("6", "Electrotechnique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(6ième MEM)= " + creerSalleDeClasse("6", "Maintenance Electromécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(6ième IH)= " + creerSalleDeClasse("6", "Industrie d'habillement", 1, "kemekongfranois@yahoo.fr", 40);

        resultat += "\n**** creer salle de classe(5ième F1)= " + creerSalleDeClasse("5", "Fabrication mécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(5ième F2)= " + creerSalleDeClasse("5", "Electronique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(5ième F4)= " + creerSalleDeClasse("5", "Génie-civil Bâtiment", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(5ième MA)= " + creerSalleDeClasse("5", "Mécanique Automobile", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(5ième MEB)= " + creerSalleDeClasse("5", "Menuiserie ébénisterie", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(5ième F3)= " + creerSalleDeClasse("5", "Electrotechnique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(5ième MEM)= " + creerSalleDeClasse("5", "Maintenance Electromécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(5ième IH)= " + creerSalleDeClasse("5", "Industrie d'habillement", 1, "kemekongfranois@yahoo.fr", 40);

        resultat += "\n**** creer salle de classe(4ième F1)= " + creerSalleDeClasse("4", "Fabrication mécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(4ième F2)= " + creerSalleDeClasse("4", "Electronique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(4ième F4)= " + creerSalleDeClasse("4", "Génie-civil Bâtiment", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(4ième MA)= " + creerSalleDeClasse("4", "Mécanique Automobile", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(4ième MEB)= " + creerSalleDeClasse("4", "Menuiserie ébénisterie", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(4ième F3)= " + creerSalleDeClasse("4", "Electrotechnique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(4ième MEM)= " + creerSalleDeClasse("4", "Maintenance Electromécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(4ième IH)= " + creerSalleDeClasse("4", "Industrie d'habillement", 1, "kemekongfranois@yahoo.fr", 40);

        resultat += "\n**** creer salle de classe(3ième F1)= " + creerSalleDeClasse("3", "Fabrication mécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(3ième F2)= " + creerSalleDeClasse("3", "Electronique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(3ième F4)= " + creerSalleDeClasse("3", "Génie-civil Bâtiment", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(3ième MA)= " + creerSalleDeClasse("3", "Mécanique Automobile", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(3ième MEB)= " + creerSalleDeClasse("3", "Menuiserie ébénisterie", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(3ième F3)= " + creerSalleDeClasse("3", "Electrotechnique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(3ième MEM)= " + creerSalleDeClasse("3", "Maintenance Electromécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(3ième IH)= " + creerSalleDeClasse("3", "Industrie d'habillement", 1, "kemekongfranois@yahoo.fr", 40);

        resultat += "\n**** creer salle de classe(2nde F1)= " + creerSalleDeClasse("2nde", "Fabrication mécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde F2)= " + creerSalleDeClasse("2nde", "Electronique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde F4)= " + creerSalleDeClasse("2nde", "Génie-civil Bâtiment", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde MA)= " + creerSalleDeClasse("2nde", "Mécanique Automobile", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde MEB)= " + creerSalleDeClasse("2nde", "Menuiserie ébénisterie", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde F3)= " + creerSalleDeClasse("2nde", "Electrotechnique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde MEM)= " + creerSalleDeClasse("2nde", "Maintenance Electromécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde IH)= " + creerSalleDeClasse("2nde", "Industrie d'habillement", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde ACA)= " + creerSalleDeClasse("2nde", "action commerciale administrative", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde ACC)= " + creerSalleDeClasse("2nde", "Action commerciale de communication", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(2nde CG)= " + creerSalleDeClasse("2nde", "Comptabilité et gestion", 1, "kemekongfranois@yahoo.fr", 40);

        resultat += "\n**** creer salle de classe(P F1)= " + creerSalleDeClasse("P", "Fabrication mécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P F2)= " + creerSalleDeClasse("P", "Electronique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P F4)= " + creerSalleDeClasse("P", "Génie-civil Bâtiment", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P MA)= " + creerSalleDeClasse("P", "Mécanique Automobile", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P MEB)= " + creerSalleDeClasse("P", "Menuiserie ébénisterie", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P F3)= " + creerSalleDeClasse("P", "Electrotechnique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P MEM)= " + creerSalleDeClasse("P", "Maintenance Electromécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P IH)= " + creerSalleDeClasse("P", "Industrie d'habillement", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P ACA)= " + creerSalleDeClasse("P", "action commerciale administrative", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P ACC)= " + creerSalleDeClasse("P", "Action commerciale de communication", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(P CG)= " + creerSalleDeClasse("P", "Comptabilité et gestion", 1, "kemekongfranois@yahoo.fr", 40);

        resultat += "\n**** creer salle de classe(Tle F1)= " + creerSalleDeClasse("Tle", "Fabrication mécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle F2)= " + creerSalleDeClasse("Tle", "Electronique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle F4)= " + creerSalleDeClasse("Tle", "Génie-civil Bâtiment", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle MA)= " + creerSalleDeClasse("Tle", "Mécanique Automobile", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle MEB)= " + creerSalleDeClasse("Tle", "Menuiserie ébénisterie", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle F3)= " + creerSalleDeClasse("Tle", "Electrotechnique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle MEM)= " + creerSalleDeClasse("Tle", "Maintenance Electromécanique", 1, "mbiethieucezar@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle IH)= " + creerSalleDeClasse("Tle", "Industrie d'habillement", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle ACA)= " + creerSalleDeClasse("Tle", "action commerciale administrative", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle ACC)= " + creerSalleDeClasse("Tle", "Action commerciale de communication", 1, "kemekongfranois@yahoo.fr", 40);
        resultat += "\n**** creer salle de classe(Tle CG)= " + creerSalleDeClasse("Tle", "Comptabilité et gestion", 1, "kemekongfranois@yahoo.fr", 40);

        resultat += "\n**** creer nouveau eleve= " + creerNewEleve("dzuchejoelle@gmail.com", "Tle", "Fabrication mécanique", "DZUCHE", "Joelle", "06/05/1992", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve2= " + creerNewEleve("kemekongyann@gmail.com", "P", "Mécanique Automobile", "KEMEKONG", "Yann", "27/09/2016", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve3= " + creerNewEleve("defoange@gmail.com", "2nde", "Comptabilité et gestion", "DEFO", "Ange", "11/02/2006", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve3= " + creerNewEleve("dzuchearis@gmail.com", "5", "Menuiserie ébénisterie", "DZUCHE", "Aris", "16/12/2014", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve= " + creerNewEleve("tsamoyoan@gmail.com", "Tle", "Fabrication mécanique", "TSAMO", "Yoan", "06/05/1992", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve2= " + creerNewEleve("teguealex@gmail.com", "P", "Mécanique Automobile", "TEGUE", "Alex", "27/09/2016", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve3= " + creerNewEleve("mintedemajoyce@gmail.com", "2nde", "Comptabilité et gestion", "MINTEDEMA", "Joyce", "11/02/2006", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve3= " + creerNewEleve("deffoquicelle@gmail.com", "5", "Menuiserie ébénisterie", "DEFFO", "Quicelle", "16/12/2014", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve= " + creerNewEleve("2dzuchejoelle@gmail.com", "Tle", "Fabrication mécanique", "2DZUCHE", "2Joelle", "06/05/1992", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve2= " + creerNewEleve("2kemekongyann@gmail.com", "P", "Mécanique Automobile", "2KEMEKONG", "2Yann", "27/09/2016", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve3= " + creerNewEleve("2defoange@gmail.com", "2nde", "Comptabilité et gestion", "2DEFO", "2Ange", "11/02/2006", "0000", "d:", "c:");
        resultat += "\n**** creer nouveau eleve3= " + creerNewEleve("2kenfackvanessa@gmail.com", "5", "Menuiserie ébénisterie", "2KENFACK", "2Vanessa", "16/12/2014", "0000", "d:", "c:");

        resultat += "\n**** creer ancien eleve= " + creerAncienEleve("waffochris@gmail.com", "6", "Génie-civil Bâtiment", "WAFFO", "Chris", "22/11/2013", "0000", "d:");
        resultat += "\n**** creer ancien eleve1= " + creerAncienEleve("motouommaeliz@gmail.com", "6", "Mécanique Automobile", "MOTOUOM", "Maeliz", "25/12/2016", "0000", "d:");
        resultat += "\n**** creer ancien eleve2= " + creerAncienEleve("kengnenelly@gmail.com", "2nde", "Industrie d'habillement", "KENGNE", "Nelly", "08/05/1994", "0000", "d:");
        resultat += "\n**** creer ancien eleve= " + creerAncienEleve("mekamchristelle@gmail.com", "6", "Génie-civil Bâtiment", "WAFFO", "Chris", "22/11/2013", "0000", "d:");
        resultat += "\n**** creer ancien eleve1= " + creerAncienEleve("moootouomcatherine@gmail.com", "6", "Mécanique Automobile", "MOOOTOUOM", "Cathérine", "25/12/2016", "0000", "d:");
        resultat += "\n**** creer ancien eleve2= " + creerAncienEleve("kengnegaelle@gmail.com", "2nde", "Industrie d'habillement", "KENGNE", "Gaelle", "08/05/1994", "0000", "d:");
        resultat += "\n**** creer ancien eleve= " + creerAncienEleve("guedemnadege@gmail.com", "6", "Génie-civil Bâtiment", "GUEDEM", "Nadège", "22/11/2013", "0000", "d:");
        resultat += "\n**** creer ancien eleve1= " + creerAncienEleve("mekammarie@gmail.com", "6", "Mécanique Automobile", "MEKAM", "Marie", "25/12/2016", "0000", "d:");
        resultat += "\n**** creer ancien eleve2= " + creerAncienEleve("kuateyannick@gmail.com", "2nde", "Industrie d'habillement", "KEUATE", "Yannick", "08/05/1994", "0000", "d:");

/*
        resultat += "\n**** dossiée élève validé= " + validerDossierEleve("KEF@yahoo.fr", "nouveau@gmail.com", true);
        resultat += "\n**** dossiée élève rejeter= " + validerDossierEleve("KEF@yahoo.fr", "2nouveau@gmail.com", false);

        resultat += "\n**** inscription élève nouveau validé= " + validerInscription("22kemekongfranois@yahoo.fr", "nouveau@gmail.com", true);
        resultat += "\n**** inscription élève ancien rejeté= " + validerInscription("22kemekongfranois@yahoo.fr", "ancien@gmail.com", false);

        resultat += "\n**** modifier admin= " + modifiAdmin("22kemekongfranois@yahoo.fr", "nom modifier", "prenom modifier", "16/11/2000", "1111", "f:");

        resultat += "\n**** modifier SupAdmin= " + modifiSupAdmin("KEF@yahoo.fr", "nomSupAdmin", "prenom SupAdmin", "16/11/2000", "0000", "f:");
        
        resultat += "\n**** modifier eleve= " + modifiEleve("nouveau@gmail.com", "nom nouveau 1", "prenom nouveau 1", "16/11/2000", "0000", "f:");

*/
         
        
        //resultat = suprimerComptAdmin("kemekongfranois@yahoo.fr");
        return "Hello " + txt + " !  "+resultat;
    }
    
    public String creerCompteSuperAdministrateur(String nom, String prenom, String date, String email, String pass, String patchPhoto) {
        return sessionBean.creerCompteSuperAdministrateur(nom, prenom, date, email, pass, patchPhoto);
    }

    public String creerCompteAdministrateur(String nom, String prenom, String date, String emailAdmin, String emailSupAdmin, String pass, String patchPhoto){
        return sessionBean.creerCompteAdministrateur(nom, prenom, date, emailAdmin, emailSupAdmin, pass, patchPhoto);
    }

    public String suprimerComptAdmin(String email) {
        return sessionBean.suprimerComptAdmin(email);
    }
    
    public String creerOption(String nomOption, String emailAdmin){
        return sessionBean.creerOption(nomOption, emailAdmin);
    }
    
    public String creerClasse(String nomClasse, int pension) {
        return sessionBean.creerClasse(nomClasse, pension);
    }
    
    public String creerSalleDeClasse(String nomClasse, String nomOption, int numeroClasse, String emailAdmin, int effectif){
        return sessionBean.creerSalleDeClasse(nomClasse, nomOption, numeroClasse, emailAdmin, effectif);
    }
    
    public String creerAncienEleve(String email, String nomClasse, String nomOption, String nom, String prenom, String dateNaissance, String pass, String pathPhotos){
        return sessionBean.creerAncienEleve(email, nomClasse, nomOption, nom, prenom, dateNaissance, pass, pathPhotos);
    }
    
    public String creerNewEleve(String email, String nomClasse, String nomOption, String nom, String prenom, String dateNaissance, String pass, String pathPhotos, String pathBulletin){
        return sessionBean.creerNewEleve(email, nomClasse, nomOption, nom, prenom, dateNaissance, pass, pathPhotos, pathBulletin);
    }
    
    public String validerDossierEleve(String emailSupAdmin, String emailEleve, boolean decision){
        return sessionBean.validerDossierEleve(emailSupAdmin, emailEleve, decision);
    }
    
    public String validerInscription(String emailAdmin, String emailEleve, boolean decision){
        return sessionBean.validerInscription(emailAdmin, emailEleve, decision);
    }
    
     public List<Student> listElevEnAttentDeValidInscription(){
         return sessionBean.listElevEnAttentDeValidInscription();
     }
     
     public List<Student> listElevEnAttentDeValidDeDossier(){
         return sessionBean.listElevEnAttentDeValidDeDossier();
     }

     public List<Classroom> listSalleClasse(){
         return sessionBean.listSalleClasse();
     }
     
     public String suprimerSalleClasse(String nomClasse, String nomOption, int numeroClasse) {
         return sessionBean.suprimerSalleClasse(nomClasse, nomOption, numeroClasse);
     }
     
     public String modifierSalleDeClasse(String nomClasse, String nomOption, int numeroClasse, int effectif){
         return sessionBean.modifierSalleDeClasse(nomClasse, nomOption, numeroClasse, effectif);
     }
     
     public String typeDeCompte(String email){
         return sessionBean.typeDeCompte(email);
     }
     
     public SuperAdmin getSuperAdministrateur(String email){
         return sessionBean.getSuperAdministrateur(email);
     }
     
     public Admin getAdministrateur(String email){
         return sessionBean.getAdministrateur(email);
     }
     
     public Student getEleve(String email){
         return sessionBean.getEleve(email);
     }
     
     public String modifiAdmin(String emailAdmin, String nom, String prenom, String date, String pass, String patchPhoto) {
         return sessionBean.modifiAdmin(emailAdmin, nom, prenom, date, pass, patchPhoto);
     }
   
     public String modifiSupAdmin(String emailSupAdmin, String nom, String prenom, String date, String pass, String patchPhoto) {
         return sessionBean.modifiSupAdmin(emailSupAdmin, nom, prenom, date, pass, patchPhoto);
     }
    
     public String modifiEleve(String emailEleve, String nom, String prenom, String date, String pass, String patchPhoto) {
         return sessionBean.modifiEleve(emailEleve, nom, prenom, date, pass, patchPhoto);
     }
     
     public List<OptionClass> listOption(){
         return sessionBean.listOption();
     }
     
     public List<TheClass> listClasse(){
         return sessionBean.listClasse();
     }
     
     public byte[] downloadFile(String nomFichie){
         return sessionBean.downloadFile(nomFichie);
     }
     
     public List<OptionClass> listOptionClasse(String nomClasse){
         return sessionBean.listOptionClasse(nomClasse);
     }
     
     public int nombreEleveInscritSalleClasse(String nomClasse, String nomOption){
         return sessionBean.nombreEleveInscritSalleClasse(nomClasse, nomOption);
     }
     
     public int nombrePlaceRestanteSalleClasse(String nomClasse, String nomOption){
         return sessionBean.nombrePlaceRestanteSalleClasse(nomClasse, nomOption);
     }
     
     public List<Student> listEleveInscrit(){
         return sessionBean.listEleveInscrit();
     }
     
     public List<Student> listEleveInscritDansClasse(String nomClasse, String nomOption){
         return sessionBean.listEleveInscritDansClasse(nomClasse, nomOption);
     }
}
