/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdict;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class FavoriteDict extends Dict {
    private final String FILE_PATH = "favorite.xml";
    private static FavoriteDict dict;
    
    private FavoriteDict() {
        File file = new File(FILE_PATH);
        if(! file.exists())
            createFavoriteFile();     
    }
    
    private void createFavoriteFile() {
        try {            
            PrintWriter output = new PrintWriter(FILE_PATH);
            StringWriter stringWriter = new StringWriter();

            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();	
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory
                    .createXMLStreamWriter(stringWriter);

            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("dictionary");            
            xMLStreamWriter.writeEndElement();
                       
            String xmlString = stringWriter.getBuffer().toString();
            stringWriter.close();
            output.printf(xmlString);
            output.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }
    
    public static FavoriteDict getInstance() {
        if(dict == null)
            dict = new FavoriteDict();
        return dict;
    }
    
    public void remove(int index) {
        records.remove(index);
    }
    
    public void add(Record record) {
        records.add(record);
    }
    
    private void recordsToXml(XMLStreamWriter xMLStreamWriter) {        
        try {
            for(int i = 0; i < records.size(); i++) {
                Record record = records.get(i);
                xMLStreamWriter.writeStartElement("record");
                xMLStreamWriter.writeStartElement("word");
                xMLStreamWriter.writeCharacters(record.getWord());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeStartElement("meaning");
                xMLStreamWriter.writeCharacters(record.getMeaning());
                xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeEndElement();
            }            
        } catch (XMLStreamException ex) {
            System.out.println(ex);
        }      
    }
    
    public void save() {
        try {            
            PrintWriter output = new PrintWriter(FILE_PATH);
            StringWriter stringWriter = new StringWriter();

            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();	
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory
                    .createXMLStreamWriter(stringWriter);

            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("dictionary");
            recordsToXml(xMLStreamWriter);
            xMLStreamWriter.writeEndElement();
                       
            String xmlString = stringWriter.getBuffer().toString();
            stringWriter.close();
            output.printf(xmlString);
            output.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
}
