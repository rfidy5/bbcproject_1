package chrono_views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import chrono_controller.ControllerVoiture;
import chrono_model.Evenement;
import chrono_model.Pilote;
import chrono_model.Voiture;

import javax.swing.JScrollPane;

public class VoitureIhmImpl extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField textFieldNumVoiture;
	private JTextField textField_CouleurVoiture;
	private JTextField textField_NbToursParRelais;
	private JTextField textField_NomPilote;
	private JTextField textField_CouleurCasque;
	private JList list_Pilotes;

	private DefaultListModel<Pilote> listePilotes_ = new DefaultListModel<Pilote>();
	private JButton btnAjouterPilote;
	private JButton btnModifierPilote;
	private JButton btnSauvegarderVoiture;
	private JButton btnImprimerVoiture;
	private JButton btnSupprimerPilote;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private ControllerVoiture controller_;

	private Evenement event ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoitureIhmImpl frame = new VoitureIhmImpl(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VoitureIhmImpl(EvenementIhmImpl evenementIhmImpl,
			Evenement observable_) {
		this.event = observable_;
		controller_=ControllerVoiture.getInstance();
//		this.controller_ = new ControllerVoiture(new Voiture(), this);
		setResizable(false);
		setTitle("Cr�er une voiture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 527);
		this.setSize(900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.inactiveCaption);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		JLabel lblNDeLa = new JLabel("N\u00B0 de la voiture :");
		lblNDeLa.setBounds(10, 80, 99, 14);
		desktopPane.add(lblNDeLa);

		textFieldNumVoiture = new JTextField();
		textFieldNumVoiture.setBounds(158, 77, 193, 20);
		desktopPane.add(textFieldNumVoiture);
		textFieldNumVoiture.setColumns(10);

		JLabel lblCouleur = new JLabel("Couleur de la voiture");
		lblCouleur.setBounds(10, 121, 138, 14);
		desktopPane.add(lblCouleur);

		textField_CouleurVoiture = new JTextField();
		textField_CouleurVoiture.setBounds(158, 118, 193, 20);
		desktopPane.add(textField_CouleurVoiture);
		textField_CouleurVoiture.setColumns(10);

		JLabel lblNombreDeTours = new JLabel("Nombre de tours par relais :");
		lblNombreDeTours.setBounds(452, 95, 142, 14);
		desktopPane.add(lblNombreDeTours);

		textField_NbToursParRelais = new JTextField();
		textField_NbToursParRelais.setBounds(624, 92, 232, 20);
		desktopPane.add(textField_NbToursParRelais);
		textField_NbToursParRelais.setColumns(10);

		JLabel lblCrationDuPilote = new JLabel("Cr\u00E9ation du pilote");
		lblCrationDuPilote.setFont(lblCrationDuPilote.getFont().deriveFont(lblCrationDuPilote.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, lblCrationDuPilote.getFont().getSize() + 2f));
		lblCrationDuPilote.setBounds(170, 314, 126, 14);
		desktopPane.add(lblCrationDuPilote);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(22, 342, 46, 14);
		desktopPane.add(lblNom);




		JList list = new JList();
		list.setBounds(475, 269, 79, -23);
		desktopPane.add(list);



		model = new DefaultListModel();
		list_Pilotes = new JList(model);
		list_Pilotes.setValueIsAdjusting(true);
		list_Pilotes.setVisibleRowCount(model .size());

		textField_NomPilote = new JTextField();
		textField_NomPilote.setBounds(158, 339, 193, 20);
		desktopPane.add(textField_NomPilote);
		textField_NomPilote.setColumns(10);

		JLabel lblCouleurDeCasque = new JLabel("Couleur de casque :");
		lblCouleurDeCasque.setBounds(22, 373, 112, 14);
		desktopPane.add(lblCouleurDeCasque);

		textField_CouleurCasque = new JTextField();
		textField_CouleurCasque.setBounds(158, 370, 193, 20);
		desktopPane.add(textField_CouleurCasque);
		textField_CouleurCasque.setColumns(10);

		btnModifierPilote = new JButton("Modifier");
		btnModifierPilote.addActionListener(this);

		btnAjouterPilote = new JButton("Ajouter");
		btnAjouterPilote.addActionListener(this);

		//		btnAjouterPilote.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//
		//
		//				int i = list_Pilotes.getSelectedIndex();
		//				String NomPilote = textField_NomPilote.getText();
		//
		//				// Pour une premiere insertion 
		//				if(NomPilote != "" && !model.contains(NomPilote)  ){
		//
		//					model.addElement(NomPilote);
		//					textField_NomPilote.setText("");
		//					textField_CouleurCasque.setText("");
		//
		//					System.out.println("1 " + NomPilote);
		//
		//
		//				}
		//
		//				String ValeurListe = (String) model.getElementAt(i);
		//
		//
		//				// Quand on modifie un element de la liste des pilotes
		//
		//				if(NomPilote != ""){
		//					if(model.contains(NomPilote)  ){
		//						textField_NomPilote.setText(NomPilote);	
		//						String nom = textField_NomPilote.getText();
		//
		//
		//						if( nom !=""  && nom !=ValeurListe)
		//						{
		//							model.set(i, nom);
		//							System.out.println("2 "+ nom);
		//						}
		//						textField_NomPilote.setText("");
		//						textField_CouleurCasque.setText("");
		//
		//
		//						// Si la liste contient des doublons
		//						if(NomPilote.equals(nom)){
		//
		//							model.removeElement(nom);
		//
		//						} // end if doublons
		//
		//					} // end if modification Element de la liste
		//
		//				}// end if premiere insert
		//			}
		//		});
		btnAjouterPilote.setBounds(237, 421, 114, 23);
		desktopPane.add(btnAjouterPilote);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(452, 169, 404, 227);
		desktopPane.add(scrollPane);


		list_Pilotes.setBorder(new TitledBorder(null, "Liste des pilotes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(list_Pilotes);

		btnModifierPilote.addActionListener(this);

		//		btnModifierPilote.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//
		//				int i = list_Pilotes.getSelectedIndex();
		//				// String texte = (String) model.getElementAt(i);
		//				Pilote p = listePilotes_.getElementAt(i);
		//				String nom = p.getNomPilote_();
		//				textField_NomPilote.setText(nom);
		//
		//				String couleur = p.getCouleurCasque_();
		//				textField_CouleurCasque.setText(couleur);
		//
		//			}
		//		});
		btnModifierPilote.setBounds(452, 421, 112, 23);
		desktopPane.add(btnModifierPilote);

		btnSupprimerPilote = new JButton("Supprimer");
		btnSupprimerPilote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = list_Pilotes.getSelectedIndex();
				String NomPilote = (String) model.getElementAt(i);
				//String CouleurPilote = textField_CouleurCasque.getText();
				model.removeElement(NomPilote);
				//model.removeElement(CouleurPilote);

			}
		});
		btnSupprimerPilote.setBounds(744, 421, 112, 23);
		desktopPane.add(btnSupprimerPilote);

		btnSauvegarderVoiture = new JButton("Sauvegarder");
		btnSauvegarderVoiture.addActionListener(this);
		//		btnSauvegarderVoiture.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//
		//				if(  textFieldNumVoiture.getText().isEmpty())
		//				{
		//					JPanel panel = new JPanel();
		//					JOptionPane.showMessageDialog(panel, "Vueillez remplir tous les champs", "Erreur ", JOptionPane.ERROR_MESSAGE);
		//				}  else{
		//					dispose();
		//					//					EvenementIhmImpl event = new  EvenementIhmImpl();
		//					//					event.show();
		//
		//				}
		//
		//			}
		//		});
		btnSauvegarderVoiture.setBounds(237, 513, 114, 23);
		desktopPane.add(btnSauvegarderVoiture);

		btnImprimerVoiture = new JButton("Imprimer");
		btnImprimerVoiture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImprimerVoiture.setBounds(450, 513, 114, 23);
		desktopPane.add(btnImprimerVoiture);


	}

	/**
	 * @return the textFieldNumVoiture
	 */
	public JTextField getTextFieldNumVoiture() {
		return textFieldNumVoiture;
	}

	/**
	 * @param textFieldNumVoiture the textFieldNumVoiture to set
	 */
	public void setTextFieldNumVoiture(JTextField textFieldNumVoiture) {
		this.textFieldNumVoiture = textFieldNumVoiture;
	}

	/**
	 * @return the textField_CouleurVoiture
	 */
	public JTextField getTextField_CouleurVoiture() {
		return textField_CouleurVoiture;
	}

	/**
	 * @param textField_CouleurVoiture the textField_CouleurVoiture to set
	 */
	public void setTextField_CouleurVoiture(JTextField textField_CouleurVoiture) {
		this.textField_CouleurVoiture = textField_CouleurVoiture;
	}

	/**
	 * @return the textField_NbToursParRelais
	 */
	public JTextField getTextField_NbToursParRelais() {
		return textField_NbToursParRelais;
	}

	/**
	 * @param textField_NbToursParRelais the textField_NbToursParRelais to set
	 */
	public void setTextField_NbToursParRelais(JTextField textField_NbToursParRelais) {
		this.textField_NbToursParRelais = textField_NbToursParRelais;
	}

	/**
	 * @return the textField_NomPilote
	 */
	public JTextField getTextField_NomPilote() {
		return textField_NomPilote;
	}

	/**
	 * @param textField_NomPilote the textField_NomPilote to set
	 */
	public void setTextField_NomPilote(JTextField textField_NomPilote) {
		this.textField_NomPilote = textField_NomPilote;
	}

	/**
	 * @return the textField_CouleurCasque
	 */
	public JTextField getTextField_CouleurCasque() {
		return textField_CouleurCasque;
	}

	/**
	 * @param textField_CouleurCasque the textField_CouleurCasque to set
	 */
	public void setTextField_CouleurCasque(JTextField textField_CouleurCasque) {
		this.textField_CouleurCasque = textField_CouleurCasque;
	}

	/**
	 * @return the list_Pilotes
	 */
	public JList getList_Pilotes() {
		return list_Pilotes;
	}

	/**
	 * @param list_Pilotes the list_Pilotes to set
	 */
	public void setList_Pilotes(JList list_Pilotes) {
		this.list_Pilotes = list_Pilotes;
	}

	/**
	 * @return the listePilotes_
	 */
	public List<Pilote> getListePilotes_() {
		List<Pilote> liste = new ArrayList<Pilote>();
		int i = this.listePilotes_.getSize();
		if(i > 0)
			for(int j = 0; j < i; j++){
				liste.add(this.listePilotes_.getElementAt(j));
			}
		return liste;
	}

	/**
	 * @param listePilotes_ the listePilotes_ to set
	 */
	public void setListePilotes_(DefaultListModel<Pilote> listePilotes_) {
		this.listePilotes_ = listePilotes_;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btnAjouterPilote){
			String nomPilote_ = this.getTextField_NomPilote().getText();
			String couleurCasque_ = this.getTextField_CouleurCasque().getText();
			if(!nomPilote_.isEmpty()){
				Pilote p = new Pilote(nomPilote_);
				if(!couleurCasque_.isEmpty())
					p.setCouleurCasque_(couleurCasque_);

				model.addElement(nomPilote_);
				listePilotes_.addElement(p);
			}
			this.textField_CouleurCasque.setText("");
			this.textField_NomPilote.setText("");
		}

		if(e.getSource() == this.btnSauvegarderVoiture){
			String numVoiture_ = this.textFieldNumVoiture.getText();
			if(numVoiture_.isEmpty())
				JOptionPane.showMessageDialog(this, "Veuillez saisir un num�ro de voiture valide", "Error", JOptionPane.ERROR_MESSAGE);
			else{
				this.controller_.control(this,this.event);
				this.dispose();
			}
		}

		if(e.getSource() == this.btnModifierPilote){
			int index;
			try{
				index = this.list_Pilotes.getSelectedIndex();
				// System.out.println("Index \t"+index);
				Pilote p = this.listePilotes_.getElementAt(index);
				this.model.removeElementAt(index);
				this.listePilotes_.removeElementAt(index);
				this.textField_CouleurCasque.setText(p.getCouleurCasque_());
				this.textField_NomPilote.setText(p.getNomPilote_());
			}catch (Exception ArrayIndexOutOfBoundsException) {
				if(ArrayIndexOutOfBoundsException.getMessage().equals("-1")) 
					if(!this.listePilotes_.isEmpty())
						JOptionPane.showMessageDialog(this, "Selectionner un pilote", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void display() {
		this.setVisible(true);
		
	}

}
