/***********************************************************************
 * Module:  CourseCreerIhmImpl.java
 * Author:  29004847
 * Purpose: Defines the Class CourseCreerIhmImpl
 ***********************************************************************/

package chrono_views;

import chrono_controller.*;
import chrono_model.EssaiOrCourse;
import chrono_model.Evenement;
import chrono_model.Voiture;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.DropMode;
import javax.swing.AbstractListModel;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.SystemColor;

public class CourseCreerIhmImpl extends JFrame implements Ihm, ActionListener {
//	private ControllerCourseCreer controller_ = new ControllerCourseCreer();
	private ControllerCourseCreer controller_;
	private JPanel contentPane;
	private JTextField nomCourse;
	private JTextField dureeTotMaxPilote;
	private JTextField dureeConsecMaxPilote;
	private JTextField nbToursMaxPilote;
	private JList list_voitures_of_course;
	private JList list_allvoitures;
	private JTextArea commentaires;
	private JCheckBox chckbxDpartAutomatique;
	private JRadioButton rdbtnCourse;
	private JRadioButton rdbtnEssai;
	private boolean essai_ = true;
	private boolean demarrageAuto_ = false;
	private ButtonGroup group;
	private JLabel lblFormatHhmmss;
	private JLabel lblFormat;
	private JLabel lblDateDpart;
	private JButton btnSauvegarder_;
	private JButton btnImprimer;
	private JLabel lblCommentaires;
	private String hDepart;

	private Evenement event_;
	private final DefaultListModel<String> listModel;
	private JFormattedTextField frmtdtxtfldJj;
	private JFormattedTextField frmtdtxtfldMm;
	private JFormattedTextField frmtdtxtfldYyyy;
	private JLabel lblNombreToursMax;
	private JLabel lblDureConscutiveMax;
	private JLabel lblDureTotaleMax;
	private JLabel lblHeureDeDpart;
	private JLabel lblNomDeLa;
	private JTextField txtHh;
	private JTextField txtMn;
	private JTextField txtSs;

	private DefaultListModel<String> listModelVoiture = new DefaultListModel<String>();
	private List<Voiture> listVoiture_ = new ArrayList<Voiture>();
	private JButton btnAjouterVoiture;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ControllerCourseCreer c = new ControllerCourseCreer();
//					CourseCreerIhmImpl frame = new CourseCreerIhmImpl(null,null);
//					frame.controller_ = c;
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param observable_ 
	 */
	public CourseCreerIhmImpl(EvenementIhmImpl ev, Evenement observable_) {
		controller_= ControllerCourseCreer.getInstance();
		setBackground(SystemColor.inactiveCaption);
		this.event_ = observable_;
		setTitle("Cr\u00E9ation ou modification de course/essai");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 552);
		this.setSize(900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		nomCourse = new JTextField();
		nomCourse.setColumns(10);

		rdbtnEssai = new JRadioButton("essai");
		rdbtnEssai.setBackground(UIManager.getColor("inactiveCaption"));
		rdbtnEssai.setSelected(true);
		rdbtnEssai.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnEssai.setActionCommand("Essai");
		rdbtnEssai.addActionListener(this);

		rdbtnCourse = new JRadioButton("course");
		rdbtnCourse.setActionCommand("Course");
		rdbtnCourse.addActionListener(this);

		rdbtnCourse.setBackground(UIManager.getColor("inactiveCaption"));
		rdbtnCourse.setHorizontalAlignment(SwingConstants.LEFT);

		group = new ButtonGroup();
		rdbtnEssai.addActionListener(this);
		rdbtnCourse.addActionListener(this);

		group.add(rdbtnCourse);
		group.add(rdbtnEssai);

		chckbxDpartAutomatique = new JCheckBox("D�part automatique");
		chckbxDpartAutomatique.setBackground(UIManager.getColor("inactiveCaption"));
		chckbxDpartAutomatique.addActionListener(this);

		Format shortTime = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.FRANCE);

		dureeTotMaxPilote = new JTextField();
		dureeTotMaxPilote.setColumns(10);

		dureeConsecMaxPilote = new JTextField();
		dureeConsecMaxPilote.setColumns(10);

		nbToursMaxPilote = new JFormattedTextField(NumberFormat.getIntegerInstance());
		nbToursMaxPilote.setColumns(10);


