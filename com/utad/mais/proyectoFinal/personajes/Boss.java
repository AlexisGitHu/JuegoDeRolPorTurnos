package com.utad.mais.proyectoFinal.personajes;

import com.utad.mais.proyectoFinal.pattern.strategy.ActivityStrategy;

public abstract class Boss extends Enemy
{
	/*
	 * Clase abstracta Boss. 
	 * 
	 * Hereda de la clase Enemy al ser un enemigo al que se enfrenta el jugador.
	 * 
	 * Como cada Boss de los distintos mundos es diferente, pero guardan caracter�sticas en com�n, 
	 * se utiliza esta clase abstracta como base en la instanciaci�n de dichos enemigos. 
	 * 
	 * Presenta un constructor por defecto con las estad�sticas base, 
	 * aunque el utilizado por las factor�as es el constructor por par�metros.
	 * 
	 * Sus m�todos son llamadas a su clase padre para que devuelva los valores correspondientes. 
	 * 
	 * Los valores por defecto vienen descritos en la documentaci�n.
	 * 
	 */
	public Boss(ActivityStrategy strategy)
	{
		super(200,200,200,150,0,1200,"Boss",Caracteristicas.BOSSFACTOR,strategy);
	}
	public Boss(Integer fuerza, Integer agilidad, Integer intelecto, Integer vida,ActivityStrategy strategy)
	{
		super(fuerza,agilidad,intelecto,0,0,vida,"Boss",Caracteristicas.BOSSFACTOR,strategy);
	}
	public Integer getFuerza()
	{
		return super.getFuerza();
	}
	public Integer getAgilidad()
	{
		return super.getAgilidad();
	}
	public Integer getIntelecto()
	{
		return super.getIntelecto();
	}
	public Integer getResistenciaMagica()
	{
		return super.getResistenciaMagica();
	}
	public Integer getResistenciaFisica()
	{
		return super.getResistenciaFisica();
	}
	public Integer getVida()
	{
		return super.getVida();
	}
	public void setVida(Integer vida)
	{
		super.setVida(vida);
	}
	@Override
	public Caracteristicas getCaracteristica() {
		// TODO Auto-generated method stub
		return super.getCaracteristica();
	}
}
