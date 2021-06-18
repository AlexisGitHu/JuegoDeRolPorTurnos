package com.utad.mais.proyectoFinal.pattern.template;

import com.utad.mais.proyectoFinal.pattern.abstractFactory.*;
import com.utad.mais.proyectoFinal.pattern.singleton.GameController;
import com.utad.mais.proyectoFinal.pattern.strategy.*;
import com.utad.mais.proyectoFinal.personajes.World;

public class RandomProbabilityGeneratorUnfair extends RandomProbabilityGenerator
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
	 * Se denomina generador "injusto" porque las estrategias que devuelven son las más agresivas de cara al jugador,
	 * lo que aporta un incremento de dificultad al juego.
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
		if(world==World.LEVEL1)
		{
			return 0.2;
		}
		else if(world==World.LEVEL2)
		{
			return 0.3;
		}
		else
		{
			return 0.4;
		}
	}

	@Override
	protected ActivityStrategy generateStrategy(Double probability) {
		// TODO Auto-generated method stub
		Double probability2 = Math.random()-probability;
		if(probability2>=0.7)
		{
			return new BuffStrategy();
		}
		else if(probability2<0.7 && probability2>=0.5)
		{
			return new DefensiveStrategy();
		}
		else if(probability2<0.5 && probability2>=0.3)
		{
			
			return new HealingStrategy();
		}
		else
		{
			return new OffensiveStrategy();
			
		}
	}
}
