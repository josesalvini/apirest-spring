package com.tipre.libreria.apiserver.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
	
	private ArrayList<HashMap<String, String>> metaData = new ArrayList<>();
	
	public ArrayList<HashMap<String, String>> getMetaData(){
		return metaData;
	}
	
	public void setMetaData(String tipo, String codigo,String date) {
		HashMap<String, String> data = new HashMap<String, String>();
		
		data.put("tipo", tipo);
		data.put("codigo", codigo);
		data.put("dato", date);
		
		metaData.add(data);
	}
	
	
	
	
	
	

}
