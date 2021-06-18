package com.utad.mais.proyectoFinal.pattern.strategy;
import com.utad.mais.proyectoFinal.personajes.Personaje;

public interface ActivityStrategy
{
	/*
	 * Interfaz más abstracta del patrón strategy. 
	 * 
	 * Permite que se pueda aplicar las acciones de la estrategia que corresponda en cada caso.
	 * Como las estrategias se van a utilizar para atacar y ser atacado, es necesario pasarles por parámetro a ambos personajes.
	 */
	public void applyStrategy(Personaje personaje1, Personaje personaje2); 
}
