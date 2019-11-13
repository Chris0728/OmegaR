

import java.util.Scanner;

//This is more of a proof of concept class
public abstract class ArticleBuilder {
	
	public ArticleBuilder() {}
	
	//General Methods
	public static Article createArticle(Scanner scan) {
		Article article = new Article();
		
		System.out.printf("URL:\n");
		article.setURL(scan.nextLine());
		
		System.out.printf("\nTitle: \n");
		article.setTitle(scan.nextLine());
		
		System.out.printf("\nSubject: \n");
		article.setSubject(scan.nextLine());
		
		System.out.printf("\nDescription: \n");
		article.setDescription(scan.nextLine());
		
		System.out.printf("\nAuthor: \n");
		article.setAuthor(scan.nextLine());
		
		return article;
	}

}
