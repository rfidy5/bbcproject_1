/***********************************************************************
 * Module:  EssaiOrCourse.java
 * Author:  bbc
 * Purpose: Defines the Class EssaiOrCourse
 ***********************************************************************/

package chrono_model;

import java.util.*;


/** Une course contient la liste des voitures qui y participent, et fais partie d'un evenement */
public class EssaiOrCourse extends Observable{
	private String nomEssaiOrCourse_;
	private java.util.Date heureDebut_;
	private java.util.Date heureFin_;
	private TypeEssaiOrCourse typeEssaiOrCourse_;
	private boolean departAutomatique_;
	private TypeFin typeDeFin_;
	private int nbToursMax_;
	private float dureeMaxPilotage_;
	private float dureeConsecutiveMaxPilotage_;
	private String commentaire_;
	private int nbToursParRelais_;

	private java.util.List<Voiture> collectionVoiture_;


	/**
	 * permet d'ajouter une nouvelle voiture é la course
	 * @param newVoiture
	 * precondition :  @param doit avoir été créée au préalable et est issue d'une liste contenant toutes les voitures 
	 */
	public void ajouterVoiture(Voiture newVoiture) {
		// TODO: implement
		if (newVoiture == null)
			/* ajouter ici un popup qui permettra d'afficher "pas de voiture selectionnée" */
			return;
		if (this.collectionVoiture_ == null)
			this.collectionVoiture_ = new ArrayList<Voiture>();
		this.collectionVoiture_.add(newVoiture);
		/* ajouter ici un popup qui permettra d'afficher "la voiture a déjé été ajoutée" */
		System.out.println("coucou");
	}
	/**
	 * permet de retirer une voiture de la course
	 * @param newVoiture
	 * 
	 */
	public void retirerVoiture(Voiture voitureSelected) {
		// TODO: implement
		if (voitureSelected == null)
			/* ajouter ici une méthode qui permettra d'afficher "pas de voiture selectionnée" */
			return;
		if (this.collectionVoiture_ != null){
			if (this.collectionVoiture_.contains(voitureSelected)){
				this.collectionVoiture_.remove(voitureSelected);
			}else{
				/* ajouter ici une méthode qui permettra d'afficher "pas de voiture é retirer" */
			}

		}
	}
	/**
	 * permet de sauvegarder une nouvelle nouvelle course ou de modifier une déjà existante en récupérant le fichier xml
	 * @param pcourse
	 * 
	 */
	public void sauvegarderCourse(EssaiOrCourse pcourse) {
		// TODO: implement
		if(pcourse == null){
			pcourse=new EssaiOrCourse();
			/* récupérer les champs depuis l'ihm pour la mettre dans l'objet*/ 

		}else{
			/* récupérer les champs depuis l'ihm pour la mettre dans l'objet*/ 
		}
		/* ajouter une méthode permettant d'enregistrer dans un fichier xml */
	}
	/**
	 * associe un top à une voiture
	 * @param pVoiture
	 * @param pOldVoiture 
	 * @param ptop
	 */
	public void associerTopAVoiture(Voiture pNewVoiture, Pilote pilote, Top ptop) {
		int index = this.estContenu(this.getCollectionVoiture_(), pNewVoiture);
		// vérifier que la voiture existe dans la liste des voitures de la course.
		if(index > -1){
			this.getCollectionVoiture_().get(index).addCollectionPilotes_Tops_(ptop, pilote);
		}
		// vérifie s'il y est nécessaire de desassocier d'abord le top à la voiture 
		//		if(pOldVoiture!=null){
		//			dessassocierTopDeVoiture(pOldVoiture, ptop);
		//		}
		//		List<Top> listTop=pNewVoiture.getCollectionTops_();
		//		listTop.add(ptop);



		/* ajouter une méthode permettant de mettre à jour le fichier xml */
	}
	/**
	 * permet d'enlever un top de la liste des tops d'une voiture selectionnée
	 * @param pVoiture 
	 * @param ptop
	 */
	public void dessassocierTopDeVoiture(Voiture pVoiture, Pilote p, Top ptop) {
		int index = this.estContenu(this.collectionVoiture_, pVoiture);
		if(index > -1){
			this.getCollectionVoiture_().get(index).removeCollectionTops_(ptop, p);
		}
		/* ajouter une méthode permettant de mettre à jour le fichier xml */
	}

