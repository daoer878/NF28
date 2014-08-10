package td2;

public class Mots {
	private String key;
	private String definition;
	private String traslation;
	
	public Mots(String key, String def, String trans) {
		this.key = key;
		this.definition = def;
		this.traslation = trans;
	}
	public String toString(){return key;}
	public String getKey(){return key;}
	public String getDefinition(){return definition;}
	public String getTranslation(){return traslation;}

	
	
}