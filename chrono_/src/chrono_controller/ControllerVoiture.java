package chrono_controller;

import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;

import chrono_model.EssaiOrCourse;
import chrono_model.Evenement;
import chrono_model.Pilote;
import chrono_model.Voiture;
import chrono_utils.JDomOperations;
import chrono_views.CourseCreerIhmImpl;
import chrono_views.VoitureIhmImpl;

public class ControllerVoiture extends Controller {

	private Voiture voiture_;

	private VoitureIhmImpl ihmVoiture_;

	
	 /** L'instance statique */
    private static ControllerVoiture instance;
	
	public static ControllerVoiture getInstance() {
        if (null == instance) { // Premier appel
            instance = new ControllerVoiture();
        }
        return instance;
    }

    /** Constructeur redéfini comme étant privé pour interdire
    * son appel et forcer à passer par la méthode 
    */
    private ControllerVoiture() {
    	
    }
	
	
//	/**
//	 * @param voiture_
//	 * @param ihmVoiture_
//	 */
//	public ControllerVoiture(Voiture voiture_, VoitureIhmImpl ihmVoiture_) {
//		super();
//		this.voiture_ = voiture_;
//		this.ihmVoiture_ = ihmVoiture_;
//	}

	public void displayVoiture(Evenement observable_){
//    	evenement_=setEvenementsElement();
//    	evenement_=ControllerEvent.getInstance().getEvent_();
    	ihmVoiture_=new VoitureIhmImpl(null, observable_);
//    	evenement_.addObserver(eventIhmImpl);
    	ihmVoiture_.display();
    	
    }
	
	public void control (VoitureIhmImpl a, Evenement event){
		voiture_=new Voiture();
		// Num�ro de la voiture
		String numVoiture_ = ihmVoiture_.getTextFieldNumVoiture().getText();
		if(!numVoiture_.isEmpty())
			this.voiture_.setNumeroVoiture_(numVoiture_);

		// Couleur de la voiture
		String couleur_ = ihmVoiture_.getTextField_CouleurVoiture().getText();
		if(!couleur_.isEmpty())
			this.voiture_.setCouleur_(couleur_);

		// Liste des Pilotes
		List<Pilote> listePilote = ihmVoiture_.getListePilotes_();
		int size = listePilote.size();
		if(size > 0){
			for(Iterator<Pilote> it = listePilote.iterator(); it.hasNext();)
				voiture_.addCollectionPilotes_Tops(it.next());
		}
		
		// Nombre de tours par relais
		String nb = ihmVoiture_.getTextField_NbToursParRelais().getText();
		if(!nb.isEmpty()){
			int nbTourParRelais = Integer.parseInt(nb);
			this.voiture_.setNombreDeTours_(nbTourParRelais);
		}

		voiture_ = this.voiture_;
		event.creerVoiture(voiture_);

		System.out.println("ici");
		for(int i = 0; i< event.getCollectionVoiture_().size(); i++){
			System.out.println("Num\t"+event.getCollectionVoiture_().get(i).getNumeroVoiture_());
		}
		
		saveVoituresTemp(voiture_);
	}
	
	
	private void saveVoituresTemp(Voiture pvoiture) {
		JDomOperations.saveVoituresinTempFile(pvoiture);
		
	}
}
