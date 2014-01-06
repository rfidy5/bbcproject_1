/***********************************************************************
 * Module:  Top.java
 * Author:  29004847
 * Purpose: Defines the Class Top
 ***********************************************************************/

package chrono_model;

import java.util.*;

/** Un top enregistre les informations concernant une voiture de la course et un pilote à un moment donné */
public class Top {
	private String etatTop_;
	private String tempsDepuisTopPrecedent_;
	private String heureDePassage_;
	private double heureDePassageMS_;
	private String commentaire_;

	public Top(String etatTop_, String tempsDepuisTopPrecedent_2,
			String heureDePassage_2, double heureDePassageMS_, String commentaire_) {
		super();
		this.etatTop_ = etatTop_;
		this.tempsDepuisTopPrecedent_ = tempsDepuisTopPrecedent_2;
		this.heureDePassage_ = heureDePassage_2;
		this.heureDePassageMS_ = heureDePassageMS_;
		this.commentaire_ = commentaire_;
	}
	public String getEtatTop_() {
		return etatTop_;
	}
	public void setEtatTop_(String etatTop_) {
		this.etatTop_ = etatTop_;
	}
	public String getTempsDepuisTopPrecedent_() {
		return tempsDepuisTopPrecedent_;
	}
	public void setTempsDepuisTopPrecedent_(String tempsDepuisTopPrecedent_) {
		this.tempsDepuisTopPrecedent_ = tempsDepuisTopPrecedent_;
	}
	public String getHeureDePassage_() {
		return heureDePassage_;
	}
	public void setHeureDePassage_(String heureDePassage_) {
		this.heureDePassage_ = heureDePassage_;
	}
	public double getHeureDePassageMS_() {
		return heureDePassageMS_;
	}
	public void setHeureDePassageMS_(double heureDePassageMS_) {
		this.heureDePassageMS_ = heureDePassageMS_;
	}
	public String getCommentaire_() {
		return commentaire_;
	}
	public void setCommentaire_(String commentaire_) {
		this.commentaire_ = commentaire_;
	}

}