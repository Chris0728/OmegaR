package backend_classes;

public class Article {
	
	private String url;
	private String title;
	private String subject;
	private String description;
	private String author;
	
	public Article() {}
	
	//Setters
	public void setURL(String url) {
		this.url  = url;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setAuthor(String name) {
		this.author = name;
	}
	//
	
	
	//Getters
	public String getURL() {
		return this.url;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getAuthor() {
		return this.author;
	}
	//
	
	
	//General Methods
	public void print() {
		System.out.println();
		System.out.println(this.url);
		System.out.println(this.title);
		System.out.println(this.subject);
		System.out.println(this.description);
		System.out.println(this.author);
	}
	
	//

}
