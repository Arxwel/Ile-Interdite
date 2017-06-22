package Controleur;


import Modèle.*;
import Vue.VueEcranTitre;
import Vue.VueInscription;
import Vue.VuePlateau;
import Vue.VueEcranTitre;
import Vue.VueMonteeEaux;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.codegen.CompilerConstants;

public class Controleur extends Observateur {
  
    //private VueAventurier vueAventurier;
    private static Grille grille;
    
    private static ArrayList<Joueur> joueurs;
    private static Joueur joueurActif;
    private static Tuile lastCase;
    
    private static Stack<CarteInondation> piocheInondation;
    private static Stack<CarteInondation> défausseInondation;
    private static Stack<CarteTresor> piocheCarteTresor;
    private static Stack<CarteTresor> defausseCarteTresor;
    
    private static int numTour = 0;
    private static int nbact;
    private static boolean joueurMort = false;
    private static boolean[] reliquesPrises = new boolean[4]; //Magenta(brasier) Orange(Zéphir) Grey(Globe(pété)) Cyan(Calice)
    private static int niveauDEau;
    private int actionChoisie;
    
    private static boolean[] actionsPossibles = new boolean[4];
    
    private static Scanner sc = new Scanner(System.in);
    
    private Lock lockAct;
    private Condition conditionAct;
    
    private static VueInscription vueInscription;
    private static VueEcranTitre vueEcranTitre;
    private static VueMonteeEaux monteeEau;
    
    private int difficulte = 1;
    
    private int finFinDeJeu;
    
    public Controleur() {
        
        lockAct =  new ReentrantLock();
        conditionAct = lock.newCondition();
        
        grille = new Grille();
        
        joueurs = new ArrayList<>();
        piocheInondation = new Stack<>();
        défausseInondation = new Stack<>();
        defausseCarteTresor = new Stack<>();
        joueurActif = null;
        piocheCarteTresor = new Stack<>();
        
        vueEcranTitre = new VueEcranTitre();
        vueEcranTitre.setObservateur(this);
        vueEcranTitre.afficher();
        this.waitForInput();
        
        vueInscription = new VueInscription();
        vueInscription.setObservateur(this);
        vueInscription.afficher();
        
        this.waitForInput();
        this.init();
        
        vuePlateau = new VuePlateau(this);
        vuePlateau.setObservateur(this);
        vuePlateau.afficher();
        
        monteeEau = new VueMonteeEaux(difficulte);
        monteeEau.setVisible(true);
    }
    
    /**
     * @return the nbact
     */
    public static int getNbact() {
        return nbact;
    }

    /**
     * @param aNbact the nbact to set
     */
    private static void setNbact(int aNbact) {
        nbact = aNbact;
    }
    public VuePlateau vuePlateau;

    /**
     * @return the joueurs
     */
    public static ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * @return the defausseCarteTresor
     */
    public static Stack<CarteTresor> getDefausseCarteTresor() {
        return defausseCarteTresor;
    }

