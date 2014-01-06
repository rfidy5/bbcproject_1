package chrono_controller;

//import org.jdom2.Attribute;
//import org.jdom2.Document;
//import org.jdom2.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;

import chrono_model.EssaiOrCourse;
import chrono_model.Evenement;
import chrono_model.Voiture;
import chrono_utils.JDomOperations;
import chrono_views.ChronoIhmImpl;
import chrono_views.EvenementIhmImpl;

public class ControllerEvenement extends AbstractController{

	private Evenement evenement_;
	private EvenementIhmImpl ihmEvent_;
	private ChronoIhmImpl ihmChrono_;
	
    /** L'instance statique */
    private static ControllerEvenement instance;
	
	public static ControllerEvenement getInstance() {
        if (null == instance) { // Premier appel
            instance = new ControllerEvenement();
        }
        return instance;
    }

    /** Constructeur redéfini comme étant privé pour interdire
    * son appel et forcer à passer par la méthode 
    */
    private ControllerEvenement() {
    	
    }

    public void display(){
    	evenement_=setEvenementsElement();
    	EvenementIhmImpl eventIhmImpl=new EvenementIhmImpl(evenement_);
    	evenement_.addObserver(eventIhmImpl);
    	eventIhmImpl.display();
    	
    }
	public Evenement setEvenementsElement(){
		evenement_=new Evenement();
		Element elementactuel=JDomOperations.getElementActuel_();
		evenement_.setNomEvenement_(elementactuel.getAttributeValue("nomEvent"));
		evenement_.setNomCircuit_(elementactuel.getAttributeValue("nomCircuitEvent"));
		evenement_.setLongueurCircuit_(Float.parseFloat(elementactuel.getAttributeValue("longCircuitEvent")));
		
//		List<Voiture> listVoitures=new ArrayList<Voiture>();
		Element elementlistvoitures=elementactuel.getChild("listvoitures");
		if(elementlistvoitures.getChildren()!=null){
			List<Element> listVoitures=elementlistvoitures.getChildren();
			for(Element elementVoiture:listVoitures){
				Voiture voiture=new Voiture();
				voiture.setNumeroVoiture_(elementVoiture.getAttributeValue("numeroVoiture"));
				voiture.setCouleur_(elementVoiture.getAttributeValue("couleur"));
				evenement_.creerVoiture(voiture);
			}
		}
//		List<EssaiOrCourse> listCourses=new ArrayList<EssaiOrCourse>();
		Element elementlistcourses=elementactuel.getChild("listcourses");
		if(elementlistcourses.getChildren()!=null){
			List<Element> listecourses=elementlistcourses.getChildren();
			
			for(Element elementcourse:listecourses){
				EssaiOrCourse course=new EssaiOrCourse();
				course.setNomEssaiOrCourse_(elementcourse.getAttributeValue("nomEssaiOrCourse"));
				evenement_.creerCourse(course);
			}
		}
//		evenement_.notifyObservers();
		return evenement_;
	}
	
//	/**
//	 * @param evenement_
//	 * @param ihmEvent
//	 * @param ihmChrono
//	 */
//	public ControllerEvenement(EvenementIhmImpl ihmEvent,
//			ChronoIhmImpl ihmChrono) {
//
//		this.ihmEvent_ = ihmEvent;
//		this.ihmChrono_ = ihmChrono;
//	}
	
	/**
	 * construit un Document à partir d'un objet 
	 */
	@Override
	public Document constructxml(Object object) {
		
		Element evenementracine=new Element("evenement");
		Document document=new Document(evenementracine);
		
		Attribute attributsEvenementNom=new Attribute("nomEvent",((Evenement)object).getNomEvenement_());
		Attribute attributsEvenementNomCircuit=new Attribute("nomCircuitEvent",((Evenement)object).getNomCircuit_());
		Attribute attributsEvenementLongueurCircuit=new Attribute("longCircuitEvent",Float.toString(((Evenement)object).getLongueurCircuit_()));
		
		evenementracine.setAttribute(attributsEvenementNom);
		evenementracine.setAttribute(attributsEvenementNomCircuit);
		evenementracine.setAttribute(attributsEvenementLongueurCircuit);

		
//		ihmEvent_.getListVoitures_().
		
//		//On crée un nouvel Attribut classe et on l'ajoute à etudiant
//		//grâce à la méthode setAttribute
//		Attribute classe = new Attribute("classe","P2");
//		etudiant.setAttribute(classe);
//		//On crée un nouvel Element nom, on lui assigne du texte
//		//et on l'ajoute en tant qu'Element de etudiant
//		Element nom = new Element("nom");
//		nom.setText("CynO");
//		etudiant.addContent(nom);
//		JDom_Save jdomsave=new JDom_Save();
		
//		affiche(document); //définie plus loin
		
//		JDomOperations.enregistre(document,JDomOperations.getFilepath_event()); //définie plus loin
		
		return document;
	}

	@Override
	public Object getObjectByXml(String filepath) {
		SAXBuilder sxb = new SAXBuilder();
		Document document=new Document();
		Object obj=null;
		try
		{
			//On crée un nouveau document JDOM avec en argument le fichier XML
			//Le parsing est terminé ;)
			
			document = sxb.build(new File(filepath));
			
			//il faut valider le fichier xml avec la dtd
//			JDomOperations.validateJDOM(document);
			obj=constructObject(document);
		}
		catch(Exception e){
			// afficher un popup qui dit que le format du fichier xml entré n'est pas valide
			System.out.println("format xml non respecté");

		}
		
		//On initialise un nouvel élément racine avec l'élément racine du document.
//		Element racine = document.getRootElement();
//		List<Object> listEvents=getListObjectByFiltre(document);
		
		return obj;
	}
	private Object constructObject(Document pdocument){
		Element elementRacine = pdocument.getRootElement();
	
		evenement_=new Evenement();
		evenement_.setNomEvenement_(elementRacine.getAttributeValue("nomEvent"));
		evenement_.setNomCircuit_(elementRacine.getAttributeValue("nomCircuitEvent"));
		evenement_.setLongueurCircuit_(Float.parseFloat(elementRacine.getAttributeValue("longCircuitEvent")));
		
		
		
		
//		List<Element> listElementVoiture = elementRacine.getChildren("course");
//		int nombrevoiture=listElementVoiture.size();
//		for(int i=0;i<nombrevoiture;i++){
//			Voiture voiture=new Voiture();
////			voiture.setCouleur_(couleur_);
//		}
		
//		List<Object> listEvents=new ArrayList<Object>();
//		listEvents.add(evenement_);
		
		return evenement_;
	}

	public Evenement getObjectViaIhm(EvenementIhmImpl eventIhm) {
		Evenement event=new Evenement();
		event.setNomEvenement_(eventIhm.getJtxtNomEvenement_().getText());
		event.setNomCircuit_(eventIhm.getjTxtNomCircuit_().getText());
		event.setLongueurCircuit_(Float.parseFloat(eventIhm.getJtxtLongueurCircuit_().getText()));
		
		return event;
	}
}
