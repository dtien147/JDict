/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdict;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Blue
 */
public abstract class Dict {
    private final int MAX_SIMILAR_WORDS = 10;
    protected ArrayList<Record> records;
    
    protected Dict() {
        records = new ArrayList();
        load();
    }
    
    protected abstract String getFilePath();
    
    private Document loadXml() {
        try {	
            File inputFile = new File(getFilePath());
            DocumentBuilderFactory dbFactory 
               = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();                
            return doc;
        } catch (Exception e) {
           System.out.println(e);
           records = new ArrayList();
        }
        
        return null;
    }    
    
    private void load() {
        try {
            Document doc = loadXml();
            NodeList nList = doc.getElementsByTagName("record");
            for(int i = 0; i < nList.getLength(); i++) {
                Element record = (Element) nList.item(i);
                Element word = (Element) record
                        .getElementsByTagName("word").item(0);
                Element meaning = (Element) record
                        .getElementsByTagName("meaning").item(0);
                records.add(new Record(word.getTextContent(), 
                        meaning.getTextContent()));
            }
        } catch(Exception e) {
            
        }
    }    
    
    public int getSize() {        
        return records.size();
    }
    
    public Record getItem(int index) {
        return records.get(index);
    }        
    
    public int findWord(String word) {
        for(int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            if(record.getWord().equals(word))
                return i;
        }
        
        return -1;
    }
    
    public ArrayList<String> findSimilars(String word) {
        ArrayList<String> similarWords = new ArrayList();
        for(int i = 0; i < records.size(); i++) {
            String tempWord = records.get(i).getWord();
            if(tempWord.indexOf(word) == 0)        
                similarWords.add(tempWord);
            
            if(similarWords.size() == MAX_SIMILAR_WORDS)
                return similarWords;
        }
        return similarWords;
    }
}
