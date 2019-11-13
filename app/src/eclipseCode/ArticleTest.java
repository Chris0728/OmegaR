package backend_classes;

import java.util.Scanner;

public class ArticleTest {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		Article article = ArticleBuilder.createArticle(scan);
		
		article.print();
		
		scan.close();
	}

}