    /**
     * @param aDefausseCarteTresor the defausseCarteTresor to set
     */
    public static void setDefausseCarteTresor(Stack<CarteTresor> aDefausseCarteTresor) {
        defausseCarteTresor = aDefausseCarteTresor;
    }

    
    
    
    //décompte le nombre d'actions disponibles pour le joueur et propose les actions en fonction de leur disponibilité
    public void débutTour() {
        verifMain(joueurActif);
        if (joueurActif.getCouleur()==Color.YELLOW) {
            setNbact(4);
        } else {
            setNbact(3);
        }        
        while (getNbact()>0) {
            actionsPossibles[0]=joueurActif.isMvmntPossible();
            actionsPossibles[1]=joueurActif.isAssPossible();
            actionsPossibles[2]=joueurActif.isDonPossible();
            actionsPossibles[3]=joueurActif.isReliquePossible();
             
            System.out.println("Désactivation interfaces");
            for (Joueur j: joueurs) {
                System.out.println("Joueur "+j.getNom());
                if (j.equals(joueurActif)) {
                    System.out.println("joueur actif");
                    j.getVueAventurier().desactiverBoutons();
                    j.getVueAventurier().activerBoutons();
                    if (j.isMvmntPossible()) {
                        j.getVueAventurier().activerBoutonAller();
                    } 
                    if (j.isAssPossible()) {
                        j.getVueAventurier().activerBoutonAssecher();
                    }
                    if (j.isDonPossible()) {
                        j.getVueAventurier().activerBoutonDonner();
                    }
                    if (j.isReliquePossible()) {
                        j.getVueAventurier().activerBoutonRelique();
                    }
                } else {
                    j.getVueAventurier().desactiverBoutons();
                    System.out.println("joueur inactif");
                }
            }
            System.out.println("En attente d'un input");
            this.waitForInput();
            System.out.println("input Reçu");
            switch (actionChoisie) {
                case(1):
                    System.out.println("[Contr] Déplacer");
                    joueurActif.getVueAventurier().desactiverBoutons();
                    joueurActif.déplacer();
                    break;
                case(2):
                    System.out.println("[Contr] Assecher");
                    joueurActif.getVueAventurier().desactiverBoutons();
                    joueurActif.assecher();
                    break;
                case(3):
                    System.out.println("[Contr] Donner Carte");
                    joueurActif.getVueAventurier().desactiverBoutons();
                    joueurActif.donnerCarte();
                    this.waitForInput();
                    break;
                case(4):
                    System.out.print("[Contr] Prendre Relique ");
                    joueurActif.getVueAventurier().desactiverBoutons();
                    Color relique = joueurActif.getPosition().getReliqueDispo();
                    switch(relique.toString()) {
                        case("MAGENTA"):
                            System.out.println("MAGENTA");
                            reliquesPrises[0]=true;
                            break;
                        case("ORANGE"):
                            System.out.println("ORANGE");
                            reliquesPrises[1]=true;
                            break;
                        case("GRAY"):
                            System.out.println("GRAY");
                            reliquesPrises[2]=true;
                            break;
                        case("CYAN"):
                            System.out.println("CYAN");
                            reliquesPrises[3]=true;
                            break;
                    }
                    break;
                case(5):
                    System.out.println("[Contr] Carte Spéciale");
                    joueurActif.getVueAventurier().desactiverBoutons();
                    joueurActif.utiliserCarte();
                    break;
                case(6):
                    System.out.println("[Contr] Terminer Tour");
                    joueurActif.getVueAventurier().desactiverBoutons();
                    this.terminerTour();
                    break;
            }
            System.out.println("Action Finie");
            setNbact(getNbact() - 1);
            //piocherCarteTresorFinTour();
            //piocherCarteInondeFinTour(difficulte);
            vuePlateau.update();
        }
    }
    
    public void init() {
        //Initialisation des pioches et position des joueurs
        initPositionAventurier();
        initPiocheTresor();
        initPiocheInondation();
        for (int i=0; i<4; i++) {
            reliquesPrises[i] = false;
        }
        
        difficulte = 1;
        
        //distribution des cartes
        for (Joueur j: getJoueurs()) {
            System.out.println("Distribution à "+j.getNom());
            for (int i=0; i<4; i++) {
                CarteTresor c = piocheCarteTresor.firstElement();
                piocheCarteTresor.remove(0);
                if (c.getType() == TypeCarte.MontéeEaux) {
                    piocheCarteTresor.add(piocheCarteTresor.size(), c);
                    i--;
                } else {
                    j.getMainJoueur().add(c);
                }
            }
        } 
    }
    
    public void play() {
        //Boucle de jeu
        while (!isPartieFinie()) {
            setJoueurActif();
            débutTour();
            numTour++;
        }    
    } 
    
