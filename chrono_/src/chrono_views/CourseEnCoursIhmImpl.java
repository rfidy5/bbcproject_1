package chrono_views;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import chrono_controller.Controller;
import chrono_model.EssaiOrCourse;
import chrono_model.Pilote;
import chrono_model.TypeTop;
import chrono_model.Voiture;
import java.awt.Color;

public class CourseEnCoursIhmImpl extends JFrame implements ActionListener{


	private String[] comboData = {"race", "in", "out"};
	private Controller controller_;

	private JPanel conteneur_;

	private DefaultTableModel colHdrs ;

	private DefaultListModel listModel;

	private Component tableauTops_;
	private JButton btnTop;

	private TableModel tableTops_;
	private JLabel lblDateDeDpart_1;
	private JLabel lblDateDuJour_1;
	private JLabel lblHeureActuelle;
	private JLabel lblHeureDeDpart;
	private JLabel lblTempscoul_1;
	private JLabel lblTempsRestant_1;
	private JButton btnDemarrer;
	private JButton btnArreter = new JButton("Arreter");

	private DefaultTableModel  model = new DefaultTableModel();
	private final Object [][] data = {};
	private JTable table;
	private JTabbedPane tabbedPane;
	private JTable table_1;

	private int timeCount = 0;

	protected Timer timer;

	private int speed = 1;

	private int pause = 0;

	private EssaiOrCourse course_;
	private JScrollPane scrollPane;
	private JTabbedPane tabbedPaneVoitures;
	private JPanel panelInfoVoiture;
	private JComboBox comboBoxListePilotes;

	private Container panel_2;

	private JProgressBar progressBar;
	private JPanel pInfosCourse_;
	private JLabel lblTempsDeConduite_2;
	private JLabel lblTempsDeCon;
	private JLabel lblNombreDeTours_1;
	private JLabel lblNbToursConsecutifs;
	private JLabel lblPiloteActuel_1;
	private JLabel lblEtat_1;
	private JLabel lblTempsTourPrcdent;
	private JLabel lblToursRestants_1;
	private JLabel lblToursEffecuts;
	private JLabel lblToursParRelais_1;

	private GroupLayout gl_pInfosCourse_;

	private GroupLayout gl_panelInfoVoiture;

	private DefaultComboBoxModel<String> liste;

	private boolean estDemarrer = false;

	private int relais_ = 1;

	private int nbTours_ = 0;
	private JMenuItem mntmArreterCourse;
	private int nbToursRestant ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EssaiOrCourse essaiOrCourse_ = new EssaiOrCourse();
					essaiOrCourse_.setNomEssaiOrCourse_("Test");
					// initialisation voiture1
					Voiture voiture1 = new Voiture();
					voiture1.setNumeroVoiture_("voiture1");
					voiture1.setCouleur_("bleu");
					Pilote pilote = new Pilote("Hamilton");
					voiture1.addCollectionPilotes_Tops(pilote);
					pilote = new Pilote("Fernando");
					voiture1.addCollectionPilotes_Tops(pilote);

					essaiOrCourse_.ajouterVoiture(voiture1);


					// initialisation voiture2
					Voiture voiture2 = new Voiture();
					voiture2.setNumeroVoiture_("voiture2");
					voiture2.setCouleur_("rouge");
					pilote = new Pilote("coco");
					voiture2.addCollectionPilotes_Tops(pilote);
					pilote = new Pilote("toto");
					voiture2.addCollectionPilotes_Tops(pilote);

					essaiOrCourse_.ajouterVoiture(voiture2);

					// initialisation voiture3
					Voiture voiture3 = new Voiture();
					voiture3.setNumeroVoiture_("voiture3");
					voiture3.setCouleur_("vert");
					pilote = new Pilote("frank");
					voiture3.addCollectionPilotes_Tops(pilote);
					pilote = new Pilote("ribery");
					voiture3.addCollectionPilotes_Tops(pilote);

					essaiOrCourse_.ajouterVoiture(voiture3);
					//                  
					//                  //initialisation d'un top quelconque;
					//                  top=new Top("R","","",2.0,"");
					////                    top.setHeureDePassage_(new Date());
					////                    top.setEtatTop_("R");


