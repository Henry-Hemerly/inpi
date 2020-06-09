package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MarcasData {

	private StringProperty marcas;
	
	public MarcasData(String marcas)
	{
		this.marcas = new SimpleStringProperty(marcas);
	}

	public StringProperty getMarcasStringProperty() {
		return marcas;
	}

	public void setMarcas(StringProperty marcas) {
		this.marcas = marcas;
	}
	
	public String getMarcas(){
		return marcas.get();
	}
	
	public void setMarcas(String value){
		marcas.set(value);
	}
}
