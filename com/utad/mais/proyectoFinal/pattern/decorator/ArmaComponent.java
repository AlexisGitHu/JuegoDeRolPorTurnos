package com.utad.mais.proyectoFinal.pattern.decorator;

public interface ArmaComponent 
{
	public String getDesc(); //Metodo para ver la descripcion
	public Integer getStat(); //Metodo para ver en cu�nto afecta a su estd�stica
	
	//Metodos relacionados con las estad�sticas
	public Integer getFuerza(); 
	public Integer getAgilidad();
	public Integer getIntelecto();
	public void setFuerza(Integer stat);
	public void setAgilidad(Integer stat);
	public void setIntelecto(Integer stat);
	
	//M�todo para quitar equipacion en el caso de que sea necesario
	public ArmaComponent removeArma(ArmaComponent arma);
}
