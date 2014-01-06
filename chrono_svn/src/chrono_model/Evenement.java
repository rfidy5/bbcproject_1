/***********************************************************************
 * Module:  Evenement.java
 * Author:  29004847
 * Purpose: Defines the Class Evenement
 ***********************************************************************/

package chrono_model;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;


/** Un evenement peut contenir des courses ainsi que des séances d'essais */
public class Evenement extends Observable {
	public Evenement() {
		super();
		this.collectionEssaiCourse_ = new ArrayList<EssaiOrCourse>();
		this.collectionVoitures_ = new ArrayList<Voiture>();
	}

	/**
	 * Attribut de classe qui stocke le nom de l'�v�nement 
	 */
	private String nomEvenement_;
	/**
	 * Attribut de classe qui stocke le nom du circuit de l'�v�nement
	 */
	private String nomCircuit_;
	/**
	 * Attribut de classe qui stocke la taille du circuit de l'�v�nement
	 */
	private float longueurCircuit_;
	/**
	 * Attribut de classe qui stocke la liste des courses et essais de l'�v�nement
	 */
	public List<EssaiOrCourse> collectionEssaiCourse_;
	/**
	 * Attribut de classe qui stocke la liste des voitures de l'�v�nement
	 */
	public List<Voiture> collectionVoitures_;

	/**
	 * Ajoute une nouvelle course ou un nouvel essai � la liste des {@link EssaiOrCourse}
	 * @param course {@link EssaiOrCourse}
	 */
	public void creerCourse(EssaiOrCourse nouvelleCourse) {
		if (nouvelleCourse == null)
			return;
		if (this.collectionEssaiCourse_ == null)
			this.collectionEssaiCourse_ = new ArrayList<EssaiOrCourse>();
		if (!this.collectionEssaiCourse_.contains(nouvelleCourse))
			this.collectionEssaiCourse_.add(nouvelleCourse);
		setChanged();
		notifyObservers();
	}

	/**
	 * Charge la course {@link EssaiOrCourse} afin de pouvoir �ventuellement la lancer
	 * @param i int position de la course ou de l'essai dans la liste {@link EssaiOrCourse}
	 */
	public void chargerCourse(int i) {
		// TODO: implement
	}

	/**
	 * Modifie la course {@link EssaiOrCourse} situ�e � la position i par "course"
	 * @param i int position de la course ou de l'essai dans la liste {@link EssaiOrCourse}
	 * @param course nouvelle course � inserer � la position i
	 */
	public void modifierCourse(int i, EssaiOrCourse course) {
		if(i < collectionEssaiCourse_.size())
			collectionEssaiCourse_.set(i, course);
	}

	/**
	 * Supprime la course {@link EssaiOrCourse} situ�e � la position i
	 * @param i int position de la course ou de l'essai dans la liste {@link EssaiOrCourse}
	 * @param course {@link EssaiOrCourse} � supprimer � la position i
	 */
	public void supprimerCourse(int i, EssaiOrCourse course) {
		if (this.collectionEssaiCourse_ != null)
			if (this.collectionEssaiCourse_.contains(course))
				this.collectionEssaiCourse_.remove(course);
		
		if( i > -1 && i < this.collectionEssaiCourse_.size())
			this.collectionEssaiCourse_.remove(i);
		setChanged();
		notifyObservers();
	}

	/**
	 * Ajoute une nouvelle voiture � la fin de la liste des voitures de l'�v�nement
	 * @param newVoiture {@link Voiture}
	 */
	public void creerVoiture(Voiture newVoiture) {
		if (newVoiture == null)
			return;
		if (this.collectionVoitures_ == null)
			this.collectionVoitures_ = new ArrayList<Voiture>();
		if (!this.collectionVoitures_.contains(newVoiture))
			this.collectionVoitures_.add(newVoiture);
		
		setChanged();
		notifyObservers();
	}

	/**
	 * Modifie la voiture {@link Voiture} situ�e � la position i par "voiture"
	 * @param i int position de la voiture dans la liste des {@link Voiture} de l'�v�nement
	 * @param voiture nouvelle voiture � ins�rer � la position i
	 */
	public void modifierVoiture(int i, Voiture voiture) {
		if(i < collectionVoitures_.size())
			collectionVoitures_.set(i, voiture);
	}

