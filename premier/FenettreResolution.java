package premier;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FenettreResolution extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8165755857391986957L;
	private List<Variable> liste1;
	private List<Variable> liste2;
	private List<Integer> valeur;
	private JTable table;
	private Object[][] cells={{1,"Abc"},{2,"Coucou"}};
	private String[] colonnes={"a","b"};
	private TableMutliModel model;
	private JFrame fenettre_variables;
	private JTable table_variables;
	private TableVariablesModel model_variables;
	private JButton bouton;
	private FenettreResolution f=this;
	
	public FenettreResolution(List<Variable> liste1, List<Variable> liste2,List<Integer> valeur)
	{
		assert(liste1!=null);
		assert(liste2!=null);
		this.liste1=liste1;
		this.liste2=liste2;
		this.valeur=valeur;
		setTitle("Mutliplication");
		setSize(400, 200);
		model=new TableMutliModel(liste1,liste2,valeur);
		table=new JTable(model);
		add(new JScrollPane(table),BorderLayout.CENTER);
		// la fenetre des variables
		fenettre_variables=new JFrame();
		fenettre_variables.setTitle("Variables");
		fenettre_variables.setSize(100, 200);
		model_variables=new TableVariablesModel(liste1,liste2);
		table_variables=new JTable(model_variables);
		fenettre_variables.add(new JScrollPane(table_variables),BorderLayout.CENTER);
		fenettre_variables.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model_variables.setFenetreParente(this);
		fenettre_variables.setVisible(true);
		// le bouton en bas
		bouton=new JButton("Actualiser");
		bouton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				f.actualise();
			}
		});
		JPanel p;
		p=new JPanel();
		p.add(bouton);
		add(p,BorderLayout.SOUTH);
	}

	public FenettreResolution()
	{
		setTitle("Mutliplication");
		setSize(400, 200);
		table=new JTable(cells,colonnes);
		add(new JScrollPane(table),BorderLayout.CENTER);
	}
	
	public void actualise()
	{
		System.out.println("Actualisation!");
		table.repaint();
	}
}
