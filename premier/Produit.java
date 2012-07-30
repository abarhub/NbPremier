package premier;

import java.util.List;
import java.util.Vector;

public class Produit {

	List<Variable> liste;
	private int no;
	
	public Produit(int no)
	{
		liste=new Vector<Variable>();
		this.no=no;
	}
	
	public void add(Variable v)
	{
		assert(v!=null);
		liste.add(v);
	}
	
	public int size()
	{
		return liste.size();
	}
	
	public Variable get(int i)
	{
		assert(i>=0);
		assert(i<size());
		return liste.get(i);
	}
	
	public String toString()
	{
		String s="";
		Variable v;
		boolean un=false,zero=false;
		for(int i=0;i<liste.size();i++)
		{
			v=liste.get(i);
			if(v.isValeur_definie())
			{
				if(v.getValeur())
				{
					un=true;
				}
				else
				{
					zero=true;
					break;
				}
			}
			else
			{
				if(s.length()>0)
					s+="*";
				s+=v;
			}
		}
		if(zero)
			return "0";
		if(s.equals("")&&un)
			return "1";
		return s;
	}
	
	public int getNo()
	{
		return no;
	}
	
	public boolean est_defini()
	{
		Variable v;
		for(int i=0;i<liste.size();i++)
		{
			v=liste.get(i);
			if(!v.isValeur_definie())
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean getValeur()
	{
		if(!est_defini())
			return false;
		Variable v;
		for(int i=0;i<liste.size();i++)
		{
			v=liste.get(i);
			if(!v.getValeur())
			{
				return false;
			}
		}
		return true;
	}
}
