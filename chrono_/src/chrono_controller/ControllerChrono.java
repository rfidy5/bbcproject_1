package chrono_controller;

import java.awt.EventQueue;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import chrono_model.Chrono;
import chrono_model.Evenement;
import chrono_utils.JDomOperations;
import chrono_views.ChronoIhmImpl;

public class ControllerChrono extends Controller{
	
	private ChronoIhmImpl chronoView_;
//	private ControllerEvenement controlEvent_;
	private Evenement eventModel;
	private static String NomEvenementActuel;
	
	 /** L'instance statique */
    private static ControllerChrono instance;
	
	public static ControllerChrono getInstance() {
        if (null == instance) { // Premier appel
            instance = new ControllerChrono();
        }
        return instance;
    }

    /** Constructeur redéfini comme étant privé pour interdire
    * son appel et forcer à passer par la méthode <link
    */
    private ControllerChrono() {
    }
	
	
	/**
	 * 
	 */
//	public ControllerChrono() {
////		this.chronoView = chronoView;
//	}


	public void displayChronoView(){
//		chronoView_=new ChronoIhmImpl(controlEvent_);
		chronoView_=new ChronoIhmImpl();
		chronoView_.display();
	}
	

	public void controlBoutonsListener(String commande){
		
		
	}


	public List<String> getListOfEvents() {
		List<String> listEventsString=JDomOperations.getEventsNameByXml();
//		System.out.println("listofEventsController taille ="+listEventsString.size());
		return listEventsString;
	}
	public Element chargerEvent(String nomEvent){
		setNomEvenementActuel(nomEvent);
		Element element=JDomOperations.getElementByName(nomEvent);
		System.out.println(element.getAttributeValue("nomEvent"));
		System.out.println(element.getAttributeValue("nomCircuitEvent"));
		System.out.println(element.getAttributeValue("longCircuitEvent"));
		return element;
	}
	
	
	public static String getNomEvenementActuel() {
		return NomEvenementActuel;
	}

	public static void setNomEvenementActuel(String nomEvenementActuel) {
		NomEvenementActuel = nomEvenementActuel;
	}

	public void supprimerEvent(String eventNameString) {
		JDomOperations.removeEventinXml(eventNameString);
		
	}
}
