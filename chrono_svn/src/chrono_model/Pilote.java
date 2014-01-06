/***********************************************************************
 * Module:  Pilote.java
 * Author:  29004847
 * Purpose: Defines the Class Pilote
 ***********************************************************************/

package chrono_model;

import java.util.*;

/** liste des pilotes conduisant les differentes voitures participantes à la course */
public class Pilote {
	private String nomPilote_;
	private String couleurCasque_;
	private String lienSurImage_;


	public String getNomPilote_() {
		return nomPilote_;
	}
	public void setNomPilote_(String nomPilote_) {
		this.nomPilote_ = nomPilote_;
	}
	public String getCouleurCasque_() {
		return couleurCasque_;
	}
	public void setCouleurCasque_(String couleurCasque_) {
		this.couleurCasque_ = couleurCasque_;
	}
	public Pilote(String nomPilote_, String couleurCasque_) {
		super();
		this.nomPilote_ = nomPilote_;
		this.couleurCasque_ = couleurCasque_;
	}


	public Pilote(String nomPilote_, String couleurCasque_, String lienSurImage_) {
		super();
		this.nomPilote_ = nomPilote_;
		this.couleurCasque_ = couleurCasque_;
		this.lienSurImage_ = lienSurImage_;
	}
	public Pilote(String nomPilote_) {
		this.nomPilote_= nomPilote_;
		this.couleurCasque_ = "";

	}
	public String getLienSurImage_() {
		return lienSurImage_;
	}
	public void setLienSurImage_(String lienSurImage_) {
		this.lienSurImage_ = lienSurImage_;
	}

}