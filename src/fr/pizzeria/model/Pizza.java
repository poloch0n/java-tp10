package fr.pizzeria.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

import fr.pizzeria.utils.Rule;
import fr.pizzeria.utils.StringUtils;
import fr.pizzeria.utils.ToString;

public class Pizza {
	public int id;
	@ToString(uppercase = true)
	@Rule(empty=false)
	public String code;
	@ToString(uppercase = true)
	@Rule(empty=false)	
	public String libelle;
	@ToString
	@Rule(min=0)
	public double prix;
	public CategoriePizza categorie;
	private int compteur = 0;

	public Pizza(String code, String libelle, double prix, CategoriePizza categorie){
		compteur++;
		this.id = compteur;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
	}
	
	public Pizza(Integer id,String code, String libelle, double prix, CategoriePizza categorie){
		//to think doublons
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
	}
	
	public String ToString() throws IllegalArgumentException, IllegalAccessException {
		StringUtils.ToString(this);
		
		Field[] fields = this.getClass().getFields();
		String msg = "";
		for(Field f : fields) {
			for(Annotation a : f.getAnnotations()) {
				if(a instanceof ToString) {
					String field = ""+f.get(this);
					switch(f.getName()) {
					case "id":
						msg += "id = "+field;
						break;
					case "code":
						msg += " " +field;
						break;
					case "libelle":
						msg += " -> " + field;
						break;
					case "prix":
						msg += " (" + field + " €€) ";
						break;
					case "categorie":
						msg += "option : "+field;
						break;
					}
				}
			};
		}
		return msg;
	}
}
