package premier;

import java.util.List;
import java.util.Vector;

public class Outils {

	public List<Integer> liste_nb_premier(int max)
	{
		List<Integer> res;
		int i;
		res=new Vector<Integer>();
		for(i=2;i<=max;i++)
		{
			if(est_premier(i))
			{
				res.add(i);
			}
		}
		return res;
	}
	
	public boolean est_premier(int nb)
	{
		if(nb==2)
			return true;
		if(nb%2==0)
			return false;
		for(int i=2;i<=nb/2;i++)
		{
			if(nb%i==0)
			{
				return false;
			}
		}
		return true;
	}
	
	public int nb_zero(int nb)
	{
		String s;
		int res=0;
		s=Integer.toBinaryString(nb);
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='0')
				res++;
		}
		return res;
	}
	
	public int nb_un(int nb)
	{
		String s;
		int res=0;
		s=Integer.toBinaryString(nb);
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='1')
				res++;
		}
		return res;
	}
}
