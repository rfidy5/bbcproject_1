package chrono_controller;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPath;

public abstract class AbstractController {
	
	
	public abstract Document constructxml(Object object);
	public abstract Object getObjectByXml(String filepath);
//	public abstract void display();
	
	public void affiche(Document document){
		try{
			//On utilise ici un affichage classique avec getPrettyFormat()
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		}
		catch (java.io.IOException e){}
	}
	
}
