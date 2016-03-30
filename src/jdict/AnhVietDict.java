/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdict;

/**
 *
 * @author Blue
 */
public class AnhVietDict extends Dict {
    private final String FILE_PATH = "Anh_Viet.xml";  
    private static Dict dict;
    
    private AnhVietDict() {}
    
    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }
    
    public static Dict getInstance() {
        if(dict == null)
            dict = new AnhVietDict();
        return dict;
    }
}
