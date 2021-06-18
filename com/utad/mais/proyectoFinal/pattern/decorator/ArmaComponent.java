package com.utad.mais.proyectoFinal.pattern.decorator;

public interface ArmaComponent 
{
	public String getDesc(); //Metodo para ver la descripcion
	public Integer getStat(); //Metodo para ver en cuánto afecta a su estdística
	
	//Metodos relacionados con las estadísticas
	public Integer getFuerza(); 
	public Integer getAgilidad();
	public Integer getIntelecto();
	public void setFuerza(Integer stat);
	public void setAgilidad(Integer stat);
	public void setIntelecto(Integer stat);
	
	//Método para quitar equipacion en el caso de que sea necesario
	public ArmaComponent removeArma(ArmaComponent arma);
}