    private boolean isPartiePerdue(){
        boolean resultat = false;
        // L'Héliport est il sombré?
        for (int x=0; x<6; x++) {
            for (int y=0; y<6; y++) {
                Tuile t = grille.getTuile(x,y);
                if (t!=null&&t.getIntitule()==Zone.Heliport&&t.getEtat()==Etat.Sombré) {
                        resultat = true;
                        finFinDeJeu = 1;
                }
            }
        }
        // Un joueur est il "mort"?
        if (joueurMort) {
            resultat = true;
            finFinDeJeu = 2;
        }
        // La récupération des reliques manquantes est elle possible?
        for (int i=0; i<4; i++) {
            if (!reliquesPrises[i]) {
                switch(i) {
                    case(0):
                        resultat = (resultat||(grille.getTuile(Zone.LaCaverneDesOmbres).isSombre()&&grille.getTuile(Zone.LaCaverneDuBrasier).isSombre()));
                        break;
                    case(1):
                        resultat = (resultat||(grille.getTuile(Zone.LeJardinDesHurlements).isSombre()&&grille.getTuile(Zone.LeJardinDesMurmures).isSombre()));
                        break;
                    case(2):
                        resultat = (resultat||(grille.getTuile(Zone.LeTempleDeLaLune).isSombre()&&grille.getTuile(Zone.LeTempleDuSoleil).isSombre()));
                        break;
                    case(3):
                        resultat = (resultat||(grille.getTuile(Zone.LePalaisDeCorail).isSombre()&&grille.getTuile(Zone.LePalaisDesMarees).isSombre()));
                        break;
                    
                }
            }
        }
        // Le niveau d'eau est il mortel
        if (niveauDEau>9){
             resultat = true;
             finFinDeJeu = 4;
        }
       
        
        
        return resultat;
    }
    
    private boolean isPartieGagnée(){
        //Les joueurs sont ils tout les 4 sur l'héliport, ont ils les 4 reliques, ont ils au moins une carte hélicoptère
        boolean resultat = false;
        if (grille.getTuile(Zone.Heliport).getLocataires().size()==joueurs.size()) {
            int nbPrises = 0;
            for (int i=0;i<4;i++){
                if (reliquesPrises[i]) {
                    nbPrises++;
                }
            }
            if (nbPrises==4){
                for (Joueur j: joueurs) {
                    for (CarteTresor c: j.getMainJoueur()){
                        if (c.getType()==TypeCarte.SpécialHélicoptère) {
                            resultat = true;
                            finFinDeJeu = 0;
                        }
                    }
                }
            }
        }
        
        return resultat;
    }
    
    private boolean isPartieFinie() {
        return (isPartiePerdue() || isPartieGagnée());
    }

    //Place les aventuriers sur le plateau dans leur position de départ
    private static void initPositionAventurier() {
        for (Joueur j: getJoueurs()) {
            Zone spawn = j.getSpawnPoint();
            for (Tuile[] tArr: grille.getTuiles()) {
                for (Tuile t: tArr) {
                    if (t!=null && t.getIntitule().equals(spawn)) {
                        System.out.println(t.getCoordonees().getX()); //Trace Test Coordonnées
                        t.addLocataire(j);
                        j.setPosition((t));
                    }
                }
            }
        }
    }
	
    //Assure que le joueur a moins de 6 cartes en main et propose l'utilisation ou la défausse de cartes
    private static void verifMain(Joueur joueur) {  
        while (joueurActif.getMainJoueur().size() >= 6) {
            joueur.getVueAventurier().desactiverBoutons();
            joueur.defausserCarte();
        }    
            /*System.out.println(joueur.getNom() + " a trop de cartes en main. Il doit en défausser jusqu'à en avoir 5 au plus.");
            CarteTresor cs1 = new CarteTresor(TypeCarte.SpécialHélicoptère);
            CarteTresor cs2 = new CarteTresor(TypeCarte.SpécialSacDeSable);
            CarteTresor cr1 = new CarteTresor(TypeCarte.TresorCyan);
            CarteTresor cr2 = new CarteTresor(TypeCarte.TresorGray);
            CarteTresor cr3 = new CarteTresor(TypeCarte.TresorMagenta);
            CarteTresor cr4 = new CarteTresor(TypeCarte.TresorOrange);
            for (int i = 1; i<joueur.getMainJoueur().size(); i++) {
                int choix;
                
                if (joueur.getMainJoueur().get(i).equals(cs1)) {
                    System.out.println("Carte Hélicoptère. Choisissez votre action: Utiliser (1) ou Passer à la carte suivante (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte(cs1);
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cs2)) {
                    System.out.println("Carte Sac de sable. Choisissez votre action: Utiliser (1) ou Passer à la carte suivante (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte(cs2);
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr1)) {
                    System.out.println("Carte relique Cyan. Choisissez votre action: Defausser (1) ou Passer à la carte suivante (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte(cr1);
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr2)) {
                    System.out.println("Carte relique Grise. Choisissez votre action: Defausser (1) ou Passer à la carte suivante (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte(cr2);
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr3)) {
                    System.out.println("Carte relique Magenta. Choisissez votre action: Defausser (1) ou Passer à la carte suivante (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte(cr3);
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr4)) {
                    System.out.println("Carte relique Orange. Choisissez votre action: Defausser (1) ou Passer à la carte suivante (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte(cr4);
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                }
            }*/
        //}
    }
    
    
    public void terminerTour() {
        setNbact(0);
        System.out.println("Terminance du Tour");
    }
    
    

