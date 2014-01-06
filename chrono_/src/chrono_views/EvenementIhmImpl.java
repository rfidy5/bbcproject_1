/***********************************************************************
 * Module:  EvenementIhmImp.java
 * Author:  29004847
 * Purpose: Defines the Class EvenementIhmImp
 ***********************************************************************/

package chrono_views;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Cursor;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.SystemColor;

import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import chrono_controller.ControllerCourseCreer;
import chrono_controller.ControllerEvent;
import chrono_controller.ControllerVoiture;
import chrono_model.EssaiOrCourse;
import chrono_model.Evenement;
import chrono_model.Voiture;

import java.awt.Color;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class EvenementIhmImpl extends JFrame implements ActionListener, Observer {

	private JMenuBar menuBar_;
	private JMenu fichier_;
	private JMenu aPropos_;
	private JPanel conteneur_ = new JPanel();
	private JMenuItem newEvent_;
	private JMenuItem aide_;
	private JMenuItem quitter_;
	private JTextField jtxtNomEvenement_;
	private JTextField jTxtNomCircuit_;
	private JFormattedTextField jtxtLongueurCircuit_;
	private JLabel lblEnMtre;
	private JList listCourses_;
	private JList listVoitures_;
	private JButton btnModifierLaCourse_;
	private JButton btnSupprimerLaCourse;
	private JButton btnCrerUneVoiture_;
	private JButton btnModifierLaVoiture_;
	private JButton btnSupprimerLaVoiture_;
	private JButton btnSauvegarder_;
	private JButton btnImprimer_;
	private JTable table;
	private JFileChooser fc;
	private JButton btnCrerUneCourse_;

	private DefaultListModel<String> listModelCourse = new DefaultListModel<String>();

	private ControllerEvent controller_;
	private ControllerCourseCreer controllerCourseCreer_;
	private ControllerVoiture controllerVoiture_;
	private float longueur_;
	private Evenement event_;
	private DefaultListModel<String> listModelVoiture = new DefaultListModel<String>();
	private JButton btnChargerCourse;

	public EvenementIhmImpl(Evenement event) throws NumberFormatException{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		controller_ = ControllerEvent.getInstance();
		controllerCourseCreer_=ControllerCourseCreer.getInstance();
		controllerVoiture_=ControllerVoiture.getInstance();
		
		event_ = event;
		setBackground(SystemColor.activeCaption);
		this.setResizable(false);
		this.setTitle("Chronom�tre");
		this.setSize(900, 600);

		menuBar_ = new JMenuBar();
		menuBar_.setToolTipText("");
		menuBar_.setFont(new Font("Times New Roman", Font.BOLD, 12));
		setJMenuBar(menuBar_);

		fichier_ = new JMenu("Fichier");
		fichier_.setHorizontalTextPosition(SwingConstants.LEFT);
		fichier_.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuBar_.add(fichier_);


		newEvent_ = new JMenuItem("Nouvel l'évènement");
		newEvent_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		fichier_.add(newEvent_);

		fichier_.addSeparator();

		quitter_ = new JMenuItem("Quitter");
		quitter_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		fichier_.add(quitter_);

		aPropos_ = new JMenu("A propos");
		menuBar_.add(aPropos_);

		aide_ = new JMenuItem("? Aide");
		aide_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		aPropos_.add(aide_);
		conteneur_.setBackground(SystemColor.activeCaption);

		this.setContentPane(conteneur_);

		JLabel lblEditEvent = new JLabel("EDIT EVENT");

		JLabel lblNomDeLvnement = new JLabel("Nom de l'évènement");

		jtxtNomEvenement_ = new JTextField();
		jtxtNomEvenement_.setForeground(Color.BLACK);
		jtxtNomEvenement_.setToolTipText("");
		jtxtNomEvenement_.setText("Nom de l'évènement");
		jtxtNomEvenement_.setColumns(10);
		jtxtNomEvenement_.addActionListener(this);

		JLabel lblNomDuCircuit = new JLabel("Nom du circuit");

		jTxtNomCircuit_ = new JTextField();
		jTxtNomCircuit_.setForeground(Color.BLACK);
		jTxtNomCircuit_.setText("Nom du circuit");
		jTxtNomCircuit_.setColumns(10);

		JLabel lblLongueurCircuit_ = new JLabel("Longueur du circuit");

		Format integer = NumberFormat.getIntegerInstance(Locale.FRENCH);
		jtxtLongueurCircuit_ = new JFormattedTextField(integer);
		jtxtLongueurCircuit_.setValue(0L);
		jtxtLongueurCircuit_.setColumns(20);
		jtxtLongueurCircuit_.setForeground(Color.BLACK);

		lblEnMtre = new JLabel("en mètre");

		listCourses_ = new JList(listModelCourse);
		listCourses_.setBorder(new TitledBorder(null, "Liste des courses", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listCourses_.setBackground(Color.WHITE);
		listCourses_.setSelectionBackground(SystemColor.activeCaption);

		listVoitures_ = new JList(listModelVoiture );
		listVoitures_.setBorder(new TitledBorder(null, "Liste des voitures", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listVoitures_.setBackground(Color.WHITE);
		listVoitures_.setSelectionBackground(SystemColor.activeCaption);

		btnCrerUneCourse_ = new JButton("Créer une course");
		btnCrerUneCourse_.addActionListener(this);

		btnModifierLaCourse_ = new JButton("Modifier la course");

		btnSupprimerLaCourse = new JButton("Supprimer la course");
		btnSupprimerLaCourse.addActionListener(this);

		btnCrerUneVoiture_ = new JButton("Créer une voiture");
		btnCrerUneVoiture_.addActionListener(this);

		btnModifierLaVoiture_ = new JButton("Modifier la voiture");

		btnSupprimerLaVoiture_ = new JButton("Supprimer la voiture");
		btnSupprimerLaVoiture_.addActionListener(this);

		btnSauvegarder_ = new JButton("Sauvegarder");
		btnSauvegarder_.addActionListener(this);

		btnImprimer_ = new JButton("Imprimer");

		table = new JTable();
		
		btnChargerCourse = new JButton("Charger Course");
		btnChargerCourse.addActionListener(this);
		GroupLayout gl_conteneur_ = new GroupLayout(conteneur_);
		gl_conteneur_.setHorizontalGroup(
			gl_conteneur_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_conteneur_.createSequentialGroup()
					.addGap(419)
					.addComponent(lblEditEvent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(418))
				.addGroup(gl_conteneur_.createSequentialGroup()
					.addGroup(gl_conteneur_.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_conteneur_.createSequentialGroup()
							.addGap(57)
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnChargerCourse, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
								.addComponent(listCourses_, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_conteneur_.createSequentialGroup()
									.addComponent(btnCrerUneCourse_, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnModifierLaCourse_)
									.addGap(18)
									.addComponent(btnSupprimerLaCourse, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSauvegarder_))
							.addGap(156)
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.TRAILING)
								.addComponent(listVoitures_, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_conteneur_.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_conteneur_.createParallelGroup(Alignment.LEADING)
										.addComponent(btnImprimer_, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_conteneur_.createSequentialGroup()
											.addComponent(btnSupprimerLaVoiture_, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnModifierLaVoiture_))
										.addComponent(btnCrerUneVoiture_, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))))
						.addGroup(gl_conteneur_.createSequentialGroup()
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNomDuCircuit, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNomDeLvnement))
							.addGap(18)
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jtxtNomEvenement_, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_conteneur_.createSequentialGroup()
									.addGroup(gl_conteneur_.createParallelGroup(Alignment.TRAILING)
										.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
										.addComponent(jTxtNomCircuit_, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblLongueurCircuit_, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(jtxtLongueurCircuit_, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblEnMtre)))))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_conteneur_.setVerticalGroup(
			gl_conteneur_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_conteneur_.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEditEvent)
					.addGap(11)
					.addGroup(gl_conteneur_.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtxtNomEvenement_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomDeLvnement, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_conteneur_.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTxtNomCircuit_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtxtLongueurCircuit_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLongueurCircuit_)
						.addComponent(lblEnMtre)
						.addComponent(lblNomDuCircuit))
					.addGroup(gl_conteneur_.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_conteneur_.createSequentialGroup()
							.addGap(50)
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.BASELINE)
								.addComponent(listCourses_, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
								.addComponent(listVoitures_, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCrerUneCourse_)
								.addComponent(btnCrerUneVoiture_)
								.addComponent(btnSupprimerLaCourse)
								.addComponent(btnModifierLaCourse_))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnModifierLaVoiture_)
								.addComponent(btnChargerCourse)
								.addComponent(btnSupprimerLaVoiture_))
							.addGap(40)
							.addGroup(gl_conteneur_.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSauvegarder_)
								.addComponent(btnImprimer_)))
						.addGroup(gl_conteneur_.createSequentialGroup()
							.addGap(143)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		conteneur_.setLayout(gl_conteneur_);
		completerChamps();
	}

	/**
	 * @return the jTxtNomCircuit_
	 */
	public JTextField getjTxtNomCircuit_() {
		return jTxtNomCircuit_;
	}

	/**
	 * @param jTxtNomCircuit_ the jTxtNomCircuit_ to set
	 */
	public void setjTxtNomCircuit_(JTextField jTxtNomCircuit_) {
		this.jTxtNomCircuit_ = jTxtNomCircuit_;
	}

	/**
	 * @return the jtxtLongueurCircuit_
	 */
	public JFormattedTextField getJtxtLongueurCircuit_() {
		return jtxtLongueurCircuit_;
	}

	/**
	 * @param jtxtLongueurCircuit_ the jtxtLongueurCircuit_ to set
	 */
	public void setJtxtLongueurCircuit_(JFormattedTextField jtxtLongueurCircuit_) {
		this.jtxtLongueurCircuit_ = jtxtLongueurCircuit_;
	}

	/**
	 * @return the listCourses_
	 */
	public JList getListCourses_() {
		return listCourses_;
	}

	/**
	 * @param listCourses_ the listCourses_ to set
	 */
	public void setListCourses_(JList listCourses_) {
		this.listCourses_ = listCourses_;
	}

	/**
	 * @return the listVoitures_
	 */
	public JList getListVoitures_() {
		return listVoitures_;
	}

	/**
	 * @param listVoitures_ the listVoitures_ to set
	 */
	public void setListVoitures_(JList listVoitures_) {
		this.listVoitures_ = listVoitures_;
	}
	/**
	 * pour compléter les champs
	 */
	public void completerChamps(){
		if(event_!=null){
			jtxtNomEvenement_.setText(event_.getNomEvenement_());
			jTxtNomCircuit_.setText(event_.getNomCircuit_());
			jtxtLongueurCircuit_.setText(Float.toString(event_.getLongueurCircuit_()));

			if(event_.sizeOfListeCourse()>0){
				for(Iterator<EssaiOrCourse> itercourse = event_.getIteratorCollectionEssaiCourse_();itercourse.hasNext();){
					listModelCourse.addElement(itercourse.next().getNomEssaiOrCourse_());
				}
			}
			if(event_.sizeOfListeVoitures()>0){
				for(Iterator<Voiture> itervoit = event_.getIteratorCollectionVoitures_();itervoit.hasNext();){
					Voiture voiture=itervoit.next();
					listModelVoiture.addElement(voiture.getNumeroVoiture_()+ " | "+voiture.getCouleur_());
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCrerUneCourse_){
		
			controllerCourseCreer_.displayCourseCreer(this.event_);

		}
		if(e.getSource() == this.btnSauvegarder_){
			String mesString = "";
			if(this.jtxtNomEvenement_.getText().isEmpty())
				mesString += "+ Saisir un nom d'évènement\n";
			if(listModelCourse.isEmpty())
				mesString += "+ Créer au moins une course ou un essai\n";

			if(listModelVoiture.isEmpty())
				mesString += "+ Créer au moins une voiture\n";
			if(!mesString.isEmpty())
				JOptionPane.showMessageDialog(this, mesString , "Erreur", JOptionPane.ERROR_MESSAGE);

			else{
				this.controller_.controlEvenement(this, this.event_);				
			}
		}
		if(e.getSource() == this.btnCrerUneVoiture_){
			System.out.println("Voiture");
				
			controllerVoiture_.displayVoiture(this.event_);
		}
		if(e.getSource() == this.btnChargerCourse){
			int i = this.listCourses_.getSelectedIndex();
			
			EssaiOrCourse course = this.event_.getEssaiOrCourse_(i);
			
			CourseEnCoursIhmImpl ihmEnCours = new CourseEnCoursIhmImpl(course, i);
			ihmEnCours.setVisible(true);
			
		}
		if(e.getSource() == this.btnSupprimerLaCourse){
			int i = this.listCourses_.getSelectedIndex();
			System.out.println("int "+i);
			this.event_.supprimerCourse(i, null);
		}
		if(e.getSource() == this.btnSupprimerLaVoiture_){
			int i = this.listVoitures_.getSelectedIndex();
			this.event_.supprimerVoiture(i, null);
		}
	}

	/**
	 * @return the jtxtNomEvenement_
	 */
	public JTextField getJtxtNomEvenement_() {
		return jtxtNomEvenement_;
	}

	/**
	 * @param jtxtNomEvenement_ the jtxtNomEvenement_ to set
	 */
	public void setJtxtNomEvenement_(JTextField jtxtNomEvenement_) {
		this.jtxtNomEvenement_ = jtxtNomEvenement_;
	}

	/**
	 * @return the longueur_
	 */
	public float getLongueur_() {
		longueur_=Float.parseFloat(jtxtLongueurCircuit_.getText());
		return longueur_;
	}

	/**
	 * @param longueur_ the longueur_ to set
	 */
	public void setLongueur_(Long longueur_) {
		this.longueur_ = longueur_;
	}

	@Override
	public void update(Observable o, Object arg) {
		List<EssaiOrCourse> listeEssai = this.event_.getCollectionEssaiCourse_();
		listModelCourse.clear();

		for(int i = 0; i < listeEssai.size(); i++){
			listModelCourse.addElement(listeEssai.get(i).getNomEssaiOrCourse_());
		}

		List<Voiture> listeVoiture = this.event_.getCollectionVoiture_();
		listModelVoiture.clear();
		for(int i = 0; i < listeVoiture.size(); i++){
			listModelVoiture.addElement(listeVoiture.get(i).getNumeroVoiture_());
		}
	}
	public void display(){
		this.setVisible(true);
	}
}