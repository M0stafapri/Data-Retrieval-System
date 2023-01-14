/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir_system;

import java.io.IOException;
import java.util.Scanner;

public class IR_System {


    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your query: ");
        String query = scanner.nextLine();
        
        // Note it must be double backslash \\
        // Enter files paths instead of Stars
        FileTokenizer f1 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\1.txt",query);
        FileTokenizer f2 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\2.txt",query);
        FileTokenizer f3 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\3.txt",query);
        FileTokenizer f4 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\4.txt",query);
        FileTokenizer f5 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\5.txt",query);
        FileTokenizer f6 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\6.txt",query);
        FileTokenizer f7 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\7.txt",query);
        FileTokenizer f8 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\8.txt",query);
        FileTokenizer f9 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\9.txt",query);
        FileTokenizer f10 = new FileTokenizer("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\10.txt",query);
      
     Scanner scanner1 = new Scanner(System.in);
     System.out.println("Enter a term: ");
     String term = scanner1.nextLine(); 
      
      
    VectorSpaceModel a = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\1.txt");
    VectorSpaceModel b = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\2.txt");
    VectorSpaceModel c = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\3.txt");
    VectorSpaceModel d = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\4.txt");
    VectorSpaceModel e = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\5.txt");
    VectorSpaceModel f = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\6.txt");
    VectorSpaceModel g = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\7.txt");
    VectorSpaceModel h = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\8.txt");
    VectorSpaceModel i = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\9.txt");
    VectorSpaceModel j = new VectorSpaceModel("C:\\Users\\asus\\Desktop\\Level 3 Projects First term\\IR_System\\10.txt");
    
    a.PrintScores(term);
    b.PrintScores(term);
    c.PrintScores(term);
    d.PrintScores(term);
    e.PrintScores(term);
    f.PrintScores(term);
    g.PrintScores(term);
    h.PrintScores(term);
    i.PrintScores(term);
    j.PrintScores(term);
   
   System.out.println("IDF of the term in the 10 files ="+a.idf(term)+"\n");
   
  Scanner scanner2 = new Scanner(System.in);
   System.out.println("Enter your query: ");
   String query2 = scanner2.nextLine(); 
   
   a.Similarity(query2);
   b.Similarity(query2);
   c.Similarity(query2);
   d.Similarity(query2);
   e.Similarity(query2);
   f.Similarity(query2);
   g.Similarity(query2);
   h.Similarity(query2);
   i.Similarity(query2);
   j.Similarity(query2);
   

   
    }
    
}
