package com.utad.mais.proyectoFinal.pattern.strategy;

import com.utad.mais.proyectoFinal.pattern.singleton.Calculadora;
import com.utad.mais.proyectoFinal.personajes.*;

public class BuffStrategy implements EnemyStrategy
{
	/*
	 * Clase estrategia de incremento de estadísticas. 
	 * 
	 * Todos los enemigos que la implemente se les permitirá realizar las acciones descritas en la documentación,
	 * ya que las estrategias difieren a la hora de las acciones que puede realizar el enemigo.
	 * 
	 * El método applyStrategy se utiliza para aplicar la acción de forma aleatoria.
	 * Las probabilidades están descritas en la documentación.
	 * 
	 * El resto de métodos llaman a la clase Calculadora que es la encargada de modificar las estadísticas de los personajes
	 * según la acción realizada y de escribir por pantalla dicha acción.
	 * 
	 * Los métodos que no permiten ser usados por la estrategia incorporan el lanzamiento de una excepción.
	 */
	@Override
	public void applyStrategy(Personaje personaje1, Personaje personaje2) 
	{
		double i = Math.random();
		if(i>=0.5)
		{
			this.buff(personaje1);
		}
		else if(i<0.5 && i>=0.3)
		{
			this.attack(personaje1,personaje2);
		}
		else
		{
			this.heal(personaje1);
		}
	}

	@Override
	public void attack(Personaje personaje1, Personaje personaje2) 
	{
		System.out.println(personaje1.getName()+" atacó a "+personaje2.getName());
		Calculadora.getInstance().calculaDañoRecibido(personaje1, personaje2);	
	}

	@Override
	public void defend(Personaje personaje1,Personaje personaje2)
	{
		throw new IllegalStateException("Defend is not available");	
	}

	@Override
	public void heal(Personaje personaje1) 
	{
		System.out.println(personaje1.getName()+" se curó así mimsmo");
		Calculadora.getInstance().calculaCuraRecibida(personaje1);
	}

	@Override
	public void buff(Personaje personaje1)
	{
		// TODO Auto-generated method stub
		System.out.println(personaje1.getName()+" se buffó a sí mismo");
		Calculadora.getInstance().calculaBuffado(personaje1);
	}

	@Override
	public void activarCaracteristica(Personaje personaje1,Personaje personaje2) 
	{
		throw new IllegalStateException("Característica is not available");
	}
}
