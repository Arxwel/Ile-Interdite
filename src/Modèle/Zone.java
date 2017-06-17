package Modèle;

public enum Zone {
	LePontDesAbimes,
	LaPorteDeBronze,
	LaCaverneDesOmbres,
	LaPorteDeFer,
	LaPorteDOr,
	LesFalaisesDeLOubli,
	LePalaisDeCorail,
	LaPorteDArgent,
	LesDunesDeLIllusion,
	Heliport,
	LaPorteDeCuivre,
	LeJardinDesHurlements,
	LaForetPourpre,
	LeLagonPerdu,
	LeMaraisBrumeux,
	Observatoire,
	LeRocherFantome,
        LaCaverneDuBrasier,
	LeTempleDuSoleil,
	LeTempleDeLaLune,
	LePalaisDesMarees,
	LeValDuCrepuscule,
	LaTourDuGuet,
	LeJardinDesMurmures;

public String nomEspace() {
    String s = String.valueOf(this.name().charAt(0));
  for(int i = 1; i < this.name().lastIndexOf(""); i++) {
      if(Character.isUpperCase(this.name().charAt(i))) {
          s = s+" "+ this.name().charAt(i);
      } else {
          s = s+this.name().charAt(i);
      }
  }
        return s;
}
}