	/**
	 * @return the nomEssaiOrCourse_
	 */
	public String getNomEssaiOrCourse_() {
		return nomEssaiOrCourse_;
	}
	/**
	 * @param nomEssaiOrCourse_ the nomEssaiOrCourse_ to set
	 */
	public void setNomEssaiOrCourse_(String nomEssaiOrCourse_) {
		this.nomEssaiOrCourse_ = nomEssaiOrCourse_;
	}
	/**
	 * @return the heureDebut_
	 */
	public java.util.Date getHeureDebut_() {
		return heureDebut_;
	}
	/**
	 * @param heureDebut_ the heureDebut_ to set
	 */
	public void setHeureDebut_(java.util.Date heureDebut_) {
		this.heureDebut_ = heureDebut_;
	}
	/**
	 * @return the heureFin_
	 */
	public java.util.Date getHeureFin_() {
		return heureFin_;
	}
	/**
	 * @param heureFin_ the heureFin_ to set
	 */
	public void setHeureFin_(java.util.Date heureFin_) {
		this.heureFin_ = heureFin_;
	}
	/**
	 * @return the typeEssaiOrCourse_
	 */
	public TypeEssaiOrCourse getTypeEssaiOrCourse_() {
		return typeEssaiOrCourse_;
	}
	/**
	 * @param typeEssaiOrCourse_ the typeEssaiOrCourse_ to set
	 */
	public void setTypeEssaiOrCourse_(TypeEssaiOrCourse typeEssaiOrCourse_) {
		this.typeEssaiOrCourse_ = typeEssaiOrCourse_;
	}
	/**
	 * @return the departAutomatique_
	 */
	public boolean isDepartAutomatique_() {
		return departAutomatique_;
	}
	/**
	 * @param departAutomatique_ the departAutomatique_ to set
	 */
	public void setDepartAutomatique_(boolean departAutomatique_) {
		this.departAutomatique_ = departAutomatique_;
	}
	/**
	 * @return the typeDeFin_
	 */
	public TypeFin getTypeDeFin_() {
		return typeDeFin_;
	}
	/**
	 * @param typeDeFin_ the typeDeFin_ to set
	 */
	public void setTypeDeFin_(TypeFin typeDeFin_) {
		this.typeDeFin_ = typeDeFin_;
	}
	/**
	 * @return the nbToursMax_
	 */
	public int getNbToursMax_() {
		return nbToursMax_;
	}
	/**
	 * @param nbToursMax_ the nbToursMax_ to set
	 */
	public void setNbToursMax_(int nbToursMax_) {
		this.nbToursMax_ = nbToursMax_;
	}
	/**
	 * @return the dureeMaxPilotage_
	 */
	public float getDureeMaxPilotage_() {
		return dureeMaxPilotage_;
	}
	/**
	 * @param dureeMaxPilotage_ the dureeMaxPilotage_ to set
	 */
	public void setDureeMaxPilotage_(float dureeMaxPilotage_) {
		this.dureeMaxPilotage_ = dureeMaxPilotage_;
	}
	/**
	 * @return the dureeConsecutiveMaxPilotage_
	 */
	public float getDureeConsecutiveMaxPilotage_() {
		return dureeConsecutiveMaxPilotage_;
	}
	/**
	 * @param dureeConsecutiveMaxPilotage_ the dureeConsecutiveMaxPilotage_ to set
	 */
	public void setDureeConsecutiveMaxPilotage_(float dureeConsecutiveMaxPilotage_) {
		this.dureeConsecutiveMaxPilotage_ = dureeConsecutiveMaxPilotage_;
	}
	/**
	 * @return the commentaire_
	 */
	public String getCommentaire_() {
		return commentaire_;
	}
	/**
	 * @param commentaire_ the commentaire_ to set
	 */
	public void setCommentaire_(String commentaire_) {
		this.commentaire_ = commentaire_;
	}
	/**
	 * @return the collectionVoiture_
	 */
	public java.util.List<Voiture> getCollectionVoiture_() {
		return new ArrayList<Voiture>(this.collectionVoiture_);
	}
	/**
	 * @param collectionVoiture_ the collectionVoiture_ to set
	 */
	public void setCollectionVoiture_(java.util.List<Voiture> collectionVoiture_) {
		this.collectionVoiture_ = collectionVoiture_;
	}
	/** @pdGenerated default iterator getter */
	public java.util.Iterator getIteratorCollectionVoiture_() {
		if (collectionVoiture_ == null)
			collectionVoiture_ = new java.util.ArrayList<Voiture>();
		return collectionVoiture_.iterator();
	}

	//   /** @pdGenerated default getter */
	//   public java.util.List<Voiture> getCollectionVoiture_() {
	//      if (collectionVoiture_ == null)
	//         collectionVoiture_ = new java.util.ArrayList<Voiture>();
	//      return collectionVoiture_;
	//   }
	//   
	//   
	//   /** @pdGenerated default setter
	//     * @param newCollectionVoiture_ */
	//   public void setCollectionVoiture_(java.util.List<Voiture> newCollectionVoiture_) {
	//      removeAllCollectionVoiture_();
	//      for (java.util.Iterator iter = newCollectionVoiture_.iterator(); iter.hasNext();)
	//         addCollectionVoiture_((Voiture)iter.next());
	//   }
	//   
	//   
	//   /** @pdGenerated default removeAll */
	//   public void removeAllCollectionVoiture_() {
	//      if (collectionVoiture_ != null)
	//         collectionVoiture_.clear();
	//   }

	/**
	 *  
	 * @return collectionVoitureSize
	 */
	public int getSizeCollectionVoiture(){
		return this.collectionVoiture_.size();
	}

	/**
	 * 
	 * @param list
	 * @param v
	 * @return
	 */
	public int estContenu (List<Voiture> list, Voiture v){

		int i = -1;

		List<String> listeNumVoiture_ = new ArrayList<String>();

		for(Iterator<Voiture> iter = list.iterator(); iter.hasNext();){
			listeNumVoiture_.add(iter.next().getNumeroVoiture_());
		}

		if(listeNumVoiture_.contains(v.getNumeroVoiture_()))
			i = listeNumVoiture_.indexOf(v.getNumeroVoiture_());
		return i;
	}
	/**
	 * @return the nbToursParRelais_
	 */
	public int getNbToursParRelais_() {
		return nbToursParRelais_;
	}
	/**
	 * @param nbToursParRelais_ the nbToursParRelais_ to set
	 */
	public void setNbToursParRelais_(int nbToursParRelais_) {
		this.nbToursParRelais_ = nbToursParRelais_;
	}

}