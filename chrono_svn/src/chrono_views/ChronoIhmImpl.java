/***********************************************************************
 * Module:  EvenementIhmImp.java
 * Author:  29004847
 * Purpose: Defines the Class EvenementIhmImp
 ***********************************************************************/

package chrono_views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;


import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import chrono_controller.ControllerChrono;
import chrono_controller.ControllerEvent;
import chrono_utils.JDomOperations;

import java.awt.Cursor;
import java.io.File;
import java.util.List;
import java.awt.SystemColor;


public class ChronoIhmImpl extends JFrame implements ActionListener {

	private JMenuBar menuBar_;
	private JMenu fichier_;
	private JMenu aPropos_;
	private JPanel conteneur_ = new JPanel();
	private JMenuItem newEvent_;
	private JMenuItem aide_;
	private JMenuItem quitter_;
	private JSeparator separator;
	private JList<String> eventList_;
	private JButton btnCharger_;
	private JButton btnSupprimer_;
	private JPanel panelBoutonsGerer_;
	private JButton btnCreerNouvelEvenement_;
	JFileChooser fc;

	private ControllerChrono controllerChrono_;
	private ControllerEvent controllerEvent_;
	private DefaultListModel<String> listModel;
	
	
	public ChronoIhmImpl() {
		setForeground(SystemColor.activeCaption);
//		controllerChrono_=new ControllerChrono();
		controllerChrono_=ControllerChrono.getInstance();
		controllerEvent_=ControllerEvent.getInstance();
		
		
		listModel= new DefaultListModel<String>();

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


		newEvent_ = new JMenuItem("Nouvel �v�nement");
		newEvent_.addActionListener(this);
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
		conteneur_.setForeground(SystemColor.activeCaption);

		this.setContentPane(conteneur_);

		/*
		 * Test cr�ation d'une liste
		 */
		
		List<String> listOfEvents=controllerChrono_.getListOfEvents();
		for(String eventString:listOfEvents){
			listModel.addElement(eventString);
		}
//		listModel.addElement("Monte Carlo 2012");
//		listModel.addElement("24 heures du Mans 2012");

		JScrollPane sc = new JScrollPane();
		
		eventList_ = new JList(listModel);
		eventList_.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		eventList_.setToolTipText("");
		eventList_.setFont(new Font("Arial Black", Font.BOLD, 16));

		eventList_.setVisibleRowCount(listModel.getSize());

		//sc.setViewportView(eventList_);

		panelBoutonsGerer_ = new JPanel();
		panelBoutonsGerer_.setBackground(SystemColor.activeCaption);

		btnCreerNouvelEvenement_ = new JButton("Creer un nouvel evenement");
		btnCreerNouvelEvenement_.addActionListener(this);
		btnCreerNouvelEvenement_.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		GroupLayout gl_conteneur_ = new GroupLayout(conteneur_);
		gl_conteneur_.setHorizontalGroup(
				gl_conteneur_.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_conteneur_.createSequentialGroup()
						.addGroup(gl_conteneur_.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_conteneur_.createSequentialGroup()
										.addContainerGap(214, Short.MAX_VALUE)
										.addComponent(eventList_, GroupLayout.PREFERRED_SIZE, 670, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_conteneur_.createSequentialGroup()
												.addGap(214)
												.addGroup(gl_conteneur_.createParallelGroup(Alignment.LEADING)
														.addComponent(btnCreerNouvelEvenement_, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
														.addComponent(panelBoutonsGerer_, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))))
														.addContainerGap())
				);
		gl_conteneur_.setVerticalGroup(
				gl_conteneur_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_conteneur_.createSequentialGroup()
						.addGap(12)
						.addComponent(eventList_, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(panelBoutonsGerer_, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
						.addComponent(btnCreerNouvelEvenement_)
						.addContainerGap())
				);

		fc = new JFileChooser();

		btnCharger_ = new JButton("Charger");
		btnCharger_.addActionListener(this);
		btnCharger_.setFont(new Font("Times New Roman", Font.BOLD, 16));

		btnSupprimer_ = new JButton("Supprimer");
		btnSupprimer_.addActionListener(this);
		btnSupprimer_.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GroupLayout gl_panelBoutonsGerer_ = new GroupLayout(panelBoutonsGerer_);
		gl_panelBoutonsGerer_.setHorizontalGroup(
			gl_panelBoutonsGerer_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBoutonsGerer_.createSequentialGroup()
					.addGap(218)
					.addComponent(btnCharger_)
					.addGap(101)
					.addComponent(btnSupprimer_))
		);
		gl_panelBoutonsGerer_.setVerticalGroup(
			gl_panelBoutonsGerer_.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBoutonsGerer_.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelBoutonsGerer_.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCharger_)
						.addComponent(btnSupprimer_)))
		);
		panelBoutonsGerer_.setLayout(gl_panelBoutonsGerer_);
		conteneur_.add(eventList_);
		conteneur_.add(btnCreerNouvelEvenement_);
		conteneur_.add(panelBoutonsGerer_);
		conteneur_.setLayout(gl_conteneur_);
	}



//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChronoIhmImpl frame = new ChronoIhmImpl();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public void display(){
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == newEvent_){
			EvenementIhmImpl event = new EvenementIhmImpl(null);
			event.setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btnCharger_){
			if(eventList_.getSelectedValue()!=null){
				String eventNameString=eventList_.getSelectedValue();
				controllerChrono_.chargerEvent(eventNameString);
				controllerEvent_.displayEventIhm();
				this.dispose();
			}
		}
		if(e.getSource() == btnSupprimer_){
			if(eventList_.getSelectedValue()!=null){
				String eventNameString=eventList_.getSelectedValue();
				controllerChrono_.supprimerEvent(eventNameString);
				listModel.remove(eventList_.getSelectedIndex());
			}
		}
		if(e.getSource() == btnCreerNouvelEvenement_){
			JDomOperations.setElementActuel_(null);
			controllerEvent_.displayEventIhm();	
			this.dispose();
		}
	}

}