	/**
	 * Supprime la voiture {@link Voiture} situ�e � la position i
	 * @param i int position de la voiture dans la liste des {@link Voiture} de l'�v�nement
	 * @param oldVoiture voiture � supprimer
	 */
	public void supprimerVoiture(int i, Voiture oldVoiture) {
		if (oldVoiture == null)
		if (this.collectionVoitures_ != null)
			if (this.collectionVoitures_.contains(oldVoiture))
				this.collectionVoitures_.remove(oldVoiture);
		if( i > -1 && i < this.collectionVoitures_.size())
			this.collectionVoitures_.remove(i);
		
		setChanged();
		notifyObservers();
	}

	/**
	 * Sauvegarde l'�tat de l'�v�nement courant.
	 * Cette sauvegarde consiste en la sauvegarde au format XML de 
	 * l'ensemble des �l�ments de l'�v�nement pour une utilisation future.
	 */
	public void sauvegarderEvenement() {
		// TODO: implement
	}


	/**
	 * Parcours la liste des {@link EssaiOrCourse} de l'�v�nement
	 * @return {@link Iterator} sur la liste des {@link EssaiOrCourse}
	 */
	public Iterator<EssaiOrCourse> getIteratorCollectionEssaiCourse_() {
		if (collectionEssaiCourse_ == null)
			collectionEssaiCourse_ = new ArrayList<EssaiOrCourse>();
		return collectionEssaiCourse_.iterator();
	}

	/**
	 * Parcours la liste des {@link Voiture} de l'�v�nement
	 * @return {@link Iterator} sur la liste des {@link Voiture}
	 */
	public Iterator<Voiture> getIteratorCollectionVoitures_() {
		if (collectionVoitures_ == null)
			collectionVoitures_ = new ArrayList<Voiture>();
		return collectionVoitures_.iterator();
	}

	/**
	 * @return the nomEvenement_
	 */
	public String getNomEvenement_() {
		return nomEvenement_;
	}

	/**
	 * @param nomEvenement_ the nomEvenement_ to set
	 */
	public void setNomEvenement_(String nomEvenement_) {
		this.nomEvenement_ = nomEvenement_;
	}

	/**
	 * @return the nomCircuit_
	 */
	public String getNomCircuit_() {
		return nomCircuit_;
	}
	/**
	 * 
	 * @return
	 */
	public int sizeOfListeCourse(){
		if(this.collectionEssaiCourse_.isEmpty()) return 0;
		return this.collectionEssaiCourse_.size();
	}
	
	public int sizeOfListeVoitures(){
		if(this.collectionVoitures_.isEmpty()) return 0;
		return this.collectionVoitures_.size();
	}

	/**
	 * @param nomCircuit_ the nomCircuit_ to set
	 */
	public void setNomCircuit_(String nomCircuit_) {
		this.nomCircuit_ = nomCircuit_;
	}

	/**
	 * @return the longueurCircuit_
	 */
	public float getLongueurCircuit_() {
		return longueurCircuit_;
	}

	/**
	 * @param longueurCircuit_ the longueurCircuit_ to set
	 */
	public void setLongueurCircuit_(float longueurCircuit_) {
		this.longueurCircuit_ = longueurCircuit_;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Evenement event) {
		boolean isTrue = (this.longueurCircuit_ == event.longueurCircuit_) && 
				(this.nomCircuit_.equals(event.nomCircuit_)) && 
				(this.nomEvenement_.equals(event.nomEvenement_));

		return isTrue;
	}

	public List<EssaiOrCourse> getCollectionEssaiCourse_() {

		return new ArrayList<EssaiOrCourse>(collectionEssaiCourse_);
	}
	
	public List<Voiture> getCollectionVoiture_(){
		return new ArrayList<Voiture>(this.collectionVoitures_);
	}

	public EssaiOrCourse getEssaiOrCourse_(int i) {
		
		return this.collectionEssaiCourse_.get(i);
	}

	public Voiture getVoiture(int i) {
		return this.collectionVoitures_.get(i);
		
	}

}