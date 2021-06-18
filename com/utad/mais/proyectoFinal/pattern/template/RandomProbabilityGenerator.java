package com.utad.mais.proyectoFinal.pattern.template;

import com.utad.mais.proyectoFinal.pattern.strategy.ActivityStrategy;
import com.utad.mais.proyectoFinal.personajes.World;

public abstract class RandomProbabilityGenerator implements ProbabilityTemplate
{
	/*
	 * Clase abstracta del patrón template method.
	 * 
	 * Implementa la intefaz ProbabilityTemplate y 
	 * se encarga de delegar a sus clases hijas la llamada a los métodos pertinentes.
	 * 
	 * Esta clase asegura que el flujo de ejecución sea: comprobar el mundo, generar una probabilidad
	 * y generar una estrategia correspondiente a dicha probabilidad.
	 * 
	 * El objetivo del template es devolver aleatoriamente según las condiciones del mundo las estrategias que van a utilizar los enemigos
	 * que se vayan instanciando. De esta manera se consigue transparencia a la hora de instanciar las diferentes estrategias.
	 */
	public RandomProbabilityGenerator()
	{
		
	}
	public final ActivityStrategy applyTemplateMethod()
	{
		World world=this.checkWorld();
		Double probability = this.generateProbability(world);
		return this.generateStrategy(probability);
	}
	protected abstract World checkWorld();
	protected abstract Double generateProbability(World world);
	protected abstract ActivityStrategy generateStrategy(Double probability);
}