		// Mod�le de la liste de voiture
		listModel = new DefaultListModel<String>();
		listModel.addElement("Monte Carlo 2012");
		listModel.addElement("24 heures du Mans 2012");

		list_voitures_of_course = new JList(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_voitures_of_course.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Voiture rattach\u00E9es \u00E0\u00A0 la course", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		list_allvoitures = new JList(listModelVoiture );
		list_allvoitures.setBorder(new TitledBorder(null, "Voiture non rattach\u00E9es \u00E0 la course", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnAjouterVoiture = new JButton("Ajouter voiture");
		btnAjouterVoiture.addActionListener(this);
		btnAjouterVoiture.setBackground(UIManager.getColor("inactiveCaption"));

		JButton btnRetirerVoiture = new JButton("Retirer voiture");
		btnRetirerVoiture.setBackground(UIManager.getColor("inactiveCaption"));

		commentaires = new JTextArea();
		commentaires.setToolTipText("");
		commentaires.setDropMode(DropMode.INSERT);
		commentaires.setFont(new Font("Monospaced", Font.PLAIN, 13));

		JLabel lblTypeCourse = new JLabel("Type course");

		lblFormatHhmmss = new JLabel("(hh-mm-ss)");

		lblFormat = new JLabel("Format");
		lblFormat.setBackground(SystemColor.activeCaption);

		lblDateDpart = new JLabel("Date d�part");

		shortTime = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);

		JLabel lblddmmyyyy = new JLabel("(dd-mm-yyyy)");

		btnSauvegarder_ = new JButton("Sauvegarder");
		btnSauvegarder_.addActionListener(this);

		btnImprimer = new JButton("Imprimer");

		lblCommentaires = new JLabel("Commentaires");

		try {
			Calendar cal = Calendar.getInstance();
			frmtdtxtfldJj = new JFormattedTextField(new MaskFormatter("##"));
			frmtdtxtfldJj.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldJj.setPreferredSize(new Dimension(10, 20));
			frmtdtxtfldJj.setFont(new Font("Tahoma", Font.BOLD, 11));
			String jj = "";
			jj += SimpleDateFormat.DAY_OF_YEAR_FIELD;
			frmtdtxtfldJj.setText(""+cal.get(Calendar.DAY_OF_MONTH));

			frmtdtxtfldMm = new JFormattedTextField(new MaskFormatter("##"));
			frmtdtxtfldMm.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldMm.setFont(new Font("Tahoma", Font.BOLD, 11));
			frmtdtxtfldMm.setText(""+(cal.get(Calendar.MONTH)+1));

			frmtdtxtfldYyyy = new JFormattedTextField(new MaskFormatter("####"));
			frmtdtxtfldYyyy.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldYyyy.setFont(new Font("Tahoma", Font.BOLD, 11));
			frmtdtxtfldYyyy.setText(""+cal.get(Calendar.YEAR));
		} catch (java.text.ParseException e) {

			e.printStackTrace();
		}

			Calendar cal = Calendar.getInstance();

			int date_ =  SimpleDateFormat.DAY_OF_YEAR_FIELD;
			System.out.println(date_+"\t"+cal.get(Calendar.MONTH));
		
		lblNombreToursMax = new JLabel("Nombre tours max pilote");
		
		lblDureConscutiveMax = new JLabel("Dur\u00E9e cons\u00E9cutive max pilote");
		
		lblDureTotaleMax = new JLabel("Dur\u00E9e totale max pilote");
		
		lblHeureDeDpart = new JLabel("Heure de d\u00E9part");
		
		lblNomDeLa = new JLabel("Nom de la course");
		
		txtHh = new JTextField();
		txtHh.setHorizontalAlignment(SwingConstants.CENTER);
		txtHh.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtHh.setText("Hh");
		txtHh.setColumns(10);
		
		txtMn = new JTextField();
		txtMn.setHorizontalAlignment(SwingConstants.CENTER);
		txtMn.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtMn.setText("Mn");
		txtMn.setColumns(10);
		
		txtSs = new JTextField();
		txtSs.setHorizontalAlignment(SwingConstants.CENTER);
		txtSs.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtSs.setText("Ss");
		txtSs.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTypeCourse, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNomDeLa))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(18)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxDpartAutomatique)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(rdbtnEssai)
													.addGap(36)
													.addComponent(rdbtnCourse, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(nomCourse, GroupLayout.PREFERRED_SIZE, 579, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(list_voitures_of_course, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNombreToursMax)
										.addComponent(lblDureConscutiveMax)
										.addComponent(lblDureTotaleMax))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnAjouterVoiture, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnRetirerVoiture, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(dureeTotMaxPilote, Alignment.LEADING)
											.addComponent(dureeConsecMaxPilote, Alignment.LEADING)
											.addComponent(nbToursMaxPilote, Alignment.LEADING)))
									.addGap(8)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblDateDpart)
													.addGap(31)
													.addComponent(frmtdtxtfldJj, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(frmtdtxtfldMm, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(frmtdtxtfldYyyy))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblHeureDeDpart)
													.addGap(10)
													.addComponent(txtHh, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(txtMn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(txtSs, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.UNRELATED, 67, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblddmmyyyy, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblFormatHhmmss, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblFormat, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
										.addComponent(list_allvoitures, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(257)
							.addComponent(btnSauvegarder_)
							.addGap(177)
							.addComponent(btnImprimer))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(commentaires, GroupLayout.PREFERRED_SIZE, 848, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblCommentaires))))
					.addGap(15))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(nomCourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(14))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNomDeLa)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTypeCourse)
								.addComponent(rdbtnCourse)
								.addComponent(rdbtnEssai))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxDpartAutomatique)
							.addGap(17))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFormat)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(nbToursMaxPilote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombreToursMax))
							.addGap(11))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFormatHhmmss)
								.addComponent(txtSs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHeureDeDpart))
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dureeConsecMaxPilote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(8)
							.addComponent(dureeTotMaxPilote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
							.addComponent(btnAjouterVoiture)
							.addGap(18)
							.addComponent(btnRetirerVoiture)
							.addGap(27))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(lblDureConscutiveMax)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblDureTotaleMax))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(7)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDateDpart)
										.addComponent(frmtdtxtfldJj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldMm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(frmtdtxtfldYyyy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblddmmyyyy))))
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(list_voitures_of_course, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addComponent(list_allvoitures, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCommentaires)
					.addGap(1)
					.addComponent(commentaires, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnImprimer)
						.addComponent(btnSauvegarder_))
					.addGap(14))
		);
		contentPane.setLayout(gl_contentPane);
		loadListAllVoitures(observable_);
	}
	/**
	 * pour compléter liste toutes les voitures
	 * @param pevenement
	 */
	public void loadListAllVoitures(Evenement pevenement){
		if(pevenement!=null){
//			if(pevenement.sizeOfListeCourse()>0){
//				for(Iterator<EssaiOrCourse> itercourse = observable_.getIteratorCollectionEssaiCourse_();itercourse.hasNext();){
//					listModelCourse.addElement(itercourse.next().getNomEssaiOrCourse_());
//				}
//			}
			if(pevenement.sizeOfListeVoitures()>0){
				for(Iterator<Voiture> itervoit = pevenement.getIteratorCollectionVoitures_();itervoit.hasNext();){
					Voiture voiture=itervoit.next();
					listModelVoiture.addElement(voiture.getNumeroVoiture_()+ " | "+voiture.getCouleur_());
				}
			}
		}
	}

	/**
	 * @return the nomCourse
	 */
	public JTextField getNomCourse() {
		return nomCourse;
	}

	/**
	 * @param nomCourse the nomCourse to set
	 */
	public void setNomCourse(JTextField nomCourse) {
		this.nomCourse = nomCourse;
	}

	/**
	 * @return the dureeTotMaxPilote
	 */
	public JTextField getDureeTotMaxPilote() {
		return dureeTotMaxPilote;
	}

	/**
	 * @param dureeTotMaxPilote the dureeTotMaxPilote to set
	 */
	public void setDureeTotMaxPilote(JTextField dureeTotMaxPilote) {
		this.dureeTotMaxPilote = dureeTotMaxPilote;
	}

	/**
	 * @return the dureeConsecMaxPilote
	 */
	public JTextField getDureeConsecMaxPilote() {
		return dureeConsecMaxPilote;
	}

	/**
	 * @param dureeConsecMaxPilote the dureeConsecMaxPilote to set
	 */
	public void setDureeConsecMaxPilote(JTextField dureeConsecMaxPilote) {
		this.dureeConsecMaxPilote = dureeConsecMaxPilote;
	}

	/**
	 * @return the nbToursMaxPilote
	 */
	public JTextField getNbToursMaxPilote() {
		return nbToursMaxPilote;
	}

	/**
	 * @param nbToursMaxPilote the nbToursMaxPilote to set
	 */
	public void setNbToursMaxPilote(JTextField nbToursMaxPilote) {
		this.nbToursMaxPilote = nbToursMaxPilote;
	}

	/**
	 * @return the list_voitures_of_course
	 */
	public JList getList_voitures_of_course() {
		return list_voitures_of_course;
	}

	/**
	 * @param list_voitures_of_course the list_voitures_of_course to set
	 */
	public void setList_voitures_of_course(JList list_voitures_of_course) {
		this.list_voitures_of_course = list_voitures_of_course;
	}

	/**
	 * @return the commentaires
	 */
	public JTextArea getCommentaires() {
		return commentaires;
	}

	/**
	 * @param commentaires the commentaires to set
	 */
	public void setCommentaires(JTextArea commentaires) {
		this.commentaires = commentaires;
	}

	/**
	 * @return the chckbxDpartAutomatique
	 */
	public JCheckBox getChckbxDpartAutomatique() {
		return chckbxDpartAutomatique;
	}

	/**
	 * @param chckbxDpartAutomatique the chckbxDpartAutomatique to set
	 */
	public void setChckbxDpartAutomatique(JCheckBox chckbxDpartAutomatique) {
		this.chckbxDpartAutomatique = chckbxDpartAutomatique;
	}

	/**
	 * @return the rdbtnCourse
	 */
	public JRadioButton getRdbtnCourse() {
		return rdbtnCourse;
	}

	/**
	 * @param rdbtnCourse the rdbtnCourse to set
	 */
	public void setRdbtnCourse(JRadioButton rdbtnCourse) {
		this.rdbtnCourse = rdbtnCourse;
	}

	/**
	 * @return the rdbtnEssai
	 */
	public JRadioButton getRdbtnEssai() {
		return rdbtnEssai;
	}

	/**
	 * @param rdbtnEssai the rdbtnEssai to set
	 */
	public void setRdbtnEssai(JRadioButton rdbtnEssai) {
		this.rdbtnEssai = rdbtnEssai;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSauvegarder_){
			this.controller_.control(this, this.event_);
			this.dispose();
		}
		if(e.getSource() == rdbtnCourse){
			this.setEssai_(false);
		}
		if(e.getSource() == rdbtnEssai) 
			this.setEssai_(true);
		if(e.getSource() == chckbxDpartAutomatique){
			this.setDemarrageAuto_(!this.isDemarrageAuto_());
		}
		if(e.getSource() == btnAjouterVoiture){
			System.out.println("voiture ajoutée");
			int i = this.list_allvoitures.getSelectedIndex();
			Voiture v = this.event_.getVoiture(i);
			this.event_.supprimerVoiture(i, null);
			this.listVoiture_.add(v);
		}

	}

	/**
	 * @return the essai_
	 */
	public boolean isEssai_() {
		return essai_;
	}

	/**
	 * @param essai_ the essai_ to set
	 */
	public void setEssai_(boolean essai_) {
		this.essai_ = essai_;
	}

	/**
	 * @return the demarrageAuto_
	 */
	public boolean isDemarrageAuto_() {
		return demarrageAuto_;
	}

	/**
	 * @param demarrageAuto_ the demarrageAuto_ to set
	 */
	public void setDemarrageAuto_(boolean demarrageAuto_) {
		this.demarrageAuto_ = demarrageAuto_;
	}

	/**
	 * @return the controller_
	 */
	public ControllerCourseCreer getController_() {
		return controller_;
	}

	/**
	 * @param controller_ the controller_ to set
	 */
	public void setController_(ControllerCourseCreer controller_) {
		this.controller_ = controller_;
	}

	/**
	 * @return the hDepart
	 */
	public String gethDepart() {
		return hDepart;
	}

	/**
	 * @param hDepart the hDepart to set
	 */
	public void sethDepart(String hDepart) {
		this.hDepart = hDepart;
	}

	public void display() {
		this.setVisible(true);
		
	}
	public List<Voiture> getListVoiture_() {
		return listVoiture_;
	}
	public void setListVoiture_(List<Voiture> listVoiture_) {
		this.listVoiture_ = listVoiture_;
	}

	
}