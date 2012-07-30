package premier;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class TableMulti {

	private List<Variable> liste1;
	private List<Variable> liste2;
	private Map<Integer,Vector<Produit>> resultat;// la clef est la puissance du produit
	private Map<Integer,Vector<Produit>> suivant_ordre2;
	
	public TableMulti(List<Variable> liste1,
		List<Variable>liste2)
	{
		assert(liste1!=null);
		assert(liste2!=null);
		this.liste1=liste1;
		this.liste2=liste2;
	}

	public void calcul() {
		int i,j;
		Vector<Produit> tmp,tmp2;
		Produit p;
		resultat=new TreeMap<Integer,Vector<Produit>>();
		suivant_ordre2=new TreeMap<Integer,Vector<Produit>>();
		for(i=0;i<liste2.size();i++)
		{
			for(j=0;j<liste1.size();j++)
			{
				if(resultat.containsKey(j+i))
				{
					tmp=resultat.get(j+i);
				}
				else
				{
					tmp=new Vector<Produit>();
					resultat.put(j+i, tmp);
				}
				if(suivant_ordre2.containsKey(i))
				{
					tmp2=suivant_ordre2.get(i);
				}
				else
				{
					tmp2=new Vector<Produit>();
					suivant_ordre2.put(i, tmp2);
				}
				assert(tmp!=null);
				assert(tmp2!=null);
				p=new Produit(j+i);
				p.add(liste2.get(i));
				p.add(liste1.get(j));
				tmp.add(p);
				tmp2.add(p);
			}
		}
	}

	public void affiche(PrintStream out) {
		assert(resultat!=null);
		int i,j;
		Set<Integer> set;
		Vector<Produit> tmp;
		Produit p;
		String s,s2;
		boolean trouve;
		set=suivant_ordre2.keySet();
		for(int nb:set)
		{
			tmp=suivant_ordre2.get(nb);
			s="";
			//out.println(tmp.toString());
			for(i=nb_colonnes()-1;i>=0;i--)
			{
				trouve=false;
				s2="";
				for(j=0;j<tmp.size();j++)
				{
					p=tmp.get(j);
					if(p.getNo()==i)
					{
						assert(!trouve);
						s2+=p.toString();
						trouve=true;
					}
				}
				while(s2.length()<7)
				{// on met 7 caracteres espaces
					s2+=" ";
				}
				s+=s2+" ";
			}
			out.println(s);
		}
	}

	public int nb_colonnes() {
		return liste1.size()+liste2.size()-1;
	}
	
	public Map<Integer,Vector<Produit>> getEquations()
	{
		return resultat;
	}
	
	public Map<Integer,Vector<Produit>> getEquations2()
	{
		return suivant_ordre2;
	}
}
