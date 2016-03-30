/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdict;

public class VietAnhDict extends Dict{
    private final String FILE_PATH = "Viet_Anh.xml";
    private static Dict dict;
    
    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }
    
    public static Dict getInstance() {
        if(dict == null)
            dict = new VietAnhDict();
        return dict;
    }
}
