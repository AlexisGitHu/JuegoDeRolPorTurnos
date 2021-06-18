package com.utad.mais.proyectoFinal.pattern.abstractFactory;

import com.utad.mais.proyectoFinal.pattern.decorator2.*;
import com.utad.mais.proyectoFinal.pattern.singleton.GameController;
import com.utad.mais.proyectoFinal.pattern.strategy.ActivityStrategy;
import com.utad.mais.proyectoFinal.personajes.*;
public class EnemyFactoryWorld1 implements AbstractEnemyFactory
{
	public static EnemyFactoryWorld1 enemyFactoryWorld1 = new EnemyFactoryWorld1(); //Estancia singleton
	private FactoryComponent factoryComponent;//decorador de la factoría
	
	//Constructor que siempre inicializa el decorador al objeto base
	private EnemyFactoryWorld1()
	{
		this.factoryComponent=new MultiplicadorBase();
	}
	public static EnemyFactoryWorld1 getInstance()
	{
		return enemyFactoryWorld1;
	}
	//Método que permite instanciar un Murciélago vil del mundo 1
	@Override
	public FelBat createFelBat(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new FelBatWorld1(100+this.factoryComponent.getFuerza(),200+this.factoryComponent.getAgilidad(),
				10+this.factoryComponent.getIntelecto(),200+this.factoryComponent.getVida(),strategy);
		
	}
	//Método que permite instanciar un Infernal del mundo 1
	@Override
	public Infernal createInfernal(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new InfernalWorld1(600+this.factoryComponent.getFuerza(),10+this.factoryComponent.getAgilidad(),
				10+this.factoryComponent.getIntelecto(),800+this.factoryComponent.getVida(),strategy);
	}
	//Método que permite instanciar un Inquisidor del mundo 1
	@Override
	public Inquisitor createInquisitor(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new InquisitorWorld1(10+this.factoryComponent.getFuerza(),20+this.factoryComponent.getAgilidad(),
				300+this.factoryComponent.getIntelecto(),200+this.factoryComponent.getVida(),strategy);
	}
	//Método que permite instanciar un Boss del mundo 1
	@Override
	public Boss createBoss(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		GameController.addEnemy();
		return new BossWorld1(200+this.factoryComponent.getFuerza(),200+this.factoryComponent.getAgilidad(),
				200+this.factoryComponent.getIntelecto(),1200+this.factoryComponent.getVida(),strategy);
	}
	//Factory method que permite instanciar de forma aleatoria los tres enemigos base
	@Override
	public Enemy createEnemy(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		double random1 = Math.random();
		GameController.addEnemy();
		if(random1>=0.3)
		{
			return this.createFelBat(strategy);
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
