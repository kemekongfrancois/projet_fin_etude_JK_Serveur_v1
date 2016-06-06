/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entite.Admin;
import entite.Classroom;
import entite.ClassroomPK;
import entite.OptionAndClasse;
import entite.OptionAndClassePK;
import entite.OptionClass;
import entite.Student;
import entite.SuperAdmin;
import entite.TheClass;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KEF10
 */
@Stateless
@LocalBean
public class SessionBean {

    @PersistenceContext(unitName = "projetJoelle-ejbPU")
    private EntityManager em;

    String formatDate="dd/MM/yyyy";
    
    private static String repPhotos = "C:\\Users\\KEF10\\Documents\\NetBeansProjects\\projetJoelle\\projetJoelle-ejb\\photos\\";
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    
    /**
     * convertie une chaine de carractére en Date
     * @param dateString dd/MM/yyyy
     * @return 
     */
    private Date stringToDate(String StringDate){
        Date date = null;
        try {
            date = (new SimpleDateFormat(formatDate)).parse(StringDate);
        } catch (ParseException ex) {
            System.out.println("date invalide");
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    /**
     * retoiurne le super administrateur donc l'email es donné en paramettre
     * @param email
     * @return null si le compte n'existe pas
     */
    public SuperAdmin getSuperAdministrateur(String email){
        return em.find(SuperAdmin.class, email);
    }
    
    public Admin getAdministrateur(String email){
        return em.find(Admin.class, email);
    }
    
    public Student getEleve(String email){
        return em.find(Student.class, email);
    }
    
    /**
     * modifie les informations d'un administrateur excepté l'email(la clé)
     * @param emailAdmin
     * @param nom
     * @param prenom
     * @param date
     * @param pass
     * @param patchPhoto
     * @return 
     */
    public String modifiAdmin(String emailAdmin, String nom, String prenom, String date, String pass, String patchPhoto) {
        Admin admin = em.find(Admin.class, emailAdmin);
        if (admin == null) {
            return "Compte administrateur inexistant";
        } else {
            admin.setNamePerson(nom);
            admin.setSurnamePerson(prenom);
            admin.setDateOfBirth(stringToDate(date));
            admin.setPassword(pass);
            admin.setPathPhoto(patchPhoto);
            persist(admin);
            return "ok";
        }
    }
    
    public String modifiSupAdmin(String emailSupAdmin, String nom, String prenom, String date, String pass, String patchPhoto) {
        SuperAdmin SupAdmin = em.find(SuperAdmin.class, emailSupAdmin);
        if (SupAdmin == null) {
            return "Compte Super administrateur inexistant";
        } else {
            SupAdmin.setNamePerson(nom);
            SupAdmin.setSurnamePerson(prenom);
            SupAdmin.setDateOfBirth(stringToDate(date));
            SupAdmin.setPassword(pass);
            SupAdmin.setPathPhoto(patchPhoto);
            persist(SupAdmin);
            return modifiAdmin(emailSupAdmin, nom, prenom, date, pass, patchPhoto);
        }
    }
    
    public String modifiEleve(String emailEleve, String nom, String prenom, String date, String pass, String patchPhoto) {
        Student eleve = em.find(Student.class, emailEleve);
        if (eleve == null) {
            return "Compte eleve inexistant";
        } else {
            eleve.setNamePerson(nom);
            eleve.setSurnamePerson(prenom);
            eleve.setDateOfBirth(stringToDate(date));
            eleve.setPassword(pass);
            eleve.setPathPhoto(patchPhoto);
            persist(eleve);
            return "ok";
        }
    }

    public void initialisation(){
        
        Query query = em.createNamedQuery("SuperAdmin.findAll");
        if(query.getResultList().size()>0){//il ya au moins un compte super-administrateur
            
        }
        else {//il n'ya pas de compte super admin
            creerCompteSuperAdministrateur("nomKEF", "prenomKEF", "06/05/2016", "KEF@yahoo.fr", "0000", "c:");
            
            //creerOption("Mecanique", "KEF");
        };
    }
    
    public String creerCompteAdministrateur(String nom, String prenom, String date, String emailAdmin, String emailSupAdmin, String pass, String patchPhoto){
        SuperAdmin supAdmin = em.find(SuperAdmin.class, emailSupAdmin);
        if (supAdmin == null) {
            return "le super administrateur n'existe pas";
        } else {//le super admin existe
            Admin admin = em.find(Admin.class, emailAdmin);
            if (admin == null) {//le compte n'existe pas on le crée
                admin = new Admin();
                admin.setNamePerson(nom);
                admin.setSurnamePerson(prenom);
                admin.setDateOfBirth(stringToDate(date));
                admin.setEmailAddress(emailAdmin);
                admin.setPassword(pass);
                admin.setPathPhoto(patchPhoto);
                admin.setClassroomList(new ArrayList<Classroom>());
                admin.setSupEmailAddress(supAdmin);
                persist(admin);

                return "ok";
            } else {//le compte existais déja
                return "cet admin existe deja";
            }
        }
    }
    
    public String creerCompteSuperAdministrateur(String nom, String prenom, String date, String email, String pass, String patchPhoto){
        SuperAdmin supAdmin = em.find(SuperAdmin.class, email);
        if (supAdmin == null) {//le compte n'existe pas on le crée
            supAdmin = new SuperAdmin();
            supAdmin.setNamePerson(nom);
            supAdmin.setSurnamePerson(prenom);
            supAdmin.setDateOfBirth(stringToDate(date));
            supAdmin.setEmailAddress(email);
            supAdmin.setPassword(pass);
            supAdmin.setPathPhoto(patchPhoto);
            persist(supAdmin);
            
            return creerCompteAdministrateur(nom, prenom, date, email, email, pass, patchPhoto);//un super administrateur es aussis un administrateur
        } else {
            return supAdmin.getEmailAddress() +" existe";
        }
    }
    
    public String suprimerComptAdmin(String email){
        Admin admin = em.find(Admin.class, email);
        if (admin == null) {//le compte n'existe pas on le crée
            return "le compte n'existe pas";
        } else {
            em.remove(admin);
            return "ok";
        }
    }
    
    /**
     * 
     * @param nomOption
     * @param emailAdmin represent l'administrateur qui à créer l'option
     * @return 
     */
    public String creerOption(String nomOption, String emailAdmin){
        
        Admin admin = em.find(Admin.class, emailAdmin);
        if (admin == null) {//le compte n'existe pas
            return "administrateur inexistant";
        } else {
            OptionClass optionF = em.find(OptionClass.class, nomOption);
            if (optionF == null) {//on créer la nouvelle option car elle n'existe pas
                OptionClass option = new OptionClass(nomOption);
                option.setEmailAddress(admin);

                persist(option);
                return "ok";
            } else {
                return "cet option existe";
            }
        }
    }
    
    public String creerClasse(String nomClasse, int pension) {
        
            TheClass classe = em.find(TheClass.class, nomClasse);
            if (classe == null) {
                classe = new TheClass(nomClasse);
                classe.setSchoolFees(pension);
                persist(classe);
                return "ok";
            } else {
                return "cette classe existe";
            }
        
    }
    
    
    /**
     * cette fonction crée une salle et met à jour la table "option and class"
     * @param nomClasse represente la classe (Exemple: 6,5,..,T)
     * @param numeroClasse represente le numero de la classe( 6M1, 6M2,...)
     * @param nomOption (Mecanique, Electricité,...)
     * @param emailAdmin represent l'admin qui à créer la salle de classe
     * @param effectif l'effectif de la classe qu'on es entrain de créer
     * @return 
     */
    public String creerSalleDeClasse(String nomClasse, String nomOption, int numeroClasse, String emailAdmin, int effectif){
        Admin admin = em.find(Admin.class, emailAdmin);
        if (admin == null) {
            return "administrateur inexistant";
        } else {//l'admin existe
            TheClass classe = em.find(TheClass.class, nomClasse);
            if (classe == null) {
                return "classe inexistante";
            } else {//la classe existe
                OptionClass option = em.find(OptionClass.class, nomOption);
                if (option == null) {
                    return "option inexistante";
                } else {//l'option existe

                    ClassroomPK cleSalleClasse = new ClassroomPK(nomOption, nomClasse, numeroClasse);//represente la clé de la sale de classe
                    Classroom salleDeClasse = em.find(Classroom.class, cleSalleClasse);
                    if (salleDeClasse == null) {
                       
                        OptionAndClasse optionClasse = em.find(OptionAndClasse.class, new OptionAndClassePK(nomOption, nomClasse));
                        if (optionClasse == null) {//on es dans le cas d'une nouvelle salle de classe
                            optionClasse = new OptionAndClasse(nomOption, nomClasse);
                            optionClasse.setTotalStrength(effectif);
                        } else {//on es dans le cas où on crée une nouvelle classe pour augmenté l'effectif
                            optionClasse.setTotalStrength(effectif + optionClasse.getTotalStrength());//on met à jour l'effectif total
                        }
                        

                        salleDeClasse = new Classroom(cleSalleClasse);
                        salleDeClasse.setEffective(effectif);
                        salleDeClasse.setEmailAddress(admin);
                        salleDeClasse.setTheClass(classe);
                        persist(salleDeClasse);
                        persist(optionClasse);
                        return "ok";
                    } else {
                        return "cette salle de classe existe";
                    }
                }

            }
        }
    }

    public String modifierSalleDeClasse(String nomClasse, String nomOption, int numeroClasse, int effectif){
        ClassroomPK cleSalleClasse = new ClassroomPK(nomOption, nomClasse, numeroClasse);//represente la clé de la sale de classe
        Classroom salleDeClasse = em.find(Classroom.class, cleSalleClasse);
        if (salleDeClasse == null) {
            return "cette salle de classe es inexistante";
        } else {
            OptionAndClasse optionAndClasse = em.find(OptionAndClasse.class, new OptionAndClassePK(nomOption, nomClasse));
            int effectifTotal = optionAndClasse.getTotalStrength();
            effectifTotal = effectifTotal - salleDeClasse.getEffective();//on retire l'ancien effectif de leffectif total
            effectifTotal = effectifTotal + effectif;//on met à jour le nouvelle effectif
            optionAndClasse.setTotalStrength(effectifTotal);
            persist(optionAndClasse);//on enregistre dans la BD
            
            salleDeClasse.setEffective(effectif);
            persist(salleDeClasse);
            //System.out.println(nomClasse+nomOption+numeroClasse+effectif);
            return "ok";
        }
    }
    
    public String suprimerSalleClasse(String nomClasse, String nomOption, int numeroClasse) {
        ClassroomPK cleSalleClasse = new ClassroomPK(nomOption,nomClasse, numeroClasse);//represente la clé de la sale de classe
        Classroom salleDeClasse = em.find(Classroom.class, cleSalleClasse);
        if (salleDeClasse == null) {
            return "cette salle de classe es inexistante";
        } else {
            OptionAndClasse optionClasse = em.find(OptionAndClasse.class, new OptionAndClassePK(nomOption, nomClasse));
                    if(optionClasse!=null){
                        optionClasse.setTotalStrength(optionClasse.getTotalStrength()-salleDeClasse.getEffective());//on met à jour l'effectif
                    }
                    persist(optionClasse);
                    
            em.remove(salleDeClasse);
            return "ok";
        }

    }
    /**
     * 
     * @param email
     * @param nomClasse
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param pass
     * @param pathPhotos
     * @param numIdentification
     * @param pathBulletin
     * @return 
     */
    private String creerEleve(String email, String nomClasse, String nomOption, String nom, String prenom, String dateNaissance, String pass, String pathPhotos, String pathBulletin, int numIdentification){
        OptionAndClasse optionEtClasse = em.find(OptionAndClasse.class, new OptionAndClassePK(nomOption, nomClasse));
        if (optionEtClasse == null) {
            return "couple option-classe inexistant";
        } else {
            Student eleve = em.find(Student.class, email);
            if (eleve == null) {
                eleve = new Student(email);
                eleve.setDateOfBirth(stringToDate(dateNaissance));
                eleve.setOptionAndClasse(optionEtClasse);
                eleve.setNamePerson(nom);
                eleve.setPassword(pass);
                eleve.setPathPhoto(pathPhotos);
                eleve.setSurnamePerson(prenom);
                eleve.setPathFormerBulletin(pathBulletin);
                eleve.setIdentificationNumber(numIdentification);
                persist(eleve);
                return "ok";
            } else {
                return "cet élève existe déja";
            }
        }
    }
    /**
     * retourne le prochain numéro d'identification
     * @return 
     */
    private int nextNumeroIdentification(){
        Query requete = em.createNativeQuery("SELECT max(identification_number) FROM bd_joelle.student;");
        int maxIdentifian = (int) requete.getSingleResult();//on recupére le numéro d'identification max
        maxIdentifian++;//on met à jour le numéro d'identification pour le prochain élève
        if(maxIdentifian==1){//le numero "1" es reservé pour les comptes donc l'inscription es validé
            maxIdentifian++;
        }
        return maxIdentifian;
    }
    /**
     * les nouveaux élèves fornissent un bulletin et leurs numéreau d'identification est "0"
     * @param email
     * @param nomClasse
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param pass
     * @param pathPhotos
     * @param pathBulletin
     * @return 
     */
    public String creerNewEleve(String email, String nomClasse, String nomOption, String nom, String prenom, String dateNaissance, String pass, String pathPhotos, String pathBulletin){
        return creerEleve(email, nomClasse, nomOption, nom, prenom, dateNaissance, pass, pathPhotos, pathBulletin, 0);
    }
    
    /**
     * les anciens ne fornisse pas de bulletin le numéro d'dentification es généré et retourné
     * @param email
     * @param nomClasse
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param pass
     * @param pathPhotos
     * @return le numéro d'identification s'il n'ya pas de pb
     */
    public String creerAncienEleve(String email, String nomClasse, String nomOption, String nom, String prenom, String dateNaissance, String pass, String pathPhotos){
        int numIdentification = nextNumeroIdentification();//on génère le numéro d'identification de l'élève
        String resultat = creerEleve(email, nomClasse, nomOption, nom, prenom, dateNaissance, pass, pathPhotos,null,numIdentification);//on créer l'élève sans patch pour le bulletin et avec le numéro générer
        if(resultat.equals("ok")) return numIdentification+"" ;//on retourne le numéro si tous c'est bien passé
        else return resultat;//on retourne le msg d'ereur en cas de problème
    }
    
    /**
     * validé le dossier de l'élève revient à lui fornir un numéro d'dentification et remplire son champ email super administrateur
     * @param emailSupAdmin represent le super administrateur qui vas valider l'inscription
     * @param emailEleve 
     * @param decision true si le dossier de l'élève es accepter
     * @return on retourne "-1" si le dossier es rejeter, "ok" s'il es accepter et une autre valeur en cas de pb
     */
    public String validerDossierEleve(String emailSupAdmin, String emailEleve, boolean decision){
        SuperAdmin supAdmin = em.find(SuperAdmin.class, emailSupAdmin);
        if(supAdmin==null){
            return "super administrateur inexistant";
        }else{
            Student eleve = em.find(Student.class, emailEleve);
            if(eleve==null){
                return "élève inexistant";
            }else{
                int numIdentification = -1;//si l'élève n'es pas acepter sont numéro sera "-1" 
                String retourne = "-1";
                if(decision){//l'élève es accepté
                    numIdentification = nextNumeroIdentification();//on met ajour le numéro d'identification pour les élèves accepter
                    retourne = "ok";
                }
                eleve.setIdentificationNumber(numIdentification);
                eleve.setSupEmailAddress(supAdmin);//on enregistre le super administrateur qui a traité le dossier
                persist(eleve);
                return retourne;
            }
        }
    }
    
    /**
     * valider une inscription revient à remplire le champs administrateur de à mettre le numéro d'identification à "1"
     * si l'inscription es rejeté le champ numéro d'identification de l'élève aura la valeur "-2" et on retourne -2
     * @param emailAdmin
     * @param emailEleve
     * @param decision
     * @return "ok" si l'enregistrement c'est bien effectué, "-2" si l'inscription es rejeté et un msg d'ereur en cas de pb
     */
    public String validerInscription(String emailAdmin, String emailEleve, boolean decision){
        Admin admin = em.find(Admin.class, emailAdmin);
        if(admin==null){
            return "compte administrateur inexistant";
        }else{
            Student eleve = em.find(Student.class, emailEleve);
            if(eleve==null){
                return "élève inexistant";
            }else{
                String retourne = "ok";
                if(!decision){//l'inscription es rejeté
                    eleve.setIdentificationNumber(-2);//on met "-2" pour signifié que l'inscription de l'élève a été rejeté
                    retourne = "-2";
                }else{//l'inscription es accepter
                    eleve.setIdentificationNumber(1);//on met "1" pour signifier que l'inscription es effectué
                }
                eleve.setAdmEmailAddress(admin);//on enregistre l'administrateur qui a traité le dossier
                persist(eleve);
                return retourne;
            }
        }
    }
    
    /**
     * retourne la liste des élèves en attente de validation de leurs inscription
     * @return 
     */
    public List<Student> listElevEnAttentDeValidInscription(){
        String requeteString = "SELECT * FROM student where identification_number>0 and adm_email_address is NULL;";
          Query requete = em.createNativeQuery(requeteString,Student.class);
          return (List<Student>) requete.getResultList();
    }
    
    /**
     * retourne la liste des élèves en attente de validation de leurs dossiers d'inscription
     * @return 
     */
    public List<Student> listElevEnAttentDeValidDeDossier(){
        String requeteString = "SELECT * FROM student where identification_number=0;";
          Query requete = em.createNativeQuery(requeteString,Student.class);
          return (List<Student>) requete.getResultList();
    }
    
    /**
     * 
     * @return la liste des salles de classe
     */
    public List<Classroom> listSalleClasse(){
        Query requete = em.createNamedQuery("Classroom.findAll");
        return (List<Classroom>) requete.getResultList();
    }
    
    /**
     * retourne la liste des classes
     * @return 
     */
    public List<TheClass> listClasse(){
        Query requete = em.createNamedQuery("TheClass.findAll");
        return (List<TheClass>) requete.getResultList();
    }
    
    /**
     * cette fonction retourn la liste des options
     * @return 
     */
    public List<OptionClass> listOption(){
        Query requete = em.createNamedQuery("OptionClass.findAll");
        return (List<OptionClass>) requete.getResultList();
    }
    
    /**
     * retourne les option de classe donc le nom es pris en paramètre
     * @param nomClasse
     * @return 
     */
    public List<OptionClass> listOptionClasse(String nomClasse){
        TheClass classe = em.find(TheClass.class, nomClasse);
        List<OptionAndClasse> listOptionAndClasse = classe.getOptionAndClasseList();
        List<OptionClass> listOption = new ArrayList<OptionClass>();
        for(OptionAndClasse optionAndClasse : listOptionAndClasse){
            listOption.add(optionAndClasse.getOptionClass());
//            System.out.println("option: "+optionAndClasse.getOptionClass().getNameOption());
        }
        return listOption;
    }
    
    /**
     * rette fonction retourne le type du compte donc l'email es pris en paramettre
     * @param email
     * @return 
     */
    public String typeDeCompte(String email){
        SuperAdmin supAdmin = em.find(SuperAdmin.class, email);
        if(supAdmin!=null) return "supAdmin";
        else{
            Admin admin = em.find(Admin.class, email);
            if(admin!=null) return "admin";
            else {
                Student student = em.find(Student.class, email);
                if(student!=null) return "eleve";
                else return "ko";//compte inexistant
            }
        }
    }
    
    /**
     * retourne le fichier donc le nom es donné en paramettre
     * @param nomFichie
     * @return 
     */
    public byte[] downloadFile(String nomFichie){
        nomFichie = repPhotos+"img0027.jpg";
        
        File f = new File(nomFichie);
        int length = (int)f.length();
        byte[] bites = new byte[length];
        /* Partie intéressante : on crée un BufferedInputStream,
        qui va nous permettre de lire plusieurs byte d'un coup */
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            /* On lit la totalité du ficher. Le premier paramètre est le tableau de byte qui va recevoir les données,
            le deuxième est la position dans le fichier où on commence à lire les données (donc zéro puisqu'on commence au début du fichier),
            enfin le dernier paramètre est le nombre d'octet que l'on veut lire,
            ici on donne length puisqu'on veut lire la totalité du fichier d'un coup. */
            int result = in.read(bites, 0, length);
        } catch (IOException ex) {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bites;
    }
    
    /**
     * retourne le nombre d'élèves inscrit dans la salle de classe pris en paramettre
     * @param nomClasse
     * @param nomOption
     * @return 
     */
    public int nombreEleveInscritSalleClasse(String nomClasse, String nomOption){
        OptionAndClassePK cleSalleClasse = new OptionAndClassePK(nomOption, nomClasse);//represente la clé de la sale de classe
        OptionAndClasse salleDeClasse = em.find(OptionAndClasse.class, cleSalleClasse);
        if (salleDeClasse == null) {
            //return "cette salle de classe es inexistante";
            return -1;
        } else {
            int nbr = 0;
            List<Student> listEleve = salleDeClasse.getStudentList();
            for(Student eleve : listEleve){
                if((eleve.getIdentificationNumber()>0)&&(eleve.getAdmEmailAddress()!=null)){
                    nbr++;
                }
            }
                
            return nbr;
        }
    }
    
    /**
     * retourne le nombre de place restante dans une salle de classe
     * @param nomClasse
     * @param nomOption
     * @return -1001 en cas de pb
     */
    public int nombrePlaceRestanteSalleClasse(String nomClasse, String nomOption){
        OptionAndClassePK cleSalleClasse = new OptionAndClassePK(nomOption, nomClasse);//represente la clé de la sale de classe
        OptionAndClasse salleDeClasse = em.find(OptionAndClasse.class, cleSalleClasse);
        if (salleDeClasse == null) {
            //return "cette salle de classe es inexistante";
            return -1001;
        } else {
            int nbr = 0;
            List<Student> listEleve = salleDeClasse.getStudentList();
            for(Student eleve : listEleve){
                if((eleve.getIdentificationNumber()>0)&&(eleve.getAdmEmailAddress()!=null)){
                    nbr++;
                }
            }
                
            return salleDeClasse.getTotalStrength()-nbr;
        }
    }
}
