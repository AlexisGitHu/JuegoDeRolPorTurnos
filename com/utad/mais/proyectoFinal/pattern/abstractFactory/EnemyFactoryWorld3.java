package com.utad.mais.proyectoFinal.pattern.abstractFactory;

import com.utad.mais.proyectoFinal.pattern.decorator2.*;
import com.utad.mais.proyectoFinal.pattern.singleton.GameController;
import com.utad.mais.proyectoFinal.pattern.strategy.ActivityStrategy;
import com.utad.mais.proyectoFinal.personajes.*;
public class EnemyFactoryWorld3 implements AbstractEnemyFactory {

	public static EnemyFactoryWorld3 enemyFactoryWorld3 = new EnemyFactoryWorld3(); //Estancia singleton de la factoría
	private FactoryComponent factoryComponent; //Decorador de la factoría
	//Constructor que instancia siempre el decorador de la factoría con un objeto base
	private EnemyFactoryWorld3()
	{
		this.factoryComponent=new MultiplicadorBase();
	}
	public static EnemyFactoryWorld3 getInstance()
	{
		return enemyFactoryWorld3;
	}
	//Método que permite instanciar un Murciélago vil del mundo 2
	@Override
	public FelBat createFelBat(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new FelBatWorld3(100+this.factoryComponent.getFuerza(),200+this.factoryComponent.getAgilidad(),
				0+this.factoryComponent.getIntelecto(),200+this.factoryComponent.getVida(),strategy);
		
	}
	//Método que permite instanciar un Infernal del mundo 2
	@Override
	public Infernal createInfernal(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new InfernalWorld3(600+this.factoryComponent.getFuerza(),10+this.factoryComponent.getAgilidad(),
				0+this.factoryComponent.getIntelecto(),800+this.factoryComponent.getVida(),strategy);
	}
	//Método que permite instanciar un Inquisidor del mundo 3
	@Override
	public Inquisitor createInquisitor(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new InquisitorWorld3(10+this.factoryComponent.getFuerza(),20+this.factoryComponent.getAgilidad(),
				300+this.factoryComponent.getIntelecto(),200+this.factoryComponent.getVida(),strategy);
	}
	//Método que permite instanciar un Boss del mundo 3
	public Boss createBoss(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		GameController.addEnemy();
		return new BossWorld3(200+this.factoryComponent.getFuerza(),200+this.factoryComponent.getAgilidad(),
				200+this.factoryComponent.getIntelecto(),1200+this.factoryComponent.getVida(),strategy);
	}
	//Factory method que permite instanciar enemigos de forma aleatoria
	@Override
	public Enemy createEnemy(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		double random = Math.random();
		GameController.addEnemy();
		if(random>=0.7)
		{
			return this.createFelBat(strategy);
		}
		else if(random<0.7 && random >= 0.5)
		{
			return this.createInfernal(strategy);
		}
		else
		{
			return this.createInquisitor(strategy);
		}
	}
	public void setFactoryComponent(FactoryComponent factoryComponent)
	{
		this.factoryComponent=factoryComponent;
	}
	public FactoryComponent getFactoryComponent()
	{
		return this.factoryComponent;
	}

}
