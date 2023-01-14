/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir_system;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VectorSpaceModel {
     static AtomicInteger nextId = new AtomicInteger();
     int id=1;
    public static int size=0;
    private  FileInputStream inFile;
    private  InputStreamReader inReader;
    private  BufferedReader reader;
    private  StringTokenizer StrTkn;
    private  String line,token;
    private  int TokensNumber;
    private  ArrayList<String> TokensArray = new ArrayList<String>();
    private  List<String> StopWordsArray = new ArrayList<>();
    private  String[] StopWords=
    {"i","he","she","it","you","we","they","be","am","is","are","was","were","my","his","her",
    "your","our","their","myself","himself","herself","itself","yourself","yourselves","ourselves",
    "themselves","me","him","her","us","them","mine","hers","its","ours","yours","theirs","has","have",
    "had","here","there","a","an","the","or","with","of","off","for","as","to","from","this","that","up",
    "down","no","all","at","on","in","under","above","below","and","over","into","but","between","about",
    "during","out","with","some","what","why","how","where","whom","when","who", "which", "while", "do",
    "does", "did", "these", "those", "after", "before","not","now","just","too","only","few","can","can’t",
    "will","won’t","than","then","more","most","should","would","may","might","if","once","very","again","own",
    "such","other","another","each","until","s","being","against","by","through","don","nor","both","any","same",
    "been","because","so","t","doing","how","further"};
    public  ArrayList<String> IndexedTerms = new ArrayList<String>();
    public static  ArrayList<String> IndexedTerms2 = new ArrayList<String>(); 
    public int check=0;
    public  ArrayList<Double> Squared_TF_IDF_doc = new ArrayList<Double>();
    public  ArrayList<Double> Normalize_docs = new ArrayList<Double>();
    public  ArrayList<Double> Squared_TF_IDF_query = new ArrayList<Double>();
    public  ArrayList<Double> Normalize_query = new ArrayList<Double>();
    public  ArrayList<Double> SimilarityArr = new ArrayList<Double>();
    
    public VectorSpaceModel(String s)throws IOException{
    id = nextId.incrementAndGet();
    initFile(s);
    Tokenization();
    StopWordsRemoval();
    TermsInput();
    inFile.close();
    
    }
    
    
    
    public  void initFile(String FilePath) throws IOException{
        inFile = new FileInputStream(FilePath);
        inReader = new InputStreamReader(inFile);
        reader = new BufferedReader(inReader);
    }
    
    public  void Tokenization() throws IOException{
    line = reader.readLine();
    StrTkn = new StringTokenizer(line,"[\t ; : . , \\ ! ? ]");
    while(StrTkn.hasMoreTokens()){
    token = StrTkn.nextToken().toLowerCase();
    TokensArray.add(token);
    }}
    
    public  void StopWordsRemoval() throws IOException{
    StopWordsArray.addAll(Arrays.asList(StopWords));
    TokensArray.removeAll(StopWordsArray);
    }
    
    
    public  void TermsInput() throws IOException{
    TokensNumber = TokensArray.size();
    for(String token: TokensArray)
    {
    IndexedTerms2.add(token);
    IndexedTerms.add(token);
    }}
    
    
public double TermFrequency(String term) {
    double result = 0;
    for (String word : IndexedTerms) {       
       if (term.equalsIgnoreCase(word))
              result++;
       }
return result;
}


public double idf(String term) {
    double n = 0;
        for (String word : IndexedTerms2) {
            if (term.equalsIgnoreCase(word)) {
                n++;
            }
        }
     return (Math.log(3/n));
}
    

public double TF_IDF(String term) {
return TermFrequency(term)*idf(term);
}

public  void PrintScores(String q) throws IOException{
   
   System.out.println("The Term Frequency of file "+id+" = "+TermFrequency(q));
   System.out.println("TF-IDF of file "+id+" = "+TF_IDF(q));
   System.out.println("\n");
   
}



public void doc_normalize(String query){
    String terms[] = query.split(" ");
    double t=0;
    
    for(String term: terms){
    t=TF_IDF(term);
    Squared_TF_IDF_doc.add(t*t);}
    
    double length=0;
    for(double m : Squared_TF_IDF_doc){
    length+=m;
    }
    for(double d: Squared_TF_IDF_doc){
    double dn = d/Math.sqrt(length);

    Normalize_docs.add(dn);
    }
}

public void query_normalize(String query){
    String terms[] = query.split(" ");
    double t=0;
    
    for(String term: terms){
    t=idf(term);
    Squared_TF_IDF_query.add(t*t);}
    
    double length=0;
    for(double m : Squared_TF_IDF_query){
    length+=m;
    }
    
    for(double d: Squared_TF_IDF_query){
    double dn = d/Math.sqrt(length);
    Normalize_query.add(dn);
    }
    
}
public void Similarity(String query){
    doc_normalize(query);
    query_normalize(query);
    for(int i=0;i<Squared_TF_IDF_doc.size();i++){
    SimilarityArr.add(Squared_TF_IDF_doc.get(i)*Squared_TF_IDF_query.get(i));
    }
    
    double Similarity=0;
    for(double d : SimilarityArr){
    Similarity+=d;
    }
 
System.out.println("The Similarity between the enterd query and file "+id+" = "+Similarity);

}


}
