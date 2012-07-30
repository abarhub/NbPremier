package premier;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableMutliModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9147559478981726029L;
	private List<Variable> liste1;
	private List<Variable> liste2;
	//private List<Integer> reste;
	private List<Integer> valeur;
	private TableMulti table;
	private int nb_colonnes;
	private Map<Integer,Vector<Produit>> equ;
	
	public TableMutliModel(List<Variable> liste1, List<Variable> liste2,List<Integer> valeur) {
		assert(liste1!=null);
		assert(liste2!=null);
		this.liste1=liste1;
		this.liste2=liste2;
		table=new TableMulti(liste1,liste2);
		table.calcul();
		nb_colonnes=table.nb_colonnes();
		equ=table.getEquations();
		this.valeur=valeur;
	}

	public int getColumnCount() {
		return nb_colonnes;
	}

	public int getRowCount() {
		return liste2.size()+5;
	}

	public Object getValueAt(int ligne, int colonne) {
		Set<Produit> set;
		int col2;
		assert(colonne>=0);
		assert(colonne<getColumnCount());
		assert(ligne>=0);
		assert(ligne<getRowCount());
		if(ligne>=0&&ligne<=1)
		{
			int r;
			col2=nb_colonnes-colonne-1;
			r=calcul_reste(col2);
			if(r<0)
				return "";
			else
			{
				if(ligne==0)
					return r;
				else
					return r%2;
			}
		}
		else if(ligne>=2&&ligne<liste2.size()+2)
		{
			col2=nb_colonnes-colonne-1;
			assert(col2>=0);
			set = calcul_elt(ligne-2, col2);
			if(set.size()==0)
				return "";
			if(set.size()==1)
				return set.toArray()[0];
			return set;
		}
		else if(ligne==liste2.size()+2)
		{
			return "=";
		}
		else if(ligne==liste2.size()+3)
		{
			int r;
			col2=nb_colonnes-colonne-1;
			r=calcul_colonne(col2);
			if(r<0)
				return "";
			else
			{
				return r;
			}
		}
		else if(ligne==liste2.size()+4)
		{
			return valeur.get(colonne);
		}
		else
		{
			return "*";
		}
	}

	private int calcul_colonne(int colonne) {
		int reste,resultat;
		assert(colonne>=0);
		assert(colonne<nb_colonnes);
		reste=calcul_reste(colonne);
		if(reste<0)
			return -1;
		resultat = calcul_colonne2(colonne);
		if(resultat<0)
			return -1;
		resultat=(reste+resultat)%2;
		return resultat;
	}

	private int calcul_colonne2(int colonne) {
		int resultat;
		Set<Produit> set;
		Produit p;
		resultat=0;
		for(int j=0;j<=colonne;j++)
		{
			set=calcul_elt(j,colonne);
			if(set.size()>0)
			{
				assert(set.size()==1);
				p=set.toArray(new Produit[0])[0];
				if(!p.est_defini())
				{
					return -1;
				}
				else
				{
					if(p.getValeur())
						resultat+=1;
				}
			}
		}
		return resultat;
	}

	private int calcul_reste(int colonne) {
		Set<Produit> set;
		Produit p;
		int r=0;
		assert(colonne>=0);
		assert(colonne<nb_colonnes);
		for(int i=0;i<colonne;i++)
		{
			//r2=0;
			for(int j=0;j<colonne;j++)
			{
				set=calcul_elt(j,i);
				if(set.size()>0)
				{
					assert(set.size()==1);
					p=set.toArray(new Produit[0])[0];
					if(!p.est_defini())
					{
						return -1;
					}
					else
					{
						if(p.getValeur())
							r+=1;
					}
				}
			}
			r=r/2;
		}
		return r;
	}

	private Set<Produit> calcul_elt(int ligne, int colonne) {
		int i;
		int j;
		int k;
		Set<Produit> set;
		Vector<Produit> tmp;
		Produit p;
		Variable v;
		boolean trouve;
		tmp=equ.get(colonne);
		assert(tmp!=null):"total="+nb_colonnes+","+colonne;
		set=new HashSet<Produit>();
		trouve=false;
		for(j=0;j<tmp.size();j++)
		{
			p=tmp.get(j);
			for(i=0;i<p.size();i++)
			{
				v=p.get(i);
				if(v.getNo()==ligne)
				{
					trouve=false;
					for(k=0;k<liste2.size();k++)
					{// recherche si elle est bien dans liste2
						if(v.equals(liste2.get(k)))
						{
							trouve=true;
							break;
						}
					}
					if(trouve)
					{// 
						set.add(p);
						break;
					}
				}
			}
		}
		return set;
	}

}
