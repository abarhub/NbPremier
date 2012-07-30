package premier;

public class Variable {

	private String nom;
	private boolean valeur;
	private int no;
	private boolean valeur_definie;
	
	public Variable(String nom,int no)
	{
		assert(nom!=null);
		this.nom=nom;
		this.no=no;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void setValeur(boolean val)
	{
		valeur_definie=true;
		valeur=val;
	}

	public boolean getValeur()
	{
		return valeur;
	}
	
	public int getNo()
	{
		return no;
	}
	
	public String toString()
	{
		String s;
		if(valeur_definie)
		{
			s=((valeur)?"1":"0");
		}
		else
		{

			s=nom;
		}
		return s;
	}
	
	public boolean equals(Object o)
	{
		if(o==null||!(o instanceof Variable))
		{
			return false;
		}
		if(o==this)
		{
			return true;
		}
		if(((Variable)o).getNom().equals(getNom()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isValeur_definie() {
		return valeur_definie;
	}
}
