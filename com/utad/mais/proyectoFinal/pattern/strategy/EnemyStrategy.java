package com.utad.mais.proyectoFinal.pattern.strategy;

import com.utad.mais.proyectoFinal.personajes.Personaje;

public interface EnemyStrategy extends ActivityStrategy 
{
	/*
	 * Interfaz del patr�n strategy, se encuentra en un nivel de abstracci�n menor que ActivityStrategy.
	 * 
	 * Implementa los m�todos necesarios para permitir a los personajes realizar las diferentes acciones del juego.
	 * Como las estrategias se van a utilizar para atacar y ser atacado, curarse, defenderse, incrementar sus estad�sticas o activar 
	 * su caracter�stica especial, es necesario pasarles por par�metro a ambos personajes o al solo a uno de ellos,
	 * dependiendo del caso.
	 */
	public void attack(Personaje personaje1, Personaje personaje2);//Pasar por par�metro al enemy o al personaje?
	public void defend(Personaje personaje1,Personaje personaje2);
	public void heal(Personaje personaje1);
	public void buff(Personaje personaje1);
	public void activarCaracteristica(Personaje personaje1, Personaje personaje2);
}