    //Détermine le joueur dont c'est le tour de jouer en début de tour
    private static void setJoueurActif() {
        joueurActif = getJoueurs().get(numTour%getJoueurs().size());
    }

    
    //met les bonnes cartes dans la pioche trésor et les mélange
    private static void initPiocheTresor() {
        for (int i=0; i<5; i++) {
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorGray));
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorCyan));
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorMagenta));
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorOrange));
        }
        for (int i=0; i<2; i++) {
            piocheCarteTresor.add(new CarteTresor(TypeCarte.MontéeEaux));
        }
        for (int i=0; i<3; i++) {
            piocheCarteTresor.add(new CarteTresor(TypeCarte.SpécialHélicoptère));
        }
        for (int i=0; i<2; i++) {
           piocheCarteTresor.add(new CarteTresor(TypeCarte.SpécialSacDeSable));
        }
        Collections.shuffle(piocheCarteTresor);
    }
    
    //met les bonnes cartes dans la pioche inondation et les mélange
    private static void initPiocheInondation() {
        for (Tuile[] tArray: grille.getTuiles()) {
            for (Tuile t: tArray) {
                piocheInondation.add(new CarteInondation(t));
            }
        }
        Collections.shuffle(piocheInondation);
    }
    
    // gère l'action "donner carte"
    private static void piocherCarte(Joueur j) {
        j.getMainJoueur().add(piocheCarteTresor.firstElement());
        piocheCarteTresor.remove(0);
    }
    
    

    public void surligner(ArrayList<Tuile> casesDispo) {System.out.println("A afficher : ");
        for (Tuile t: casesDispo) {
            System.out.println(t.getIntitule());
        }
        vuePlateau.surligner(casesDispo);
    }



    //traite les messages en provenance des vues
    @Override
    public void traiterMessage(Message msg) {
        switch (msg.getType().toString()) {
            case ("Valider"):
               Joueur j1, j2, j3, j4;
               String nom;
               nom = vueInscription.getNomFieldJ1();
                while(nom == null || nom.isEmpty()) {
                    nom = fenetreNom("Joueur 1");
                }  
                String role = vueInscription.getRoleComboJ1();
                Random randomGenerator = new Random();
                ArrayList<String> listRoles = new ArrayList<>();
                for(String s : vueInscription.getRoles()) {
                    if(!vueInscription.isAleatoireVide(s)) {
                        listRoles.add(s);
                    }
                }
                if(!vueInscription.isAleatoireVide(vueInscription.getRoleComboJ1())) {
                    listRoles.remove(vueInscription.getRoleComboJ1());
                }
                if(!vueInscription.isAleatoireVide(vueInscription.getRoleComboJ2())) {
                    listRoles.remove(vueInscription.getRoleComboJ2());
                }
                if(!vueInscription.isAleatoireVide(vueInscription.getRoleComboJ3())) {
                    listRoles.remove(vueInscription.getRoleComboJ3());
                }
                if(!vueInscription.isAleatoireVide(vueInscription.getRoleComboJ4())) {
                    listRoles.remove(vueInscription.getRoleComboJ4());
                }
                if(vueInscription.getRoleComboJ1() == "Aléatoire") {
                    int index = randomGenerator.nextInt(listRoles.size());
                    role = listRoles.get(index);
                }
                
                switch (role) {
                    case ("Explorateur"):
                        j1 = new Explorateur(nom, this);
                        getJoueurs().add(j1);
                    break;
                    case ("Ingénieur"):
                        j1 = new Ingénieur(nom, this);
                        getJoueurs().add(j1);
                    break;
                    case ("Messager"):
                        j1 = new Messager(nom, this);
                        getJoueurs().add(j1);
                    break;
                    case ("Navigateur"):
                        j1 = new Navigateur(nom, this);
                        getJoueurs().add(j1);
                    break;
                    case ("Pilote"):
                        j1 = new Pilote(nom, this);
                        getJoueurs().add(j1);
                    break;
                    case ("Plongeur"):
                        j1 = new Plongeur(nom, this);
                        getJoueurs().add(j1);
                    break;
                } 
                listRoles.remove(role);
                nom = vueInscription.getNomFieldJ2();
                while(nom == null || nom.isEmpty()) {
                    nom = fenetreNom("Joueur 2");
                }  
                role = vueInscription.getRoleComboJ2();
                if(vueInscription.getRoleComboJ2() == "Aléatoire") {
                    int index = randomGenerator.nextInt(listRoles.size());
                    role = listRoles.get(index);
                    listRoles.remove(index);
                }
                switch (role) {
                    case ("Explorateur"):
                        j2 = new Explorateur(nom, this);
                        getJoueurs().add(j2);
                    break;
                    case ("Ingénieur"):
                        j2 = new Ingénieur(nom, this);
                        getJoueurs().add(j2);
                    break;
                    case ("Messager"):
                        j2 = new Messager(nom, this);
                        getJoueurs().add(j2);
                    break;
                    case ("Navigateur"):
                        j2 = new Navigateur(nom, this);
                        getJoueurs().add(j2);
                    break;
                    case ("Pilote"):
                        j2 = new Pilote(nom, this);
                        getJoueurs().add(j2);
                    break;
                    case ("Plongeur"):
                        j2 = new Plongeur(nom, this);
                        getJoueurs().add(j2);
                    break;
                }
                listRoles.remove(role);
                nom = vueInscription.getNomFieldJ3();
                role = vueInscription.getRoleComboJ3();
                while((nom == null || nom.isEmpty()) && role != "Vide") {
                    nom = fenetreNom("Joueur 3");
                }  
                
                if(vueInscription.getRoleComboJ3() == "Aléatoire") {
                    int index = randomGenerator.nextInt(listRoles.size());
                    role = listRoles.get(index);
                    listRoles.remove(index);
                }
                switch (role) {
                    case ("Explorateur"):
                        j3 = new Explorateur(nom, this);
                        getJoueurs().add(j3);
                    break;
                    case ("Ingénieur"):
                        j3 = new Ingénieur(nom, this);
                        getJoueurs().add(j3);
                    break;
                    case ("Messager"):
                        j3 = new Messager(nom, this);
                        getJoueurs().add(j3);
                    break;
                    case ("Navigateur"):
                        j3 = new Navigateur(nom, this);
                        getJoueurs().add(j3);
                    break;
                    case ("Pilote"):
                        j3 = new Pilote(nom, this);
                        getJoueurs().add(j3);
                    break;
                    case ("Plongeur"):
                        j3 = new Plongeur(nom, this);
                        getJoueurs().add(j3);
                    break;
                    case ("Vide"):
                    break;
                }
                listRoles.remove(role);
                nom = vueInscription.getNomFieldJ4();
                role = vueInscription.getRoleComboJ4();
                while((nom == null || nom.isEmpty()) && role != "Vide") {
                    nom = fenetreNom("Joueur 4");
                }
                
                if(vueInscription.getRoleComboJ4() == "Aléatoire") {
                    int index = randomGenerator.nextInt(listRoles.size());
                    role = listRoles.get(index);
                    listRoles.remove(index);
                }
                switch (role) {
                    case ("Explorateur"):
                        j4 = new Explorateur(nom, this);
                        getJoueurs().add(j4);
                    break;
                    case ("Ingénieur"):
                        j4 = new Ingénieur(nom, this);
                        getJoueurs().add(j4);
                    break;
                    case ("Messager"):
                        j4 = new Messager(nom, this);
                        getJoueurs().add(j4);
                    break;
                    case ("Navigateur"):
                        j4 = new Navigateur(nom, this);
                        getJoueurs().add(j4);
                    break;
                    case ("Pilote"):
                        j4 = new Pilote(nom, this);
                        getJoueurs().add(j4);
                    break;
                    case ("Plongeur"):
                        j4 = new Plongeur(nom, this);
                        getJoueurs().add(j4);
                    break;
                    case ("Vide"):
                    break;
                }
                listRoles.remove(role);
                vueInscription.getWindow().dispose();
                System.out.println("Joueurs Enregistrés: ");
                for (Joueur j: this.getJoueurs()) {
                    System.out.println(j.getNom()+" incarnant "+j.getClass().toString());
                }
                this.notifier();
            break;
            case ("Annuler"):
                System.exit(0);
            break;
            case ("Jouer") :
                vueEcranTitre.fermer();
                this.notifier();
            break;
        }
        
    }
    
    public Tuile getLastCase() {
        return lastCase;
    }
    
    public Grille getGrille() {
        return grille;
    }
 
    //message d'erreur en cas de pseudo null
    public String fenetreNom(String Joueur) {
        String nom;
        JFrame frame = new JFrame("Saisie du Nom");
        nom =JOptionPane.showInputDialog(frame, Joueur + " : Saisissez votre nom");
        return nom;
    }

    @Override
    public void traiterMessagePlateau(MessagePlateau msg) {
        System.out.println(msg.getCoo().getX()+" "+msg.getCoo().getY()+" a été cliqué.");
        lastCase = grille.getTuile(msg.getCoo().getX(), msg.getCoo().getY());
        this.notifier();
    }

    @Override
    public void traiterMessageAventurier(MessageAventurier msg) {
        System.out.print("Le Joueur "+msg.getJoueur().getNom());
        switch(msg.getType().toString()){
            case ("Deplacer"):
                System.out.println("a cliqué sur Deplacer");
                actionChoisie=1;
                this.notifier();
                break;
            case ("Assecher"):
                System.out.println("a cliqué sur Assecher");
                actionChoisie=2;
                this.notifier();
                break;
            case ("Donner"):
                System.out.println("a cliqué sur Donner");
                actionChoisie=3;
                this.notifier();
                break;
            case ("PrendreRelique"):
                System.out.println("a cliqué sur PrendreRelique");
                actionChoisie=4;
                this.notifier();
                break;
            case ("CarteSpe"):
                System.out.println("a cliqué sur CarteSpe");
                actionChoisie=5;
                this.notifier();
                break;
            case ("TerminerTour"):
                System.out.println("a cliqué sur TerminerTour");
                actionChoisie=6;
                this.notifier();
                break;
        }
    }
    
    public void piocherCarteTresorFinTour() {
        CarteTresor carteTresorFinTour;
        if (piocheCarteTresor.isEmpty()) {
            for (int i = 0; i < defausseCarteTresor.capacity(); i++) {
                carteTresorFinTour = defausseCarteTresor.firstElement();
                piocheCarteTresor.add(carteTresorFinTour);
            }
            Collections.shuffle(piocheCarteTresor);
        }
        
        for (int i = 0; i < 2; i++) {
            piocherCarte(joueurActif);
            
        }
    }
    
    public void piocherCarteInondeFinTour(int difficulte) {
        CarteInondation carteInondeFinTour;
        if (piocheInondation.isEmpty()) {
            for (int i = 0; i < défausseInondation.capacity(); i++) {
                carteInondeFinTour = défausseInondation.firstElement();
                piocheInondation.add(carteInondeFinTour);
            }
            Collections.shuffle(piocheInondation);
        }
        
        int niveauEau = 2;
        if (difficulte == 1 || difficulte == 2) {
            niveauEau = 2;
        } else if (difficulte == 3 || difficulte == 4 || difficulte == 5) {
            niveauEau = 3;
        } else if (difficulte == 6 || difficulte == 7) {
            niveauEau = 4;
        } else if (difficulte == 8 || difficulte == 9) {
            niveauEau = 5;
        }
        for (int i = 0; i < niveauEau; i++) {
            carteInondeFinTour = piocheInondation.firstElement();
            if (carteInondeFinTour.getTuile().getEtat() == Etat.Sec) {
                carteInondeFinTour.getTuile().setEtat(Etat.Inondé);
                piocheInondation.remove(carteInondeFinTour);
                défausseInondation.add(carteInondeFinTour);
            } else if (carteInondeFinTour.getTuile().getEtat() == Etat.Inondé) {
                carteInondeFinTour.getTuile().setEtat(Etat.Sombré);
                piocheInondation.remove(carteInondeFinTour);
            }
        }
    }
}
