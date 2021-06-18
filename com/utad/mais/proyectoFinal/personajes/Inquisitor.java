package com.utad.mais.proyectoFinal.personajes;

import com.utad.mais.proyectoFinal.pattern.strategy.*;
public abstract class Inquisitor extends Enemy
{
	/*
	 * Clase abstracta Inquisitor. 
	 * 
	 * Hereda de la clase Enemy al ser un enemigo al que se enfrenta el jugador.
	 * 
	 * Como cada Inquisitor de los distintos mundos es diferente, pero guardan características en común, 
	 * se utiliza esta clase abstracta como base en la instanciación de dichos enemigos. 
	 * 
	 * Presenta un constructor por defecto con las estadísticas base, 
	 * aunque el utilizado por las factorías es el constructor por parámetros.
	 * 
	 * Sus métodos son llamadas a su clase padre para que devuelva los valores correspondientes. 
	 * 
	 * Los valores por defecto vienen descritos en la documentación.
	 * 
	 */
	public Inquisitor(ActivityStrategy strategy)
	{
		super(10,20,300,50,300,200,"Inquisitor",Caracteristicas.VOLUNTADINQUISIDOR,strategy);
	}
	public Inquisitor(Integer fuerza, Integer agilidad, Integer intelecto, Integer vida,ActivityStrategy strategy)
	{
		super(fuerza,agilidad,intelecto,0,0,vida,"Inquisitor",Caracteristicas.VOLUNTADINQUISIDOR,strategy);
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
