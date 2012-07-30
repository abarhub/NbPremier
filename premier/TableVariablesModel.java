package premier;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableVariablesModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 77179239702342299L;
	private List<Variable> liste1;
	private List<Variable> liste2;
	private FenettreResolution resolution;

	public TableVariablesModel(List<Variable> liste1, List<Variable> liste2)
	{
		assert(liste1!=null);
		assert(liste2!=null);
		this.liste1=liste1;
		this.liste2=liste2;
	}
	
	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		assert(liste1!=null);
		assert(liste2!=null);
		return Math.max(liste1.size(), liste2.size());
	}

	public Object getValueAt(int ligne, int colonne) {
		Variable v=null;
		if(colonne==0)
		{
			return ligne;
		}
		else if(colonne==1)
		{
			v=liste1.get(ligne);
		}
		else if(colonne==2)
		{
			v=liste2.get(ligne);
		}
		else
		{
			assert(false):"colonne="+colonne;
		}
		if(v!=null&&v.isValeur_definie())
		{
			if(v.getValeur())
				return 1;
			else
				return 0;
		}
		else
		{
			return "";
		}
	}
	
	public boolean isCellEditable(int ligne,int colonne)
	{
		if(colonne==0)
			return false;
		if(colonne==1)
			return ligne>=0&&ligne<liste1.size();
		if(colonne==2)
			return ligne>=0&&ligne<liste2.size();
		return false;
	}
	
	public void setValueAt(Object o,int ligne,int colonne)
	{
		if(colonne==1&&ligne>=0&&ligne<liste1.size())
		{
			if(o instanceof String)
			{
				String s=(String) o;
				if(s.trim().equals("1"))
				{
					liste1.get(ligne).setValeur(true);
					resolution.actualise();
				}
				else if(s.trim().equals("0"))
				{
					liste1.get(ligne).setValeur(false);
					resolution.actualise();
				}
			}
		}
		else if(colonne==2&&ligne>=0&&ligne<liste2.size())
		{
			if(o instanceof String)
			{
				String s=(String) o;
				if(s.trim().equals("1"))
				{
					liste2.get(ligne).setValeur(true);
					resolution.actualise();
				}
				else if(s.trim().equals("0"))
				{
					liste2.get(ligne).setValeur(false);
					resolution.actualise();
				}
			}
		}
	}

	public void setFenetreParente(FenettreResolution resolution) {
		this.resolution=resolution;
	}
	
	public String getColumnName(int colonne)
	{
		if(colonne==1)
		{
			return "A";
		}
		if(colonne==2)
		{
			return "B";
		}
		return "";
	}
}
