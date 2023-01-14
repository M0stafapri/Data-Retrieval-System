/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir_system;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author Ahmed Shoeb
 */
public class FileTokenizer {
    /** AtomicIntegers >> compare and swap (mutable) are containers for those values.
    You can read and set them. Same as assigning a value to variable. **/
    private static AtomicInteger nextId = new AtomicInteger();
    private static int id=1;
    public static int size=0;
    /* 1)FileInputStream is meant for reading streams of raw bytes such as image data.
     For reading streams of characters, consider using FileReader */
    private  FileInputStream inFile;
    /* 2)InputStreamReader is a bridge from byte streams to character streams.
    It reads bytes and decodes them into characters using a specified charset.*/
    private  InputStreamReader inReader;
    /* 3) BufferedReader is a Java class to reads the text from an Input stream*/
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
    public  ArrayList<ArrayList<Integer>> Terms_Index = new ArrayList<ArrayList<Integer>>();
    public  ArrayList<Integer> arr; 
    public int check=0;
    
    
    public FileTokenizer(String s,String q)throws IOException{
    id = nextId.incrementAndGet();
    initFile(s);
    Tokenization();
    StopWordsRemoval();
    PositionalIndex();
    check(q);
    inFile.close();
    
    }
    
    
    
    public  void initFile(String FilePath) throws IOException{
        inFile = new FileInputStream(FilePath);
        inReader = new InputStreamReader(inFile);
        reader = new BufferedReader(inReader);
        //the whole class is used to read from our text files
    }
    
    public  void Tokenization() throws IOException{
    line = reader.readLine();
    // the tokenizer gets the line and [\t ; : . , \\ ! ? ] means that
    // it will saperate every token if the tokenizer found one of those 
    // for example if the tokenizer found (\t for tap or space) or (. dot)
    // it will saperate the word "token" and so on for the rest of them
    StrTkn = new StringTokenizer(line,"[\t ; : . , \\ ! ? ]");
    // for loop to get tokens and put them in the (arraylist "TokensArray")
    while(StrTkn.hasMoreTokens()){
    token = StrTkn.nextToken().toLowerCase();
    TokensArray.add(token);
    

    
    }
    
    
    }
    
    public  void StopWordsRemoval() throws IOException{
    /*this line adds the stopWords array that we 
    declared above to the (arraylist "StopWordsArray")*/
    StopWordsArray.addAll(Arrays.asList(StopWords));
    /*After that we remove the StopWords from the (arraylist "TokensArray"
    using the next line by passing (arraylist "StopWordsArray") into (removeAll method)*/
    TokensArray.removeAll(StopWordsArray);
      
    }
    
    public  void PositionalIndex() throws IOException{
    TokensNumber = TokensArray.size();
    int i=1;
    //for every token in TokensArray we will put the token in the (IndexedTerms arraylist)
    //and give it an index
    for(String token: TokensArray)
    {
    //if the token dosen't exist in (IndexedTerms arraylist) then we'll add the token to the arraylist (IndexedTerms)
    //note that the token may exist more than one time so to store the indexes for this 
    //for this token we declared the !!!!arraylist of Integer arraylists!!!! (Terms_Index) above
    // it's very """IMPORTANT""" as this arraylist takes only Integer arraylists
    if(!IndexedTerms.contains(token)){
    IndexedTerms.add(token);
    //we define arr arrayist to add index in it and pass the whole arraylist to
    //Terms_Index that accepts only arraylists !!!!! ""IMPORTANT""
    arr= new ArrayList<Integer>();
    arr.add(new Integer(i));
    Terms_Index.add(arr);
    }
    // if it already exists so we have to add the other index in arr then
    //pass arr to Terms_Index again
    else{
    int index=IndexedTerms.indexOf(token);
    arr = Terms_Index.get(index);
    if (!arr.contains(new Integer(i))){
    
        arr.add(new Integer(i));
        Terms_Index.set(index, arr);
    }
    }
    i++;
    }
    
        }

public  void Print() throws IOException{
    System.out.println(IndexedTerms.toString());
    System.out.println(IndexedTerms.size());
    System.out.println(Terms_Index.toString());
    System.out.println(Terms_Index.size());
    System.out.println("id: "+id);
}

public  void check(String s) throws IOException{
String query[] = s.split(" ");
//for loop to chek if ALL query terms exist or not
// if term matches existed token check++
// if check has the same number of query terms it means that all terms has been found
//then we call the search method
for(String token: IndexedTerms){
    for(String q: query){
        //equalsIgnoreCase compares two strings irrespective of the case (lower or upper) of the string.
    if (token.equalsIgnoreCase(q)){
    check++;
    }
    }
}

if(check==query.length){
search(query);
}
}

public  void search(String[] query) throws IOException{
System.out.println("-----------");
for(String token: IndexedTerms){
    size=0;
    for(String q: query){
//equalsIgnoreCase compares two strings irrespective of the case (lower or upper) of the string.
if (token.equalsIgnoreCase(q)){
int index=IndexedTerms.indexOf(token);
arr = Terms_Index.get(index);
size+= arr.size();
System.out.println("Document"+id+":"+arr);
System.out.println(q+":"+FileTokenizer.size+"\n Time(s) mentioned"+"\n");
}
}}

System.out.println("-----------");

}
    

    
}
