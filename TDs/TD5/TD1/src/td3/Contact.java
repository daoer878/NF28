package td3;

public class Contact {
	private String Nom;
	private String Mail;
	private String Icone;

	public Contact(String nom, String mail, String icone) {
		Nom = nom;
		Mail = mail;
		Icone = icone;
	}
	
	public Contact(){
		Nom = "new contact";
		Mail = "mail";
		Icone = "icone";
	}
	
	public void setNom(String nom) {
		Nom = nom;
	}
	
	public void setMail(String mail) {
		Mail = mail;
	}
	
	public void setIcone(String icone) {
		if (icone != null) {
			Icone = icone.replaceAll("\\s+","");
		}
	}
	public String getXML() {
		return "<contact>\n"+
				" <nom>"+Nom+"</nom>\n"+
				" <mail>"+Mail+"</mail>\n"+
				" <icone>"+Icone+"</icone>\n" +
				"</contact>\n";
	}
	public String toString(){
		return Nom;
	}
	
	public String getNom() {
		return Nom;
	}
	
	public String getIcone() {
		return Icone;
	}
	
	public String getMail() {
		return Mail;
	}
	
}
