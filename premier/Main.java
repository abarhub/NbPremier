package premier;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;

public class Main {

	public static void main(String arg[])
	{
		System.out.println("Debut du programme");
		//calcul_table_multi();
		//fenettre_resolution();
		calcul_reste();
		System.out.println("Fin du programme");
	}
	
	public static void calcul_table_multi()
	{
		List<Variable> liste1,liste2;
		int len=10,i;
		Variable v;
		TableMulti t;
		liste1=new Vector<Variable>();
		liste2=new Vector<Variable>();
		for(i=0;i<len;i++)
		{
			v=new Variable("a"+i,i);
			liste1.add(v);
		}
		for(i=0;i<len;i++)
		{
			v=new Variable("b"+i,i);
			liste2.add(v);
		}
		t=new TableMulti(liste1,liste2);
		t.calcul();
		t.affiche(System.out);
	}
	
	public static void fenettre_resolution()
	{
		FenettreResolution f;
		List<Variable> liste1,liste2;
		int len=10,i;
		Variable v;
		List<Integer> valeur;
		liste1=new Vector<Variable>();
		liste2=new Vector<Variable>();
		for(i=0;i<len;i++)
		{
			v=new Variable("a"+i,i);
			liste1.add(v);
		}
		for(i=0;i<len;i++)
		{
			v=new Variable("b"+i,i);
			liste2.add(v);
		}
		valeur=new Vector<Integer>();
		for(i=0;i<liste1.size()+liste2.size();i++)
		{
			valeur.add(1);
		}
		f=new FenettreResolution(liste1,liste2,valeur);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public static void calcul_reste()
	{
		List<Integer> liste_premier;
		Outils o;
		int nb_max=300024;
		int i,j,nb1,nb0,nb;
		String s;
		double ratio;
		Map<String,Integer> map;
		FileWriter fw=null;
		o=new Outils();
		System.out.println("Calcul des nombres premiers");
		liste_premier=o.liste_nb_premier(nb_max);
		System.out.println("Affichage de la liste");
		for(i=0;i<liste_premier.size();i++)
		{
			System.out.println("premier="+liste_premier.get(i));
		}
		System.out.println("Calcul des propriétés des nombres premiers");
		map=new HashMap<String, Integer>();
		for(i=0;i<liste_premier.size();i++)
		{
			//System.out.println("premier="+liste_premier.get(i));
			nb=liste_premier.get(i);
			s=Integer.toBinaryString(nb);
			nb1=o.nb_un(nb);
			nb0=o.nb_zero(nb);
			if(nb0>0)
			{
				ratio=(double)nb1/(double)nb0;
			}
			else
			{
				ratio=0.;
			}
			System.out.println("zero="+nb+","+s+","+nb1+","+nb0+","+ratio);
			/*nb1=Integer.bitCount(nb);
			nb0=Integer.numberOfLeadingZeros(nb);
			assert(nb1+nb0==Integer.highestOneBit(nb)):
				nb+","+nb1+","+nb0+","+Integer.highestOneBit(nb);*/
			s=ratio+"";
			if(map.containsKey(s))
			{
				map.put(s,map.get(s)+1);
			}
			else
			{
				map.put(s,1);
			}
		}
		/*for(i=0;i<liste_premier.size();i++)
		{
			for(j=0;j<i;j++)
			{
				
			}
		}*/
		System.out.println("affichage rapide du résultat");
		System.out.println(map);
		System.out.println("Génération du CVS...");
		try {
			fw=new FileWriter("E:\\workspace_nb_premier\\NbPremier\\test.csv",false);
			for(String s2:map.keySet())
			{
				fw.write(s2.replaceAll("\\.", ",")+";"+map.get(s2));
				fw.write("\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fw!=null)
			{
				try {
					fw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
