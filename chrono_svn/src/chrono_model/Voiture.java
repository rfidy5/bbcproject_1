/***********************************************************************
 * Module:  Voiture.java
 * Author:  29004847
 * Purpose: Defines the Class Voiture
 ***********************************************************************/

package chrono_model;

import java.util.*;

/** Une voiture participe à une course à la fois, et contient une liste de pilotes */
public class Voiture {
	private String numeroVoiture_;
	private String couleur_;
	private String lienSurImage_;
	private int nombreDeTours_;
	private float tempsEstimeTour_;
	private boolean voitureActive_;
	private float quantiteMoyenneEssenceParTour_;
	private int nombreToursEffectues_;
	private int nombreToursDepuisDernierRelais_;
	private int numeroRelaisEnCours_;
	private java.util.Date heurePrevueProchainPassage_;

	private Pilote piloteActuel_;


	private HashMap<String, List<Top>> collectionPilotes_Tops = new HashMap<String, List<Top>>();

	public String [] listePilote_(){
		List<String> liste = new ArrayList<String>();
		
		for(String key : collectionPilotes_Tops.keySet()){
			liste.add(key);
		}
		
		int size = liste.size();
		String [] res = new String[size];
		for(int i = 0; i < size; i++)
			res[i] = liste.get(i);
		
		return res;
	}
	
	/**
	 * Ajouter un pilote
	 * @param newPilote
	 */

	public void addCollectionPilotes_Tops(Pilote newPilote) {
		String key = ""+newPilote.getNomPilote_();
		System.out.println("le pilote "+key+"  casque "+newPilote.getCouleurCasque_());
		if(!newPilote.getCouleurCasque_().isEmpty())
			key+=" "+newPilote.getCouleurCasque_();

		List<Top> listeTop = new ArrayList<Top>();

		if(!this.collectionPilotes_Tops.containsKey(key)){
			this.collectionPilotes_Tops.put(key, listeTop);
		}
	}

	/**
	 * supprimer un pilote
	 * @param oldPilote
	 */
	public void removeCollectionPilotes_(Pilote oldPilote) {
		if (oldPilote == null)
			return;
		
		String key = ""+oldPilote.getNomPilote_();
		if(!oldPilote.getCouleurCasque_().isEmpty())
			key+=" "+oldPilote.getCouleurCasque_();

		if(this.collectionPilotes_Tops.containsKey(key)){
			this.collectionPilotes_Tops.remove(key);
		}
	}


	/** @pdGenerated default add
	 * @param newTop */
	public void addCollectionPilotes_Tops_(Top newTop, Pilote piloteActuel) {

		// Concatener le nom du pilote et son casque pour cr�er la cl� de la hashmap
		String key = ""+piloteActuel.getNomPilote_();
		if(!piloteActuel.getCouleurCasque_().isEmpty())
			key+=" "+piloteActuel.getCouleurCasque_();

		// Si le pilote existe d�j�, on lui ajoute le nouveau top dans sa liste de top
		if(this.collectionPilotes_Tops.containsKey(key)){
			this.collectionPilotes_Tops.get(key).add(newTop);
		}
		// si non, on cr�e le pilote avec son top
		else {
			List<Top> listTop_ = new ArrayList<Top>();
			listTop_.add(newTop);
			this.collectionPilotes_Tops.put(key, listTop_);
		}
	}


	/** @pdGenerated default remove
	 * @param oldTop */
	public void removeCollectionTops_(Top oldTop, Pilote piloteActuel) {

		// Concatener le nom du pilote et son casque pour cr�er la cl� de la hashmap
		String key = ""+piloteActuel.getNomPilote_();

		if(!piloteActuel.getCouleurCasque_().isEmpty())
			key+=" "+piloteActuel.getCouleurCasque_();

		if(this.collectionPilotes_Tops.containsKey(key)){
			int index = this.indexTop(this.collectionPilotes_Tops.get(key), oldTop);
			if(index > -1)
				this.collectionPilotes_Tops.get(key).remove(index);
		}
	}

	public void modifierPilote(Pilote piloteUpdate) {

		//		if(!collectionPilotes_Tops.isEmpty()){
		//
		//			for (Iterator<Pilote> i = collectionPilotes_Tops.keySet().iterator() ; i.hasNext() ; ){
		//				System.out.println("3"+ i.next().getNomPilote_()); 
		//
		//				Pilote pil = i.next();
		//
		//				if(collectionPilotes_Tops.containsKey(pil)) {
		//
		//					//	System.out.println("balbla" + pil.getNomPilote_());
		//					List<Top>  value = collectionPilotes_Tops.get(pil);
		//					collectionPilotes_Tops.put(piloteUpdate, value);
		//				};
		//			}
		//		}
	}
	//A revoir
	public void validerVoiture() {

	}


	public void modifierTop(Pilote pilote, Top top) {
		if(collectionPilotes_Tops.get(pilote).contains(top)){
			//Top texte =  top.getEtatTop_();
			collectionPilotes_Tops.get(pilote).set(nombreDeTours_, null);


		}
	}


