package com.utad.mais.proyectoFinal.pattern.strategy;

import com.utad.mais.proyectoFinal.personajes.Personaje;

public interface EnemyStrategy extends ActivityStrategy 
{
	/*
	 * Interfaz del patrón strategy, se encuentra en un nivel de abstracción menor que ActivityStrategy.
	 * 
	 * Implementa los métodos necesarios para permitir a los personajes realizar las diferentes acciones del juego.
	 * Como las estrategias se van a utilizar para atacar y ser atacado, curarse, defenderse, incrementar sus estadísticas o activar 
	 * su característica especial, es necesario pasarles por parámetro a ambos personajes o al solo a uno de ellos,
	 * dependiendo del caso.
	 */
	public void attack(Personaje personaje1, Personaje personaje2);//Pasar por parámetro al enemy o al personaje?
	public void defend(Personaje personaje1,Personaje personaje2);
	public void heal(Personaje personaje1);
	public void buff(Personaje personaje1);
	public void activarCaracteristica(Personaje personaje1, Personaje personaje2);
}
