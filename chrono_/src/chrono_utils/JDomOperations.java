package chrono_utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.*;
import org.jdom2.filter.Filter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;
import org.xml.sax.InputSource;

import chrono_model.EssaiOrCourse;
import chrono_model.Evenement;
import chrono_model.Voiture;


public class JDomOperations {

	public final static  String xmlDefaultPath="srcChrono\\chrono_xml\\listevents.xml";
	public final static  String xmlTempPathListCourses="srcChrono\\chrono_xml\\listcourses.xml";
	public final static  String xmlTempPathListVoitures="srcChrono\\chrono_xml\\listvoitures.xml";
	public final static String defaultFilename="Chrono.xml";
//	private static String filepath_event;
	private static Element elementActuel_;

	
	public static void validateJDOM(Document pdocument){
		try{

//			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			DocType type = new DocType ("evenement","evenement.dtd") ;
			pdocument.setDocType (type) ;
			XMLOutputter outputter = new XMLOutputter();
			
			outputter.output(pdocument, System.out);

		} catch (IOException e) {
			System.out.println("format xml non respecté _ 2");
//			e.printStackTrace();
			
		}
	}
	public static void enregistre(Document document,String fichierpath){
		try
		{
			//vérifie si document n'est pas nul
			if(document==null){
				document=new Document();
			}
				
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			//Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
			//avec en argument le nom du fichier pour effectuer la sérialisation.
			sortie.output(document, new FileOutputStream(fichierpath));
//			new File(fichier).createNewFile();
		}
		catch (java.io.IOException e){}
	}
//	public static String getFilepath_event() {
//		return filepath_event;
//	}
//
//	public static void setFilepath_event(String filepath_event) {
//		JDomOperations.filepath_event = filepath_event;
//	}
	public static Element getElementByName(String pName){
		SAXBuilder sxbuilder = new SAXBuilder();
		Element element=null;
		try {
			Document document = sxbuilder.build(new File(xmlDefaultPath));
			Element racine = document.getRootElement();
			List<Element> children = racine.getChildren();
			System.out.println("sizechildren="+children.size());
			for(Element child : children) {
				if(child.getAttribute("nomEvent").getValue().equalsIgnoreCase(pName)){
					System.out.println("atribute value="+child.getAttribute("nomEvent").getValue());
					elementActuel_=child;
					return child;
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return element;
	}
	public static List<String> getEventsNameByXml(){
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		List<String> listEvenementsString=null;
//		Object obj=null;
		try
		{			
			document = sxb.build(new File(xmlDefaultPath));
			
//			listevents=getEventsNameByDoc(document);
			
			Element racine = document.getRootElement();
			
//			System.out.println("racine="+racine.getText()+"finracine");
			List<Element> listEvenementsElement = racine.getChildren("evenement");
			listEvenementsString =new ArrayList<String>();
			
			for(Element evenementElement:listEvenementsElement){
				listEvenementsString.add(evenementElement.getAttributeValue("nomEvent"));
			}
			
//			System.out.println("listofEventsJdomtaille ="+listEvenementsString.size());
			
			//il faut valider le fichier xml avec la dtd
//			JDomOperations.validateJDOM(document);
//			afficheXml(document);
		}
		catch(Exception e){
			// afficher un popup qui dit que le format du fichier xml entré n'est pas valide
			System.out.println("format xml non respecté");
			System.out.println(e.getMessage());

		}
		return listEvenementsString;
	}
	/**
	 * permet de sauvegarder un evenement dans le fichier xml 
	 * en créant un nouveau element s'il n'existe pas encore 
	 * sinon en mettant à jour ses élements
	 * @param pevent
	 */
	public static void saveEvenementToXml(Evenement pevent){
//		SAXBuilder sxbuilder = new SAXBuilder();
//		Document document;
		
		//vérifier si l'élément actuel existe ou pas
		if(JDomOperations.getElementActuel_()==null){
			System.out.println("pas d'element actuel");
			SAXBuilder sxb = new SAXBuilder();
//			Document document=null;
			try{			
				Document document = sxb.build(new File(xmlDefaultPath));
	
				Element racine = document.getRootElement();
			
				Element newEvenementElement=new Element("evenement");
//				newEvenementElement.setAttribute("nomEvenement",pevent.getNomEvenement_()!=null?pevent.getNomEvenement_():"");
//				newEvenementElement.setAttribute("nomCircuit",pevent.getNomCircuit_()!=null?pevent.getNomCircuit_():"");
//				newEvenementElement.setAttribute("longueurCircuit",pevent.getLongueurCircuit_()>0?String.valueOf(pevent.getLongueurCircuit_()):"");
	
				// recuperation de la liste des voitures et des courses dans les Documents xml respectifs
				Document documentlistVoitures=getXmlList(xmlTempPathListVoitures);
				Document documentlistCourses=getXmlList(xmlTempPathListCourses);
				
				
				System.out.println("liste azo");
				
				Element rootListVoitures=documentlistVoitures.getRootElement();
				Element rootListCourses=documentlistCourses.getRootElement();
				
				List<Element> listVoitures=rootListVoitures.getChildren();
				List<Element> listCourses=rootListCourses.getChildren();
				
				for(int i=0;i<listVoitures.size();i++){
					System.out.println(listVoitures.get(i).getName());
				}
				
				System.out.println("chikldren azo");
				
				// creation des elements listvoitures et listcourses
				Element listVoituresElement=new Element("listvoitures");
				Element listCoursesElement=new Element("listcourses");
				
				System.out.println(listVoitures.size());
				System.out.println(listCourses.size());

				for(int i=0;i<listVoitures.size();i++){
					System.out.println("avant"+i);
					listVoituresElement.addContent(listVoitures.get(i));
					System.out.println("apres"+i);
				}
//				System.out.println("between");
//				for(Element course:listCourses){
//					listCoursesElement.addContent(course);
//				}
//				listVoituresElement.add
//				
//				listVoituresElement.addContent(listVoitures);
//				listCoursesElement.addContent(listCourses);
					
				
				System.out.println("content ajouté");
				
				// ajout des elements listvoitures et listcourses dans l'element evenement
				newEvenementElement.addContent(listVoituresElement);
				newEvenementElement.addContent(listCoursesElement);
				
				racine.addContent(newEvenementElement);
				System.out.println("avant enregistre");
				enregistre(document, xmlDefaultPath);
			}
			catch(Exception e){
				e.getMessage();
	
			}

		}/*else{
		
			Element elementEvent=JDomOperations.getElementActuel_();
			Element listcoursesDefault=elementEvent.getChild("listcourses");
			Element listvoituresDefault=elementEvent.getChild("listvoitures");
	
			elementEvent.getAttribute("nomEvenement").setValue(pevent.getNomEvenement_());
			elementEvent.getAttribute("nomCircuit").setValue(pevent.getNomCircuit_());
			elementEvent.getAttribute("longueurCircuit").setValue(String.valueOf(pevent.getLongueurCircuit_()));
			
//			cleanTemp(xmlTempPathListCourses,"listcourses");
//			cleanTemp(xmlTempPathListVoitures,"listevents");
			
			List<Element> elementListcourses=getListinTemp(xmlTempPathListCourses);
			List<Element> elementListvoitures=getListinTemp(xmlTempPathListVoitures);
			

			System.out.println("taillecourse"+elementListcourses.size());
			System.out.println("taillevoiture"+elementListvoitures.size());
			for(Element courseElement:elementListcourses){
				listcoursesDefault.addContent(courseElement);
			}
			for(Element voitureElement:elementListvoitures){
				listvoituresDefault.addContent(voitureElement);
			}
			cleanTemp(xmlTempPathListCourses,"listcourses");
			cleanTemp(xmlTempPathListVoitures,"listevents");
			
			JDomOperations.enregistre(elementEvent.getDocument(), xmlDefaultPath);
		}*/
	}
	
	/**
	 * recupere le Document via un url xml
	 * @param xmltemppathlistvoitures2 chemin du fichier xml
	 * @return le document 
	 */
	private static Document getXmlList(String xmltemppathlistvoitures2) {
		SAXBuilder sxb = new SAXBuilder();
		Document document=null;
		try{			
			document = sxb.build(new File(xmltemppathlistvoitures2));
		}
		catch(Exception e){
			e.getMessage();
		}
		return document;
	}
//	/**
//	 * permet de creer un nouvel element Evenement
//	 * @param xmldefaultpath2 est le path principal par défaut
//	 */
//	private static Document createElementEvenement(String xmldefaultpath2) {
//		SAXBuilder sxb = new SAXBuilder();
//		Document document=null;
//		try{			
//			document = sxb.build(new File(xmldefaultpath2));
//
//			Element racine = document.getRootElement();
//		
//			Element newVoitureElement=new Element("evenement");
//			newVoitureElement.setAttribute("nomEvenement","");
//			newVoitureElement.setAttribute("nomCircuit","");
//			newVoitureElement.setAttribute("longueurCircuit","");
//
//			enregistre(document, xmlTempPathListVoitures);
//		}
//		catch(Exception e){
//			e.getMessage();
//
//		}
//		return document;
//	}
	private static void cleanTemp(String path,String childrenName) {
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		try{			
			document = sxb.build(new File(path));
			Element racine = document.getRootElement();
			racine.removeChildren(childrenName);
			JDomOperations.enregistre(document, path);
		}
		catch(Exception e){
			e.getMessage();

		}
		
	}
	private static List<Element> getListinTemp(String path) {
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		List<Element> listElement=new ArrayList<Element>();

		try
		{			
			document = sxb.build(new File(path));
			
			Element racine = document.getRootElement();
			listElement=racine.getChildren();
		}
		catch(Exception e){
			e.getMessage();

		}

		return listElement;
	}
	
//	private static List<Element> getListOfVoituresinTemp(String path) {
//		SAXBuilder sxb = new SAXBuilder();
//		Document document;
//		List<Element> listElementVoitures=new ArrayList<Element>();
//
//		try
//		{			
//			document = sxb.build(new File(path));
//			
//			Element racine = document.getRootElement();
//			listElementVoitures=racine.getChildren();
//		}
//		catch(Exception e){
//			e.getMessage();
//
//		}
//
//		return listElementVoitures;
//	}
	
	public static List<String> getEventsNameByDoc(Document pdocument){
		Element racine = pdocument.getRootElement();
		List<Element> listEvenementsElement = racine.getChildren("evenement");
		List<String> listEvenementsString =new ArrayList<String>();
		
		for(Element evenementElement:listEvenementsElement){
			listEvenementsString.add(evenementElement.getAttributeValue("nomEvent"));
		}
//		//On crée un Iterator sur notre liste
//		Iterator i = listEvenementsElement.iterator();
//		while (i.hasNext()){
//			Element evenementElement = (Element)i.next();
//			//On affiche le nom de l'élément courant
//			System.out.println(courant.getChild("nom").getText());
//		}
		return listEvenementsString;
	}
	
//	public static void afficheXml(Document document){
//			try{
//				//On utilise ici un affichage classique avec getPrettyFormat()
//				XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
//				sortie.output(document, System.out);
//			}
//			catch (java.io.IOException e){}
//		}
	public static void main(String[] args) {
//		getEventsNameByXml();
//		getElementByName("event1");
//		leo();
//		removeEventinXmlFilter();
		
//		cleanTemp(xmlTempPathListCourses,"course");
//		cleanTemp(xmlTempPathListVoitures,"voiture");
		
//		EssaiOrCourse course=new EssaiOrCourse();
//		saveCoursesinTempFile(course);
		
		Voiture voiture=new Voiture();
		saveVoituresinTempFile(voiture);
		
		Evenement evenement=new Evenement();
		saveEvenementToXml(evenement);
	}
	public static Element getElementActuel_() {
		return elementActuel_;
	}
	public static void setElementActuel_(Element elementActuel_) {
		JDomOperations.elementActuel_ = elementActuel_;
	}
	public static void removeEventinXmlFilter(){
		 Filter filtre = new Filter()
		   {
		      //On défini les propriétés du filtre à l'aide
		      //de la méthode matches
		      public boolean matches(Object ob)
		      {
//		         //1 ère vérification : on vérifie que les objets
//		         //qui seront filtrés sont bien des Elements
//		         if(!(ob instanceof Element)){return false;}
//
//		         //On crée alors un Element sur lequel on va faire les
//		         //vérifications suivantes.
//		         Element element = (Element)ob;
//
//		         //On crée deux variables qui vont nous permettre de vérifier
//		         //les conditions de nom et de prenom
//		         int verifNom = 0;
//		         int verifPrenom = 0;
//
//		         //2 ème vérification: on vérifie que le nom est bien "CynO"
//		         if(element.getAttribute("nomEvent").getValue().equalsIgnoreCase("event1"))
////		         if(element.getChild("nom").getTextTrim().equals("CynO"))
//		         {
//		            verifNom = 1;
//		         }
//		         //Si nos conditions sont remplies on retourne true, false sinon
//		         if(verifNom == 1)
//		         {
//		            return true;
//		         }
//		         return false;
		    	  return true;
		      }

			@Override
			public Filter and(Filter arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List filter(List arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object filter(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Filter negate() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Filter or(Filter arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Filter refine(Filter arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		   };//Fin du filtre

		   //getContent va utiliser notre filtre pour créer une liste d'étudiants répondant
		   //à nos critères.
		   SAXBuilder sxbuilder = new SAXBuilder();
		   Document document;
			try {
				document = sxbuilder.build(new File(xmlDefaultPath));
				Element racine = document.getRootElement();
				 List resultat = racine.getContent(filtre);
				 System.out.println("size"+resultat.size()+"##");
				   //On affiche enfin l'attribut classe de tous les éléments de notre list
				   Iterator i = resultat.iterator();
				   while(i.hasNext())
				   {
				      Element courant = (Element)i.next();
				      System.out.println(courant.getAttributeValue("nomEvent"));
				   }
				
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		  
	}


	public static void removeEventinXml(String eventNameString) {
		SAXBuilder sxbuilder = new SAXBuilder();
		try {
			Document document=sxbuilder.build(new File(xmlDefaultPath));
			Element racine = document.getRootElement();
			
			
//			List listEvents = racine.getChildren("evenement");
//			Iterator i = listEvents.iterator();
//			while(i.hasNext()){
//				Element elementEvent = (Element)i.next();
//				if(elementEvent.getAttribute("nomEvent").getValue().equalsIgnoreCase(eventNameString)){
//					racine.removeChild("evenement");
//				}
//			}
			
//			if(racine.getChild("evenement").getAttribute("nomEvent").getValue().equalsIgnoreCase(eventNameString)){
//				
//			}
			
			
//			List<Element> children = racine.getChildren();
//			for(Element child : children) {
//				
//				if(child.getAttribute("nomEvent").getValue().equalsIgnoreCase(eventNameString)){
//					System.out.println("poins");
////					child.getParentElement().removeChild("evenement");
//					System.out.println(child.getAttributeValue("nomEvent"));
//				}
//			}
			
//			enregistre(document, xmlDefaultPath);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public static void saveCourseCreerToXml(Evenement event) {
		Element elementEvent=JDomOperations.getElementActuel_();
		Element listCourses=elementEvent.getChild("listcourses");
		
		Iterator itListCoursesInEvent=event.getIteratorCollectionEssaiCourse_();
		while(itListCoursesInEvent.hasNext()){
			EssaiOrCourse course=(EssaiOrCourse) itListCoursesInEvent.next();
			Element elementCourse=new Element("course");
			elementCourse.setAttribute(new Attribute("nomEssaiOrCourse", course.getNomEssaiOrCourse_()));
			elementCourse.setAttribute(new Attribute("typeEssaiOrCourse", course.getTypeEssaiOrCourse_().toString()));
			
			listCourses.addContent(elementCourse);
		}
		
		
		
		
		JDomOperations.enregistre(elementEvent.getDocument(), xmlDefaultPath);
		
	}
	
	/**
	 * sauvegarde une course dans un fichier xml temporaire listcourses.xml
	 * @param pcourse est l'objet course recuperée depuis l'ihm 
	 */
	public static void saveCoursesinTempFile(EssaiOrCourse pcourse) {
		
		SAXBuilder sxb = new SAXBuilder();
		Document document;
		try{			
			document = sxb.build(new File(xmlTempPathListCourses));
			
			Element racine = document.getRootElement();
			
			
//			racine.removeChildren("course");
			
			
			Element newCourseElement=new Element("course");
			newCourseElement.setAttribute("nomEssaiOrCourse",pcourse.getNomEssaiOrCourse_()!=null?pcourse.getNomEssaiOrCourse_():"");
			newCourseElement.setAttribute("typeEssaiOrCourse",pcourse.getTypeEssaiOrCourse_()!=null?pcourse.getTypeEssaiOrCourse_().toString():"");
			
			Element departAutomatique=new Element("departAutomatique");
			Element typeDeFin=new Element("typeDeFin");
			Element heureDebut=new Element("heureDebut");
			Element heureFin=new Element("heureFin");
			
			Element nbToursMax=new Element("nbToursMax");
			Element dureeMaxPilotage=new Element("dureeMaxPilotage");
			
			Element dureeConsecutiveMaxPilotage=new Element("dureeConsecutiveMaxPilotage");
			Element commentaire=new Element("commentaire");
			
			
			departAutomatique.setText(pcourse.isDepartAutomatique_()?"true":"false");
			typeDeFin.setText(pcourse.getTypeDeFin_()!=null?pcourse.getTypeDeFin_().toString():"");
			heureDebut.setText(pcourse.getHeureDebut_()!=null?pcourse.getHeureDebut_().toString():"");
			heureFin.setText(pcourse.getHeureFin_()!=null?pcourse.getHeureFin_().toString():"");
			nbToursMax.setText(pcourse.getNbToursMax_()>=0?String.valueOf(pcourse.getNbToursMax_()):"");
			dureeMaxPilotage.setText(pcourse.getDureeMaxPilotage_()>=0?String.valueOf(pcourse.getDureeMaxPilotage_()):"");
			dureeConsecutiveMaxPilotage.setText(pcourse.getDureeConsecutiveMaxPilotage_()>=0?String.valueOf(pcourse.getDureeConsecutiveMaxPilotage_()):"");
			commentaire.setText(pcourse.getCommentaire_()!=null?pcourse.getCommentaire_():"");
			
			newCourseElement.addContent(departAutomatique);
			newCourseElement.addContent(typeDeFin);
			newCourseElement.addContent(heureDebut);
			newCourseElement.addContent(heureFin);
			newCourseElement.addContent(nbToursMax);
			newCourseElement.addContent(dureeMaxPilotage);
			newCourseElement.addContent(dureeConsecutiveMaxPilotage);
			newCourseElement.addContent(commentaire);
			
			racine.addContent(newCourseElement);
			
			
			enregistre(document, xmlTempPathListCourses);
			
		}catch (Exception e) {
			e.getMessage();
		}

		
	}
	
	/**
	 * sauvegarde une voiture dans un fichier xml temporaire listvoitures.xml
	 * @param pvoiture est l'objet course recuperée depuis l'ihm 
	 */
	public static void saveVoituresinTempFile(Voiture pvoiture) {

		SAXBuilder sxb = new SAXBuilder();
		Document document;
		try{			
			document = sxb.build(new File(xmlTempPathListVoitures));

			Element racine = document.getRootElement();

//			racine.removeChildren("voiture");
			
			
			Element newVoitureElement=new Element("voiture");
			newVoitureElement.setAttribute("numeroVoiture",pvoiture.getNumeroVoiture_()!=null?pvoiture.getNumeroVoiture_():"");
			newVoitureElement.setAttribute("couleur",pvoiture.getCouleur_()!=null?pvoiture.getCouleur_():"");
			
			Element nombreDeTours=new Element("nombreDeTours");
			Element tempsEstimeTour=new Element("tempsEstimeTour");
			Element voitureActive=new Element("voitureActive");
			Element quantiteMoyenneEssenceParTour=new Element("quantiteMoyenneEssenceParTour");
			Element nombreToursEffectues=new Element("nombreToursEffectues");
			Element nombreToursDepuisDernierRelais=new Element("nombreToursDepuisDernierRelais");
			Element numeroRelaisEnCours=new Element("numeroRelaisEnCours");
			Element heurePrevueProchainPassage=new Element("heurePrevueProchainPassage");
			
			newVoitureElement.addContent(nombreDeTours);
			newVoitureElement.addContent(tempsEstimeTour);
			newVoitureElement.addContent(voitureActive);
			newVoitureElement.addContent(quantiteMoyenneEssenceParTour);
			newVoitureElement.addContent(nombreToursEffectues);
			newVoitureElement.addContent(nombreToursDepuisDernierRelais);
			newVoitureElement.addContent(numeroRelaisEnCours);
			newVoitureElement.addContent(heurePrevueProchainPassage);

			racine.addContent(newVoitureElement);

			enregistre(document, xmlTempPathListVoitures);
		}
		catch(Exception e){
			e.getMessage();

		}


	}

}

