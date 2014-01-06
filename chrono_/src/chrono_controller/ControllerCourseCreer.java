package chrono_controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.text.Element;





import chrono_model.EssaiOrCourse;
import chrono_model.Evenement;
import chrono_model.TypeEssaiOrCourse;
import chrono_model.Voiture;
import chrono_utils.JDomOperations;
import chrono_views.CourseCreerIhmImpl;
import chrono_views.EvenementIhmImpl;

public class ControllerCourseCreer extends Controller{

	private EssaiOrCourse course_;

	public String nomCourse_;
	private boolean isEssai_ = true;
	private boolean isDepartAuto_ = true;
	private int nbToursMaxPilote_ = 0;
	private long heureDepart_;
	private int dureeTotaleMaxPilote_;
	private int dureeConsecutiveMaxPilote_;
	private List<String> listVoitureRattachees_ = new ArrayList<String>();
	private String commentaires_;

	private CourseCreerIhmImpl courseCreerIhmImpl;
	
	private Evenement evenement_;
	
	 /** L'instance statique */
    private static ControllerCourseCreer instance;
	
	public static ControllerCourseCreer getInstance() {
        if (null == instance) { // Premier appel
            instance = new ControllerCourseCreer();
        }
        return instance;
    }

    /** Constructeur redéfini comme étant privé pour interdire
    * son appel et forcer à passer par la méthode 
    */
    private ControllerCourseCreer() {
    	
    }

    public void displayCourseCreer(Evenement observable_){
//    	evenement_=setEvenementsElement();
//    	evenement_=ControllerEvent.getInstance().getEvent_();
    	courseCreerIhmImpl=new CourseCreerIhmImpl(null, observable_);
//    	evenement_.addObserver(eventIhmImpl);
    	courseCreerIhmImpl.display();
    	
    }
	
	
	
	public void control (CourseCreerIhmImpl a, Evenement event){
//		event=new Evenement();
		course_ = new EssaiOrCourse();
		// R�cup�re le nom de la course
		nomCourse_ = courseCreerIhmImpl.getNomCourse().getText();
		this.course_.setNomEssaiOrCourse_(nomCourse_);

		// Boolean qui dit de quel type est notre essai (essai ou course)
		isEssai_ = courseCreerIhmImpl.isEssai_();
		if(isEssai_)
			this.course_.setTypeEssaiOrCourse_(TypeEssaiOrCourse.Essai);
		else 
			this.course_.setTypeEssaiOrCourse_(TypeEssaiOrCourse.Course);

		// boolean qui dit si la course est lanc�e par un d�part auto ou non
		isDepartAuto_ = courseCreerIhmImpl.isDemarrageAuto_();

		// nb de tour maximum du pilote
		String nbToursMaxP = courseCreerIhmImpl.getNbToursMaxPilote().getText();
		if(!nbToursMaxP.isEmpty()){
			nbToursMaxPilote_ = Integer.parseInt(nbToursMaxP);
			course_.setNbToursMax_(nbToursMaxPilote_);
		}

		// Heure de d�part de la course
		//heureDepart_ = c.gethDepart();
		course_.setHeureDebut_(new Date(heureDepart_));
		//System.out.println("Depart  "+c.gethDepart()+"   jour  "+c.getDateDepart_().getText());

		// dur�e totale max de pilotage
		String dureeToMaxPil = courseCreerIhmImpl.getDureeTotMaxPilote().getText();
		if(!dureeToMaxPil.isEmpty())
			dureeTotaleMaxPilote_ = Integer.parseInt(dureeToMaxPil);

		// dur�e cons�cutive max de conduite d'un pilote
		String dureeConsMaxP = courseCreerIhmImpl.getDureeConsecMaxPilote().getText();
		if(!dureeConsMaxP.isEmpty())
			dureeConsecutiveMaxPilote_ = Integer.parseInt(dureeConsMaxP);

		// liste des voiture de la course
		ListModel liste = courseCreerIhmImpl.getList_voitures_of_course().getModel();
		List<Voiture> listVoiture = this.courseCreerIhmImpl.getListVoiture_();
		for(int i = 0; i < listVoiture.size(); i++)
			this.course_.ajouterVoiture(listVoiture.get(i));

		// commentaires
		commentaires_ = courseCreerIhmImpl.getCommentaires().getText();

		
		// ajout de la course à la liste des courses de l'évènement
		event.creerCourse(course_);
		
		System.out.println("Nom course ### CONTROLLER ####  \t"+nomCourse_+"  nbTours   "+nbToursMaxPilote_+ "   Essais "+isEssai_+"  demarrage   "+isDepartAuto_ );
		for(int i = 0 ; i< listVoitureRattachees_.size(); i++)
			System.out.println("Element\t"+listVoitureRattachees_.get(i));

		System.out.println("listecourses dans event size="+event.sizeOfListeCourse());
		
//		saveCourse(event);
		
		//on sauvegarde dans un fichier temporaire listcourses
		saveCoursesTemp(course_);
		
	}

	private void saveCoursesTemp(EssaiOrCourse course) {
		JDomOperations.saveCoursesinTempFile(course);
		
	}

	private void saveCourse(Evenement event) {
		JDomOperations.saveCourseCreerToXml(event);
	}
}
