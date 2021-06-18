package com.utad.mais.proyectoFinal.pattern.template;

import com.utad.mais.proyectoFinal.pattern.abstractFactory.*;
import com.utad.mais.proyectoFinal.pattern.singleton.GameController;
import com.utad.mais.proyectoFinal.pattern.strategy.*;
import com.utad.mais.proyectoFinal.personajes.World;

public class RandomProbabilityGeneratorFair extends RandomProbabilityGenerator 
{

	/*
	 * Clase del patrón template method.
	 * 
	 * Hereda de la clase abstracta RandomProbabilityGenerator y 
	 * se encarga de definir de forma específica los métodos del flujo de ejecución del método applyTemplateMethod.
	 * 
	 * El objetivo de esta clase es devolver aleatoriamente, según las condiciones del mundo, 
	 * las estrategias que van a utilizar los enemigos que se vayan instanciando.
	 * 
	 * Las probabilidades asignadas están descritas en la documentación, así como el tipo de estrategia que devuelven.
	 * 
	 * Se denomina generador "justo" porque las estrategias que devuelven son las menos agresivas de cara al jugador.
	 */
	@Override
	protected World checkWorld() {
		// TODO Auto-generated method stub
		if(GameController.getInstance().getAbstractFactory().getClass().equals(EnemyFactoryWorld1.class))
		{
			return World.LEVEL1;
		}
		else if(GameController.getInstance().getAbstractFactory().getClass().equals(EnemyFactoryWorld2.class))
		{
			return World.LEVEL2;
		}
		else
		{
			return World.LEVEL3;
		}
	}

	@Override
	protected Double generateProbability(World world) {
		// TODO Auto-generated method stub
		Double probability;
		if(world==World.LEVEL1)
		{
			probability=0.1;
		}
		else if(world==World.LEVEL2)
		{
			probability=0.15;
		}
		else
		{
			probability=0.2;
		}
		return probability;
	}

	@Override
	protected ActivityStrategy generateStrategy(Double probability) {
		// TODO Auto-generated method stub
		Double probability2 = Math.random()-probability;
		if(probability2>=0.7)
		{
			return new DefensiveStrategy();
			
		}
		else if(probability2<0.7 && probability2>=0.5)
		{
			return new HealingStrategy();
		}
		else if(probability2<0.5 && probability2>=0.3)
		{
			return new OffensiveStrategy();
		}
		else
		{
			return new BuffStrategy();
		}
	}

}