					CourseEnCoursIhmImpl frame = new CourseEnCoursIhmImpl(essaiOrCourse_,0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param i permet de connaitre la position de la course
	 */
	public CourseEnCoursIhmImpl(EssaiOrCourse course, int i) {
		this.course_ = course;
		this.nbToursRestant = this.course_.getNbToursMax_();
		//if(this.course_ == null) this.course_ = new EssaiOrCourse();

		List<Voiture> listeVoitures_ = this.course_.getCollectionVoiture_();
		System.out.println("Size  "+listeVoitures_.size());

		timer = new Timer(speed, this);
		timer.setInitialDelay(pause);

		this.setSize(985, 654);
		setResizable(false);
		setTitle("Tableau de bord de la course en cours");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 654);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		mntmArreterCourse = new JMenuItem("Arreter Course");
		mntmArreterCourse.addActionListener(this);
		mnFichier.add(mntmArreterCourse);

		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mnFichier.add(mntmNewMenuItem);

		JMenu mnEdition = new JMenu("Edition");
		menuBar.add(mnEdition);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmHelp = new JMenuItem("Help !");
		mnAbout.add(mntmHelp);
		conteneur_ = new JPanel();
		conteneur_.setBackground(SystemColor.inactiveCaption);
		//conteneur_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(conteneur_);

		pInfosCourse_ = new JPanel();
		pInfosCourse_.setBorder(new TitledBorder(null, "Infos course", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPaneVoitures = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneVoitures.setBorder(new TitledBorder(null, "Information voiture", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		// if(!listeVoitures_.isEmpty()){
		//for(int i = 0; i < listeVoitures_.size(); i++){
		Voiture v = listeVoitures_.get(0);
		panelInfoVoiture = new JPanel();
		tabbedPaneVoitures.addTab("Voiture "+(0+1), null, panelInfoVoiture, null);
		panelInfoVoiture.setBorder(new TitledBorder(null, "Infos voiture", TitledBorder.LEADING, TitledBorder.TOP, null, null));


		JLabel lblProgressionTour = new JLabel("Progression / tour");

		JLabel lblToursParRelais = new JLabel("Tours par relais");

		JLabel lblNewLabel = new JLabel("Tours effectués");

		JLabel lblToursRestants = new JLabel("Tours restants ");

		JLabel lblTempsTourPrecdent = new JLabel("Temps tour precédent");

		JLabel lblEtat = new JLabel("Etat");

		progressBar = new JProgressBar();

		panel_2 = new JPanel();
		((JComponent) panel_2).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Details pilotes", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblListeDesPilotes = new JLabel("Liste des pilotes");

		// String titre = "comboPilote"+i;

		comboBoxListePilotes = new JComboBox();
		String[] listePilote_ = listeVoitures_.get(0).listePilote_();
		liste = new DefaultComboBoxModel<String>(listePilote_);
		comboBoxListePilotes.setModel(liste);
		comboBoxListePilotes.addActionListener(this);

		tabbedPaneVoitures.add(comboBoxListePilotes);

		JLabel lblPiloteActuel = new JLabel("Pilote actuel");

		JLabel lblNombreDeTous = new JLabel("Nombre de tours consecutifs");

		JLabel lblNombreDeTours = new JLabel("Nombre de tours total");

		JLabel lblTempsDeConduite = new JLabel("Temps de conduite consécutif");

		JLabel lblTempsDeConduite_1 = new JLabel("Temps de conduite total");

		lblPiloteActuel_1 = new JLabel();
		lblPiloteActuel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPiloteActuel_1.setForeground(new Color(255, 0, 0));
		lblPiloteActuel_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblNbToursConsecutifs = new JLabel("nb tours consecutifs");
		lblNbToursConsecutifs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNbToursConsecutifs.setForeground(SystemColor.textHighlight);
		lblNbToursConsecutifs.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblNombreDeTours_1 = new JLabel("Nombre de tours total");
		lblNombreDeTours_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDeTours_1.setForeground(SystemColor.textHighlight);
		lblNombreDeTours_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblTempsDeCon = new JLabel("Temps de con consecutif");
		lblTempsDeCon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTempsDeCon.setForeground(SystemColor.textHighlight);
		lblTempsDeCon.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblTempsDeConduite_2 = new JLabel("Temps de conduite total");
		lblTempsDeConduite_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTempsDeConduite_2.setForeground(SystemColor.textHighlight);
		lblTempsDeConduite_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(lblListeDesPilotes)
										.addGap(86)
										.addComponent(comboBoxListePilotes, 0, 171, Short.MAX_VALUE))
										.addGroup(gl_panel_2.createSequentialGroup()
												.addComponent(lblTempsDeConduite_1)
												.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
												.addComponent(lblTempsDeConduite_2))
												.addGroup(gl_panel_2.createSequentialGroup()
														.addComponent(lblTempsDeConduite)
														.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
														.addComponent(lblTempsDeCon))
														.addGroup(gl_panel_2.createSequentialGroup()
																.addComponent(lblNombreDeTours)
																.addPreferredGap(ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
																.addComponent(lblNombreDeTours_1))
																.addGroup(gl_panel_2.createSequentialGroup()
																		.addComponent(lblNombreDeTous)
																		.addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
																		.addComponent(lblNbToursConsecutifs))
																		.addGroup(gl_panel_2.createSequentialGroup()
																				.addComponent(lblPiloteActuel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
																				.addComponent(lblPiloteActuel_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
																				.addContainerGap())
				);
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxListePilotes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblListeDesPilotes))
								.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPiloteActuel)
										.addComponent(lblPiloteActuel_1))
										.addGap(18)
										.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNombreDeTous)
												.addComponent(lblNbToursConsecutifs))
												.addGap(18)
												.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblNombreDeTours)
														.addComponent(lblNombreDeTours_1))
														.addGap(18)
														.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblTempsDeConduite)
																.addComponent(lblTempsDeCon))
																.addGap(18)
																.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
																		.addComponent(lblTempsDeConduite_1)
																		.addComponent(lblTempsDeConduite_2)))
				);
		panel_2.setLayout(gl_panel_2);

		lblToursParRelais_1 = new JLabel(""+this.course_.getNbToursParRelais_());
		lblToursParRelais_1.setForeground(SystemColor.textHighlight);
		lblToursParRelais_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblToursEffecuts = new JLabel(""+0);
		lblToursEffecuts.setForeground(SystemColor.textHighlight);
		lblToursEffecuts.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblToursRestants_1 = new JLabel("Tours restants");
		lblToursRestants_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblToursRestants_1.setForeground(SystemColor.textHighlight);

		lblTempsTourPrcdent = new JLabel("Temps tour précédent");
		lblTempsTourPrcdent.setForeground(SystemColor.textHighlight);
		lblTempsTourPrcdent.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblEtat_1 = new JLabel("Etat");
		lblEtat_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEtat_1.setForeground(SystemColor.textHighlight);
		lblEtat_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		gl_panelInfoVoiture = new GroupLayout(panelInfoVoiture);
		gl_panelInfoVoiture.setHorizontalGroup(
				gl_panelInfoVoiture.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInfoVoiture.createSequentialGroup()
						.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelInfoVoiture.createSequentialGroup()
										.addComponent(lblEtat)
										.addPreferredGap(ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
										.addComponent(lblEtat_1))
										.addGroup(Alignment.TRAILING, gl_panelInfoVoiture.createSequentialGroup()
												.addComponent(lblProgressionTour, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
												.addGroup(Alignment.TRAILING, gl_panelInfoVoiture.createSequentialGroup()
														.addComponent(lblToursParRelais)
														.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
														.addComponent(lblToursParRelais_1))
														.addGroup(Alignment.TRAILING, gl_panelInfoVoiture.createSequentialGroup()
																.addComponent(lblNewLabel)
																.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
																.addComponent(lblToursEffecuts))
																.addGroup(Alignment.TRAILING, gl_panelInfoVoiture.createSequentialGroup()
																		.addComponent(lblToursRestants)
																		.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
																		.addComponent(lblToursRestants_1))
																		.addGroup(Alignment.TRAILING, gl_panelInfoVoiture.createSequentialGroup()
																				.addComponent(lblTempsTourPrecdent)
																				.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
																				.addComponent(lblTempsTourPrcdent)))
																				.addGap(18)
																				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
																				.addGap(29))
				);
		gl_panelInfoVoiture.setVerticalGroup(
				gl_panelInfoVoiture.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInfoVoiture.createSequentialGroup()
						.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelInfoVoiture.createSequentialGroup()
										.addGap(11)
										.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelInfoVoiture.createSequentialGroup()
														.addComponent(lblProgressionTour)
														.addGap(29)
														.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblToursParRelais_1)
																.addComponent(lblToursParRelais))
																.addGap(17)
																.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.BASELINE)
																		.addComponent(lblToursEffecuts)
																		.addComponent(lblNewLabel))
																		.addGap(17)
																		.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.BASELINE)
																				.addComponent(lblToursRestants_1)
																				.addComponent(lblToursRestants)))
																				.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																				.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.LEADING)
																						.addGroup(gl_panelInfoVoiture.createSequentialGroup()
																								.addGap(16)
																								.addComponent(lblTempsTourPrecdent))
																								.addGroup(gl_panelInfoVoiture.createSequentialGroup()
																										.addGap(18)
																										.addComponent(lblTempsTourPrcdent)))
																										.addGap(18)
																										.addGroup(gl_panelInfoVoiture.createParallelGroup(Alignment.LEADING)
																												.addComponent(lblEtat)
																												.addComponent(lblEtat_1)))
																												.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		panelInfoVoiture.setLayout(gl_panelInfoVoiture);
		// }
		//} Fin des for


		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JLabel lblDateDuJour = new JLabel("Date du jour");

		btnDemarrer = new JButton("Demarrer");

		btnDemarrer.setActionCommand("Demarrer");

		btnArreter.setActionCommand("Arreter");

		btnDemarrer.addActionListener(this);

		JLabel label = new JLabel("Heure actuelle");

		lblHeureActuelle = new JLabel("Heure actuelle");
		lblHeureActuelle.setForeground(SystemColor.textHighlight);
		lblHeureActuelle.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel label_1 = new JLabel("Heure de depart");

		lblHeureDeDpart = new JLabel("Heure de départ");
		lblHeureDeDpart.setForeground(SystemColor.textHighlight);
		lblHeureDeDpart.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblDateDeDpart = new JLabel("Date de départ");

		lblDateDeDpart_1 = new JLabel("Date de départ");
		lblDateDeDpart_1.setForeground(SystemColor.textHighlight);
		lblDateDeDpart_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblDateDuJour_1 = new JLabel("Date du jour");
		lblDateDuJour_1.setForeground(SystemColor.textHighlight);
		lblDateDuJour_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		btnTop = new JButton("Bouton TOP");
		//      tabbedPane.add(table);
		//      tabbedPane.add(header);
		// tabbedPane.add(timer);

		btnTop.addActionListener(this);

		JLabel lblClassementGnral = new JLabel("Classement général");

		JProgressBar progressBarGnl_ = new JProgressBar();
		progressBarGnl_.setStringPainted(true);
		progressBarGnl_.setValue(5);

		JTextPane txtpnProchaineVoiture = new JTextPane();
		txtpnProchaineVoiture.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnProchaineVoiture.setEnabled(false);
		txtpnProchaineVoiture.setEditable(false);
		txtpnProchaineVoiture.setText("Prochaine voiture");

		lblTempsRestant_1 = new JLabel("0");
		lblTempsRestant_1.setForeground(SystemColor.textHighlight);
		lblTempsRestant_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblTempscoul_1 = new JLabel();
		lblTempscoul_1.setText("0");
		lblTempscoul_1.setForeground(SystemColor.textHighlight);
		lblTempscoul_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblProchaineVoiture_1 = new JLabel("Prochaine voiture");

		JLabel lblTempscoul = new JLabel("Temps écoulé");

		JLabel lblTempsRestant = new JLabel("Temps restant");
		gl_pInfosCourse_ = new GroupLayout(pInfosCourse_);
		gl_pInfosCourse_.setHorizontalGroup(
				gl_pInfosCourse_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pInfosCourse_.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.LEADING)
								.addComponent(lblClassementGnral, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pInfosCourse_.createSequentialGroup()
												.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.LEADING)
														.addComponent(lblTempsRestant)
														.addComponent(lblProchaineVoiture_1)
														.addComponent(lblTempscoul, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
														.addGap(65)
														.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.TRAILING)
																.addComponent(lblTempscoul_1)
																.addComponent(lblTempsRestant_1)
																.addComponent(txtpnProchaineVoiture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(progressBarGnl_, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(btnTop, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addGroup(Alignment.LEADING, gl_pInfosCourse_.createSequentialGroup()
																				.addComponent(lblDateDuJour_1)
																				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(lblDateDeDpart_1))
																				.addGroup(Alignment.LEADING, gl_pInfosCourse_.createSequentialGroup()
																						.addComponent(lblDateDuJour)
																						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																						.addComponent(lblDateDeDpart))
																						.addGroup(Alignment.LEADING, gl_pInfosCourse_.createSequentialGroup()
																								.addComponent(label)
																								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(label_1))
																								.addComponent(btnDemarrer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
																								.addGroup(Alignment.LEADING, gl_pInfosCourse_.createSequentialGroup()
																										.addComponent(lblHeureActuelle)
																										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																										.addComponent(lblHeureDeDpart)))))
																										.addContainerGap())
				);
		gl_pInfosCourse_.setVerticalGroup(
				gl_pInfosCourse_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pInfosCourse_.createSequentialGroup()
						.addComponent(btnDemarrer)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(label_1))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblHeureActuelle)
										.addComponent(lblHeureDeDpart))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblDateDuJour)
												.addComponent(lblDateDeDpart))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblDateDuJour_1)
														.addComponent(lblDateDeDpart_1))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnTop)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblClassementGnral)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(progressBarGnl_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(7)
														.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.LEADING)
																.addComponent(lblProchaineVoiture_1)
																.addComponent(txtpnProchaineVoiture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTempscoul_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_pInfosCourse_.createSequentialGroup()
																				.addComponent(lblTempscoul)
																				.addGap(18)
																				.addGroup(gl_pInfosCourse_.createParallelGroup(Alignment.BASELINE)
																						.addComponent(lblTempsRestant)
																						.addComponent(lblTempsRestant_1))))
																						.addContainerGap())
				);
		pInfosCourse_.setLayout(gl_pInfosCourse_);

		String [] titre = {"Voiture",
				"Pilote",
				"Nb tours",
				"Tours",
				"Temps",
				"Relais",
				"Etat",
				"Heure",
				"Commentaires"
		};

		model = new     DefaultTableModel(data, titre);
		GroupLayout gl_conteneur_ = new GroupLayout(conteneur_);
		gl_conteneur_.setHorizontalGroup(
				gl_conteneur_.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_conteneur_.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_conteneur_.createParallelGroup(Alignment.TRAILING)
								.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1012, Short.MAX_VALUE)
								.addGroup(gl_conteneur_.createSequentialGroup()
										.addComponent(pInfosCourse_, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tabbedPaneVoitures, GroupLayout.PREFERRED_SIZE, 707, GroupLayout.PREFERRED_SIZE)))
										.addContainerGap())
				);
		gl_conteneur_.setVerticalGroup(
				gl_conteneur_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_conteneur_.createSequentialGroup()
						.addGroup(gl_conteneur_.createParallelGroup(Alignment.LEADING)
								.addComponent(pInfosCourse_, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
								.addComponent(tabbedPaneVoitures, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				);

		table = new JTable(model);

		scrollPane = new JScrollPane(table);
		tabbedPane.addTab("Les tops de la Voiture", (Icon) null, scrollPane, null);



		table_1 = new JTable();
		tabbedPane.addTab("Tops "+listeVoitures_.get(0).getNumeroVoiture_(), null, table_1, null);
		conteneur_.setLayout(gl_conteneur_);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnTop && estDemarrer){

			this.lblToursEffecuts.setText(""+nbTours_);

			;
			this.lblToursRestants_1.setText(""+nbToursRestant);
			this.addRowTop();
		}
		if(e.getActionCommand() == "Demarrer"){
			if(estDemarrer == false){
				estDemarrer = true;
				this.timer.start(); 
				this.btnDemarrer.setText("Arrêter");

				this.addRowTop();
				this.lblToursEffecuts.setText("Start");
			}
			else{
				int reponse = JOptionPane.showConfirmDialog(this,
						"Etes-vous sûr de vouloir arrêter la course ?",
						"Etiquettes Java", 
						JOptionPane.YES_NO_OPTION);
				if (reponse == JOptionPane.YES_OPTION){
					estDemarrer = false;
					this.addRowTop();
					this.timer.stop();
					this.btnDemarrer.setEnabled(false);
				}
				else if (reponse == JOptionPane.NO_OPTION) System.out.println("Courage");;



			}
		}
		if(e.getActionCommand() == "Arreter"){
			this.btnDemarrer.setEnabled(false);
			estDemarrer = false;
			this.timer.stop(); 
		}
		if(e.getSource() == comboBoxListePilotes){
			int i = comboBoxListePilotes.getSelectedIndex();
			System.out.println("Pilote   "+i);
			this.lblPiloteActuel_1.setText(liste.getElementAt(i));
		}
		if(e.getSource() == mntmArreterCourse){
			this.btnDemarrer.setEnabled(false);
			estDemarrer = false;
			this.timer.stop(); 
		}
		else if(estDemarrer){
			//this.btnTop.setEnabled(false);
			this.timeCount ++;
			this.lblTempscoul_1.setText(""+convert(this.timeCount));
			// System.out.println("C'est\t"+tabbedPaneVoitures.getSelectedIndex());
		}
	}

	public String tempsEcoule(int delay){
		Date date = new Date(delay);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy h:mm,a", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String formattedDate = sdf.format(date);
		System.out.println(formattedDate); 
		return "";
	}

	public String convert(int delay){
		String res = "";
		int re = delay % 1000;
		String reS ="";
		if(re < 10){
			reS+="00";
		}
		if(re < 100 && re > 10){
			res+="0";
		}
		reS+=re;
		delay /= 1000;

		int secondes = delay % 60;
		delay /= 60;
		String sec = "";
		if(secondes < 10){
			sec+="0";
		}
		sec += secondes;

		int minutes = delay % 60;
		delay /= 60;
		String min = "";
		if(minutes < 10){
			min += "0";
		}
		min += minutes;

		int heures = delay % 60;
		String h = "";
		if(heures < 10){
			h += "0";
		}
		h += heures;
		delay /= 24;
		String reste = "";
		if(delay < 10){
			reste += "0";
		}
		reste += delay;


		res+=reste+":"+h+":"+min+":"+sec+":"+reS;
		return res;
	}

	/**
	 * Méthode d'ajout d'une ligne dans le tableau de top
	 */
	public void addRowTop(){
		//
		//
		//		String [] titre = {"Voiture",
		//				"Pilote",
		//				"Nb tours",
		//				"Tours",
		//				"Temps",
		//				"Relais",
		//				"Etat",
		//				"Heure",
		//				"Commentaires"
		//		};
		String voiture_ = "Voiture "+(this.tabbedPaneVoitures.getSelectedIndex()+1);

		String pilote_1 = this.liste.getElementAt(this.comboBoxListePilotes.getSelectedIndex()); 

		String pilote_ = (String) this.comboBoxListePilotes.getSelectedItem();

		Calendar cal = Calendar.getInstance();
		cal.getTimeZone();


		SimpleDateFormat date = new SimpleDateFormat("dd:M:YYYY  HH:mm:ss:SS");
		String d = date.format(cal.getInstance().getTime());

		int numRelais = this.course_.getNbToursMax_();

		int size = model.getRowCount();


		int last = 0;
		int diff = 0;
		if(size > 0){
			diff  = timeCount - last;
			System.out.println("\nLe délai\t"+last+"\n");
			Long i = 0L;
			try {
				Date d1 = date.parse(d);
				i = d1.getTime();
				String time0 = (String) model.getValueAt(size - 1, 7);
				Date d0 = date.parse(time0);
				Long d00 = d0.getTime();
				diff = (int) (i - d00);

				String duree = date.format(diff);

				System.out.println();
				System.out.println("La durée \t"+diff);
				System.out.println();

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else
			diff = 0;

		SimpleDateFormat format = new SimpleDateFormat("mm:ss:SS");

		System.out.println();
		System.out.println("La différence de chrono\t"+timeCount+"\tet du précédent "+last+"\test\t"+diff);
		System.out.println();
		String[] vect = {voiture_, pilote_,""+nbTours_++, comboData[0], ""+format.format(diff), 
				""+relais_, "race", ""+d, "Ajoutez votre commentaire"};

		JComboBox combo = new JComboBox(comboData);
		this.table.getColumn("Etat").setCellEditor(new  DefaultCellEditor(combo));
		
		this.nbToursRestant -- ;

		this.lblTempsTourPrcdent.setText(format.format(diff));

		model.addRow(vect);
		
		if(size > 1)
			this.lblEtat_1.setText(""+model.getValueAt(size - 1, 6));

		//this.relais_ = this.nbTours_ / numRelais;

	}

	private int convert(String valueAt) {
		int result = 0;

		System.out.println("Taille\t"+valueAt.length()+" \t le mot\t"+valueAt);

		int reste = Integer.parseInt(valueAt.substring(0, 2));

		result = reste * 24;

		int h = (Integer.parseInt(valueAt.substring(3, 5)));

		result = (result + h)*60;

		int min = Integer.parseInt(valueAt.substring(6, 8));

		result = (result + min)*60;

		int sec = Integer.parseInt(valueAt.substring(9, 11));

		result = (result + sec)*10;

		int reS = Integer.parseInt(valueAt.substring(12,14 ));

		result+=reS;

		//          
		//        System.out.println("reste\t"+reste);
		//        System.out.println("h\t"+h);
		//        System.out.println("min\t"+min);
		//        System.out.println("sec\t"+sec);
		//        System.out.println("reS\t"+reS);

		return result;
	}
} 