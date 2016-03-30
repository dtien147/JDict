/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdict;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Blue
 */
public class Statistics implements Serializable {
    private String word;
    private Calendar time;

    public Statistics(String word) {
        this.word = word;
        this.time = (Calendar) Calendar.getInstance().clone();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Calendar getCalendar() {
        return time;
    }

    public void setCalendar(Calendar calendar) {
        this.time = calendar;
    }        
    
    public boolean isThisWeek() {
        Calendar thisTime = Calendar.getInstance();
        return (time.get(Calendar.WEEK_OF_YEAR) == thisTime.get(Calendar.WEEK_OF_YEAR)
                && time.get(Calendar.YEAR) == thisTime.get(Calendar.YEAR));
    }
    
    public boolean isThisMonth() {
        Calendar thisTime = Calendar.getInstance();
        return (time.get(Calendar.MONTH) == thisTime.get(Calendar.MONTH)
                && time.get(Calendar.YEAR) == thisTime.get(Calendar.YEAR));
    }
}
