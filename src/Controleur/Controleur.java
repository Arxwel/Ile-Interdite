package Controleur;


import Modèle.*;
import Vue.VueAventurier;
import Vue.VueEcranTitre;
import Vue.VueInscription;
import Vue.VuePlateau;
import Vue.VueEcranTitre;
import Vue.VueFinDePartie;
import Vue.VueMonteeEaux;
import Vue.VueReliques;
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
    private static VueFinDePartie vueFinDePartie;
    private static VueMonteeEaux vueMonteeEau;
    private static VueReliques vueReliques;
    private static VueAventurier vj1,vj2,vj3,vj4;
    private ArrayList<VueAventurier> vuesAventuriers;
    
    private int difficulte;
    private boolean modeDebug;
    
    private int finDeJeu;
    
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
        
        vueFinDePartie = new VueFinDePartie();
        vueFinDePartie.setObservateur(this);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        vj1 = new VueAventurier(this.getJoueurs().get(0));
        this.getJoueurs().get(0).setVueAventurier(vj1);
        vj1.getWindow().setLocation((int) (dim.getWidth()*0.01), (int) (dim.getHeight()*0.015));
        vj1.getWindow().setVisible(true);
        vj1.setObservateur(this);
        
        vj2 = new VueAventurier(this.getJoueurs().get(1));
        this.getJoueurs().get(1).setVueAventurier(vj2);
        vj2.getWindow().setLocation((int) (dim.getWidth()*0.01), (int) (dim.getHeight()*0.245));
        vj2.getWindow().setVisible(true);
        vj2.setObservateur(this);
        
        if(this.getJoueurs().size() >=3) {
            vj3 = new VueAventurier(this.getJoueurs().get(2));
            this.getJoueurs().get(2).setVueAventurier(vj3);
            vj3.getWindow().setLocation((int) (dim.getWidth()*0.01), (int) (dim.getHeight()*0.475));
            vj3.getWindow().setVisible(true);
            vj3.setObservateur(this);
        }
        
        if(this.getJoueurs().size() ==4) {
            vj4 = new VueAventurier(this.getJoueurs().get(3));
            this.getJoueurs().get(3).setVueAventurier(vj4);
            vj4.getWindow().setLocation((int) (dim.getWidth()*0.01), (int) (dim.getHeight()*0.705));
            vj4.getWindow().setVisible(true);
            vj4.setObservateur(this);
        }
        
        vuesAventuriers = new ArrayList<VueAventurier>();
        vuesAventuriers.add(vj1);
        vuesAventuriers.add(vj2);
        if (this.getJoueurs().size()>2){
            vuesAventuriers.add(vj3);
            if (this.getJoueurs().size()==4){
                vuesAventuriers.add(vj4);
            }
        }
        
        vueMonteeEau = new VueMonteeEaux(difficulte);
        System.out.println("Difficulté de départ "+difficulte);
        vueMonteeEau.setVisible(true);
        
        vueReliques = new VueReliques();
        vueReliques.setVisible(true);
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
        if (joueurActif.getCouleur()==Color.YELLOW) {
            setNbact(4);
        } else {
            setNbact(3);
        }
        while (getNbact()>0) {
            verifMain(joueurActif);            
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
                    if (j.getCouleur()==Color.WHITE) {
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
                    joueurActif.prendreRelique();
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
            vueReliques.update(reliquesPrises);
            System.out.println("Action Finie");
            setNbact(getNbact() - 1);
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
            piocherCarteTresorFinTour();
            verifMain(joueurActif);
            piocherCarteInondeFinTour(difficulte);
            vuePlateau.update();
            for (VueAventurier v: vuesAventuriers) {
                v.update();
            }
            numTour++;
        }
        System.err.println("GAME OVER");
        vuePlateau.dispose();
        for (VueAventurier v: vuesAventuriers) {
            v.dispose();
        }
        vueMonteeEau.dispose();
        vueReliques.dispose();
        vueFinDePartie.update(finDeJeu);
        vueFinDePartie.afficher();
        this.waitForInput();
    } 
    
    private boolean isPartiePerdue(){
        boolean resultat = false;
        // L'Héliport est il sombré?
        for (int x=0; x<6; x++) {
            for (int y=0; y<6; y++) {
                Tuile t = grille.getTuile(x,y);
                if (t!=null&&t.getIntitule()==Zone.Heliport&&t.getEtat()==Etat.Sombré) {
                        resultat = true;
                        finDeJeu = 1;
                }
            }
        }
        // Un joueur est il "mort"?
        if (joueurMort) {
            resultat = true;
            finDeJeu = 2;
        }
        // La récupération des reliques manquantes est elle possible?
        for (int i=0; i<4; i++) {
            if (!reliquesPrises[i]) {
                switch(i) {
                    case(0):
                        if(grille.getTuile(Zone.LaCaverneDesOmbres).isSombre()&&grille.getTuile(Zone.LaCaverneDuBrasier).isSombre()){
                           resultat=true;
                           finDeJeu = 3;
                        }
                        break;
                    case(1):
                        if(grille.getTuile(Zone.LeJardinDesHurlements).isSombre()&&grille.getTuile(Zone.LeJardinDesMurmures).isSombre()){
                           resultat=true;
                           finDeJeu = 3;
                        }
                        break;
                    case(2):
                        if(grille.getTuile(Zone.LeTempleDeLaLune).isSombre()&&grille.getTuile(Zone.LeTempleDuSoleil).isSombre()){
                           resultat=true;
                           finDeJeu = 3;
                        }
                        break;
                    case(3):
                        if(grille.getTuile(Zone.LePalaisDeCorail).isSombre()&&grille.getTuile(Zone.LePalaisDesMarees).isSombre()){
                           resultat=true;
                           finDeJeu = 3;
                        }
                        break;
                }
            }
        }
        // Le niveau d'eau est il mortel
        if (niveauDEau>9){
             resultat = true;
             finDeJeu = 4;
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
                            finDeJeu = 0;
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
        System.out.println(piocheCarteTresor.size());
    }
    
    //met les bonnes cartes dans la pioche inondation et les mélange
    private static void initPiocheInondation() {
        for (Tuile[] tArray: grille.getTuiles()) {
            for (Tuile t: tArray) {
                if (t!=null&&t.getEtat()!=Etat.Sombré){
                    piocheInondation.add(new CarteInondation(t));
                }
            }
        }
        Collections.shuffle(piocheInondation);
    }
    
    // gère l'action "donner carte"
    private static void piocherCarte(Joueur j) {
        
        System.out.println(piocheCarteTresor.size());
        if (piocheCarteTresor.isEmpty()) {
            System.out.println("Pioche carte tresor vide.");
            
            piocheCarteTresor.addAll(defausseCarteTresor);
            defausseCarteTresor.clear();
            
            Collections.shuffle(piocheCarteTresor);
            System.out.println("La défausse vient d'etre mélangée pour reformer la pioche.");
        }
        
        CarteTresor carte = piocheCarteTresor.firstElement();
        j.getMainJoueur().add(carte);
        System.out.println("Carte Piochée"+carte.getType().toString());
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
                switch(vueInscription.getComboDiff()) {
                    case ("Novice"):
                        difficulte = 1;
                    break;
                    case ("Normal"):
                        difficulte = 2;
                    break;
                    case ("Élite"):
                        difficulte = 3;
                    break;
                    case ("Légendaire"): 
                        difficulte = 4;
                    break;
                    case ("Mortel"):
                        difficulte = 10;
                    break;
                }
                System.out.println("Difficulté :" + vueInscription.getComboDiff());
                modeDebug = vueInscription.getCheckDebug();
                System.out.println("Mode Debug :" + vueInscription.getCheckDebug());
                this.notifier();
            break;
            case ("Annuler"):
                System.exit(0);
            break;
            case ("Jouer") :
                vueEcranTitre.fermer();
                this.notifier();
            break;
            case ("Rejouer") :
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
        
        
        for (int i = 0; i < 2; i++) {
            piocherCarte(joueurActif);
            CarteTresor cartePiochee = joueurActif.getMainJoueur().get(joueurActif.getMainJoueur().size()-1);
            if(cartePiochee.getType() == TypeCarte.MontéeEaux ) {
                monteeEau();
                joueurActif.getMainJoueur().remove(cartePiochee);
                System.out.println(joueurActif.getNom() + "vient de piocher: " + cartePiochee);
            }
        }
        for (VueAventurier v: vuesAventuriers) {
            v.update();
        }
    }
    
    public void piocherCarteInondeFinTour(int difficulte) {
        CarteInondation carteInondeFinTour;
        if (piocheInondation.isEmpty()) {
            System.out.println("Pioche carte inondation vide.");
            for (int i = 0; i < défausseInondation.capacity(); i++) {
                carteInondeFinTour = défausseInondation.firstElement();
                piocheInondation.add(carteInondeFinTour);
                défausseInondation.remove(carteInondeFinTour);
            }
            Collections.shuffle(piocheInondation);
            System.out.println("La défausse vient d'etre mélangée pour reformer la pioche.");
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
            System.out.println("Vous allez piocher " + niveauEau + " cartes inondation.");
            System.out.println("Carte Tirée "+carteInondeFinTour.getTuile().getIntitule().toString());
            if (carteInondeFinTour.getTuile().getEtat() == Etat.Sec) {
                carteInondeFinTour.getTuile().setEtat(Etat.Inondé);
                piocheInondation.remove(carteInondeFinTour);
                défausseInondation.add(carteInondeFinTour);
                System.out.println(carteInondeFinTour.getTuile().getIntitule().nomEspace() + " a été inondé.");
            } else if (carteInondeFinTour.getTuile().getEtat() == Etat.Inondé) {
                carteInondeFinTour.getTuile().setEtat(Etat.Sombré);
                piocheInondation.remove(carteInondeFinTour);
                System.out.println(carteInondeFinTour.getTuile().getIntitule().nomEspace() + " a coulé.");
                if(!carteInondeFinTour.getTuile().getLocataires().isEmpty()){
                    joueurMort=true;
                }
            }
        }
    }

    private void monteeEau() {
        CarteInondation carteInonde;
        difficulte++;
        System.out.println("L'eau monte.");
        vueMonteeEau.monteDesEaux(difficulte);
        piocheInondation.addAll(défausseInondation);
        défausseInondation.clear();
        Collections.shuffle(piocheInondation);
        System.out.println("La défausse vient d'etre mélangée a la pioche.");
    }

    public void addRelique(int i) {
        reliquesPrises[i]=true;
    }
}
