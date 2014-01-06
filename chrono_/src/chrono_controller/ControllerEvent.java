/***********************************************************************
 * Module:  Controller.java
 * Author:  29004847
 * Purpose: Defines the Class Controller
 ***********************************************************************/

package chrono_controller;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import chrono_model.*;
import chrono_utils.JDomOperations;
import chrono_views.*;

public class ControllerEvent extends Controller {
	private Evenement event_;
	private EvenementIhmImpl ihmEvent_;

	private String nomEvent_;
	private String nomCircuit_;
	private float longueurCircuit_;

	private List<EssaiOrCourse> listeCourse_ = new ArrayList<EssaiOrCourse>();
	private List<Voiture> listeVoiture_ = new ArrayList<Voiture>();

	 /** L'instance statique */
    private static ControllerEvent instance;
	
	public static ControllerEvent getInstance() {
        if (null == instance) { // Premier appel
            instance = new ControllerEvent();
        }
        return instance;
    }

    /** Constructeur redéfini comme étant privé pour interdire
    * son appel et forcer à passer par la méthode 
    */
    private ControllerEvent() {
    	
    }
	public void controlEvenement(EvenementIhmImpl ihmEvent_, Evenement observable_){
		event_ = observable_;

		// Nom du circuit
		nomCircuit_ = ihmEvent_.getjTxtNomCircuit_().getText();
		if(!nomCircuit_.isEmpty())
			event_.setNomCircuit_(nomCircuit_);

		// Nom du l'�v�nement
		nomEvent_ = ihmEvent_.getJtxtNomEvenement_().getText();
		event_.setNomEvenement_(nomEvent_);

		//longueurCircuit_ = ihmEvent_.getLongueur_();

		// Longueur circuit
		String longueur = ihmEvent_.getJtxtLongueurCircuit_().getText();
		if(!longueur.isEmpty()){
			longueurCircuit_ = ihmEvent_.getLongueur_();
			event_.setLongueurCircuit_( longueurCircuit_);
		}

		// Liste des voitures de l'�v�nement
		listeVoiture_ = new ArrayList<Voiture>();


		// Liste des courses de l'�v�nement
		listeCourse_ = new ArrayList<EssaiOrCourse>();

		observable_ = event_;

		System.out.println("Nom   "+event_.getNomEvenement_());
		System.out.println("Nom Circuit   "+event_.getNomCircuit_());
		System.out.println("Long   "+event_.getLongueurCircuit_());
		if(event_.sizeOfListeCourse() > 0)
			for(int i = 0; i < event_.sizeOfListeCourse(); i++)
				System.out.println("La course   #####"+event_.getCollectionEssaiCourse_().get(i));
		
		
		saveEvent(observable_);
	}
	public void displayEventIhm(){
		event_=setEvenementsElement();
    	EvenementIhmImpl eventIhmImpl=new EvenementIhmImpl(event_);
    	event_.addObserver(eventIhmImpl);
    	eventIhmImpl.display();
    	
    }
	public Evenement setEvenementsElement(){
		event_=new Evenement();
		if(JDomOperations.getElementActuel_()!=null){
			Element elementactuel=JDomOperations.getElementActuel_();
			event_.setNomEvenement_(elementactuel.getAttributeValue("nomEvent"));
			event_.setNomCircuit_(elementactuel.getAttributeValue("nomCircuitEvent"));
			event_.setLongueurCircuit_(Float.parseFloat(elementactuel.getAttributeValue("longCircuitEvent")));
			
	//		List<Voiture> listVoitures=new ArrayList<Voiture>();
			Element elementlistvoitures=elementactuel.getChild("listvoitures");
			if(elementlistvoitures.getChildren()!=null){
				List<Element> listVoitures=elementlistvoitures.getChildren();
				for(Element elementVoiture:listVoitures){
					Voiture voiture=new Voiture();
					voiture.setNumeroVoiture_(elementVoiture.getAttributeValue("numeroVoiture"));
					voiture.setCouleur_(elementVoiture.getAttributeValue("couleur"));
					event_.creerVoiture(voiture);
				}
			}
	//		List<EssaiOrCourse> listCourses=new ArrayList<EssaiOrCourse>();
			Element elementlistcourses=elementactuel.getChild("listcourses");
			if(elementlistcourses.getChildren()!=null){
				List<Element> listecourses=elementlistcourses.getChildren();
				
				for(Element elementcourse:listecourses){
					EssaiOrCourse course=new EssaiOrCourse();
					course.setNomEssaiOrCourse_(elementcourse.getAttributeValue("nomEssaiOrCourse"));
					event_.creerCourse(course);
				}
			}
	//		evenement_.notifyObservers();
		}
		return event_;
	}

	public void saveEvent(Evenement observable_) {
		JDomOperations.saveEvenementToXml(observable_);
		
	}

	public Evenement getEvent_() {
		return event_;
	}
}