	/**
	 * 
	 * @param list
	 * @param t
	 * @return
	 */
	public int indexTop(List<Top> list, Top t){
		int i = -1;
		List<String> listeString = new ArrayList<String>();

		for(Iterator<Top> it = list.iterator(); it.hasNext();){
			String top = ""+t.getEtatTop_();
			top += t.getHeureDePassageMS_();
			listeString.add(top);
		}

		String top = ""+t.getEtatTop_();
		top += t.getHeureDePassageMS_();

		if(listeString.contains(top)) 
			i = listeString.indexOf(top);

		return i;
	}



	/**
	 * @return the numeroVoiture_
	 */
	public String getNumeroVoiture_() {
		return numeroVoiture_;
	}



	/**
	 * @param numeroVoiture_ the numeroVoiture_ to set
	 */
	public void setNumeroVoiture_(String numeroVoiture_) {
		this.numeroVoiture_ = numeroVoiture_;
	}



	/**
	 * @return the couleur_
	 */
	public String getCouleur_() {
		return couleur_;
	}



	/**
	 * @param couleur_ the couleur_ to set
	 */
	public void setCouleur_(String couleur_) {
		this.couleur_ = couleur_;
	}



	/**
	 * @return the lienSurImage_
	 */
	public String getLienSurImage_() {
		return lienSurImage_;
	}



	/**
	 * @param lienSurImage_ the lienSurImage_ to set
	 */
	public void setLienSurImage_(String lienSurImage_) {
		this.lienSurImage_ = lienSurImage_;
	}



	/**
	 * @return the nombreDeTours_
	 */
	public int getNombreDeTours_() {
		return nombreDeTours_;
	}



	/**
	 * @param nombreDeTours_ the nombreDeTours_ to set
	 */
	public void setNombreDeTours_(int nombreDeTours_) {
		this.nombreDeTours_ = nombreDeTours_;
	}



	/**
	 * @return the tempsEstimeTour_
	 */
	public float getTempsEstimeTour_() {
		return tempsEstimeTour_;
	}



	/**
	 * @param tempsEstimeTour_ the tempsEstimeTour_ to set
	 */
	public void setTempsEstimeTour_(float tempsEstimeTour_) {
		this.tempsEstimeTour_ = tempsEstimeTour_;
	}



	/**
	 * @return the voitureActive_
	 */
	public boolean isVoitureActive_() {
		return voitureActive_;
	}



	/**
	 * @param voitureActive_ the voitureActive_ to set
	 */
	public void setVoitureActive_(boolean voitureActive_) {
		this.voitureActive_ = voitureActive_;
	}



	/**
	 * @return the quantiteMoyenneEssenceParTour_
	 */
	public float getQuantiteMoyenneEssenceParTour_() {
		return quantiteMoyenneEssenceParTour_;
	}



	/**
	 * @param quantiteMoyenneEssenceParTour_ the quantiteMoyenneEssenceParTour_ to set
	 */
	public void setQuantiteMoyenneEssenceParTour_(
			float quantiteMoyenneEssenceParTour_) {
		this.quantiteMoyenneEssenceParTour_ = quantiteMoyenneEssenceParTour_;
	}



	/**
	 * @return the nombreToursEffectues_
	 */
	public int getNombreToursEffectues_() {
		return nombreToursEffectues_;
	}



	/**
	 * @param nombreToursEffectues_ the nombreToursEffectues_ to set
	 */
	public void setNombreToursEffectues_(int nombreToursEffectues_) {
		this.nombreToursEffectues_ = nombreToursEffectues_;
	}



	/**
	 * @return the nombreToursDepuisDernierRelais_
	 */
	public int getNombreToursDepuisDernierRelais_() {
		return nombreToursDepuisDernierRelais_;
	}



	/**
	 * @param nombreToursDepuisDernierRelais_ the nombreToursDepuisDernierRelais_ to set
	 */
	public void setNombreToursDepuisDernierRelais_(
			int nombreToursDepuisDernierRelais_) {
		this.nombreToursDepuisDernierRelais_ = nombreToursDepuisDernierRelais_;
	}



	/**
	 * @return the numeroRelaisEnCours_
	 */
	public int getNumeroRelaisEnCours_() {
		return numeroRelaisEnCours_;
	}



	/**
	 * @param numeroRelaisEnCours_ the numeroRelaisEnCours_ to set
	 */
	public void setNumeroRelaisEnCours_(int numeroRelaisEnCours_) {
		this.numeroRelaisEnCours_ = numeroRelaisEnCours_;
	}



	/**
	 * @return the heurePrevueProchainPassage_
	 */
	public java.util.Date getHeurePrevueProchainPassage_() {
		return heurePrevueProchainPassage_;
	}



	/**
	 * @param heurePrevueProchainPassage_ the heurePrevueProchainPassage_ to set
	 */
	public void setHeurePrevueProchainPassage_(
			java.util.Date heurePrevueProchainPassage_) {
		this.heurePrevueProchainPassage_ = heurePrevueProchainPassage_;
	}



	/**
	 * @return the piloteActuel_
	 */
	public Pilote getPiloteActuel_() {
		return piloteActuel_;
	}



	/**
	 * @param piloteActuel_ the piloteActuel_ to set
	 */
	public void setPiloteActuel_(Pilote piloteActuel_) {
		this.piloteActuel_ = piloteActuel_;
	}

}