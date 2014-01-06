/***********************************************************************
 * Module:  Chrono.java
 * Author:  qmpq
 * Purpose: Defines the Class Chrono
 ***********************************************************************/

package chrono_model;

import chrono_command.*;
import java.util.*;

public class Chrono {
   private String nom;
   
   public java.util.List<Evenement> listeEvenements;
//   public CreerEvenement creerEvenement;
//   public ChargerEvenement chargerEvenement;
//   public SupprimerEvenement supprimerEvenement;
//   public ModifierEvenement modifierEvenement;
   
   public void creerEvenement() {
      // TODO: implement
   }
   
   public void chargerEvenement() {
      // TODO: implement
   }
   
   public void supprimerEvenement() {
      // TODO: implement
   }
   
   public void modifierEvenement() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public java.util.List<Evenement> getListeEvenements() {
      if (listeEvenements == null)
         listeEvenements = new java.util.ArrayList<Evenement>();
      return listeEvenements;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorListeEvenements() {
      if (listeEvenements == null)
         listeEvenements = new java.util.ArrayList<Evenement>();
      return listeEvenements.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newListeEvenements */
   public void setListeEvenements(java.util.List<Evenement> newListeEvenements) {
      removeAllListeEvenements();
      for (java.util.Iterator iter = newListeEvenements.iterator(); iter.hasNext();)
         addListeEvenements((Evenement)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newEvenement */
   public void addListeEvenements(Evenement newEvenement) {
      if (newEvenement == null)
         return;
      if (this.listeEvenements == null)
         this.listeEvenements = new java.util.ArrayList<Evenement>();
      if (!this.listeEvenements.contains(newEvenement))
         this.listeEvenements.add(newEvenement);
   }
   
   /** @pdGenerated default remove
     * @param oldEvenement */
   public void removeListeEvenements(Evenement oldEvenement) {
      if (oldEvenement == null)
         return;
      if (this.listeEvenements != null)
         if (this.listeEvenements.contains(oldEvenement))
            this.listeEvenements.remove(oldEvenement);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllListeEvenements() {
      if (listeEvenements != null)
         listeEvenements.clear();
   }

}