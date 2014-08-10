package tfd2;

public class Mots {
	private String key;
	private String definition;
	private String translation;
	
	public Mots(String key, String def, String trans) {
		this.key = key;
		this.definition = def;
		this.translation = trans;
	}
	public String toString(){return key;}
	public String getDefinitionEn() {return translation;}
	public String getDefinitionFr() {return definition;}
	
}