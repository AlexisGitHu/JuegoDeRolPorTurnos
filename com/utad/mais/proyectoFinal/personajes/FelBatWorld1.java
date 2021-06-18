package com.utad.mais.proyectoFinal.personajes;

import com.utad.mais.proyectoFinal.pattern.strategy.*;

public class FelBatWorld1 extends FelBat
{
	/*
	 * Clase concreta del enemigo tipo FelBat mundo 1.
	 * 
	 * Se encarga incrementar las estad�sticas base que recibe  en la instanciaci�n por un factor de complejidad.
	 * 
	 * Aunque presenta un constructor por defecto, en la instanciaci�n por parte del patr�n abstract factory se utiliza el constructor
	 * por par�metros.
	 * 
	 * Guarda una referencia al tipo de mundo al que debe pertenecer. (Atributo world).
	 * 
	 * Este factor var�a seg�n el tipo de mundo.
	 * 
	 * La implementaci�n de sus m�todos son llamadas expl�citas al su clase padre.
	 */
	private World world;
	
	public FelBatWorld1()
	{
		this(new OffensiveStrategy());
	}
	public FelBatWorld1(ActivityStrategy strategy)
	{
		super(strategy);
		this.setWorld(World.LEVEL1);
	}
	public FelBatWorld1(Integer fuerza, Integer agilidad, Integer intelecto, Integer vida,ActivityStrategy strategy)
	{
		super((int)(fuerza*World.LEVEL1.getFactorComplejidad()),
				(int)(agilidad*World.LEVEL1.getFactorComplejidad()),
				(int)(intelecto*World.LEVEL1.getFactorComplejidad()),
				(int)(vida*World.LEVEL1.getFactorComplejidad()),strategy);
		this.setWorld(World.LEVEL1);
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
		return  super.getVida();
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}


}
