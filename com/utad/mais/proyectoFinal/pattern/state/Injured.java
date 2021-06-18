package com.utad.mais.proyectoFinal.pattern.state;

import com.utad.mais.proyectoFinal.interfazGrafica.InterfazGrafica;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.personajes.*;

//Estado de personaje "herido"
public class Injured implements HealthTransitions
{
	//Atributo personaje al cual le añadiremos un estado
	private Personaje player;
	
	//Constructor del estado
	public Injured(Personaje player) {
		this.player = player;
	}
	//Función adicional que utilizaremos para la probabilidad de ser paralizado
	public static double getRandomDoubleBetweenRange(double min, double max){
		return ( Math.random()*(max-min) )+min;
	}
	//Transición del estado "herido" al estado "saludable"
	public void healthyTransition() {
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getHealthyState());
		//Aumentamos las estadísticas del personaje a sus niveles predeterminados
		this.player.addAgilidad(+25);
		this.player.addFuerza(+50);
	}
	//Transición que informa que ya estamos en el estado "herido"
	public void injuredTransition() {
		throw new IllegalStateException("Already injured");
	}
	//Transición del estado "herido" al estado "muy herido"
	public void severelyInjuredTransition() {
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getSeverelyInjuredState());
		//Reducimos las estadísticas del personaje
		this.player.addAgilidad(-25);
		this.player.addFuerza(-25);
	}
	//Transición del estado "saludable" al estado "muerto"
	public void deadTransition() {
		//Cambiamos el estado actual del personaje
		this.player.setActualState(this.player.getDeadState());
		//Si el personaje es el jugador, terminamos el juego
		if(this.player instanceof Jugador)
		{
			GameController.getInstance().gameOver();
		}
		//Si no es un jugador, informamos al jugador que el enemigo ha muerto, y generamos otro enemigo
		else 
		{
			InterfazGrafica.getInstance().añadirTextoLog(this.player.getName()+" pasó a mejor vida\n");
			GameController.addEnemigoMatado();
			GameController.restaEnemyNumber();
			//Añadimos puntos en función de si el enemigo matado es un Jefe o no
			if(this.player instanceof Boss)
			{
				GameController.addPuntosTotales(100);
			}
			else
			{
				GameController.addPuntosTotales(10);
			}
		}
	}
	//Transición del estado "herido" a "paralizado"
	public void paralyzedTransition() 
	{
		//Cambiamos el estado actual del jugador a "paralizado"
		this.player.setActualState(this.player.getParalyzedState());
	}
	//Función que procesará cada ronda para ver si se tiene que transicionar a otro estado
	public void process() 
	{
		//ejecutamos la función auxiluar para calcular una probabilidad
		double experimentoAleatorio = getRandomDoubleBetweenRange(0, 100);
		//Si los puntos de vida han bajado por debajo de un punto, transición al estado "muerto"
		if(this.player.getVida() < 1) 
		{
			System.out.println("I am dead");
			this.deadTransition();
		}
		//Si los puntos de vida bajan suben hasta los 125 puntos o más de salud, transición al estado "saludable"
		else if(this.player.getVida() >= 125) 
		{
			System.out.println("Health recovered");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().añadirTextoStats("Health recovered\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("Health recovered\n");
			this.healthyTransition();
		}
		//Si los puntos de salud se reducen hasta los 50 puntos, transición al estado "muy herido"
		else if(this.player.getVida() < 50) 
		{
			System.out.println("I am severely injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().añadirTextoStats("I am severely injured\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("I am severely injured\n");
			this.severelyInjuredTransition();
		}
		//Probabilidad del 50%
		//Caso paralizado
		else if(experimentoAleatorio < 50) 
		{
			System.out.println("Paralyzed");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().añadirTextoStats("Paralyzed\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("Paralyzed\n");
			this.paralyzedTransition();
		}
		//Caso en el que no se paraliza al personaje
		else 
		{
			System.out.println("Still injured");
			if(this.player instanceof Jugador)
				InterfazGrafica.getInstance().añadirTextoStats("Still injured\n");
			else
				InterfazGrafica.getInstance().añadirTextoEnemy("Still injured\n");
		}
	}
	
}
