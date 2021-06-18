package com.utad.mais.proyectoFinal.pattern.decorator;

public class Equipamiento implements ArmaComponent //Componente base
{
	//Por tanto el componente base no afectará en ninguna estadísitca
	private Integer fuerza = 0;
	private Integer agilidad = 0;
	private Integer intelecto = 0;
	
	public Equipamiento()
	{
		super();
	}
	
	//Método para ver su descripcion
	@Override
	public String getDesc() 
	{
		return "Equipamiento";
	}

	//La forma en la que afectará a su estadística 
	@Override
	public Integer getStat() 
	{
		return 0;
	}

	//GETTERS Y SETTERS
	@Override
	public Integer getFuerza() 
	{
		return fuerza;
	}

	@Override
	public Integer getAgilidad() 
	{
		return this.agilidad;
	}

	@Override
	public Integer getIntelecto() 
	{
		return this.intelecto;
	}

	@Override
	public void setFuerza(Integer stat)
	{
		this.fuerza = stat;
	}

	@Override
	public void setAgilidad(Integer stat)
	{
		this.agilidad = stat;
	}

	@Override
	public void setIntelecto(Integer stat)
	{
		this.intelecto = stat;
	}
	//Terminan los GETTERS y SETTERS

	//El método de devolver el equipamento para quitar el equipamiento base será él mismo
	@Override
	public ArmaComponent removeArma(ArmaComponent arma) 
	{
		return this;
	}
}
