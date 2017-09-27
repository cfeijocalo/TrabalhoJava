package org.ufpr.sistemapedidos.util;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilidades {
	
	public static StringProperty converteLocalDateToStringProperty(LocalDate date) {
		
		StringProperty data;
		
		data = new SimpleStringProperty((date.getDayOfMonth() > 9 ? "" + date.getDayOfMonth() : "0" + date.getDayOfMonth()) 
				+ "/" + (date.getMonthValue() > 9 ? "" + date.getMonthValue() : "0" + date.getMonthValue()) 
				+ "/" + date.getYear());
		
		return data;
	}
	
	public static String converteLocalDateToString(LocalDate date) {
		
		String data;
		
		data = (date.getDayOfMonth() > 9 ? "" + date.getDayOfMonth() : "0" + date.getDayOfMonth()) 
				+ "/" + (date.getMonthValue() > 9 ? "" + date.getMonthValue() : "0" + date.getMonthValue()) 
				+ "/" + date.getYear();
		
		return data;
	}

}
