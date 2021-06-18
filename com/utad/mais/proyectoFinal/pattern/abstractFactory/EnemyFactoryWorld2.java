package com.utad.mais.proyectoFinal.pattern.abstractFactory;

import com.utad.mais.proyectoFinal.pattern.decorator2.FactoryComponent;
import com.utad.mais.proyectoFinal.pattern.decorator2.MultiplicadorBase;
import com.utad.mais.proyectoFinal.pattern.singleton.GameController;
import com.utad.mais.proyectoFinal.pattern.strategy.ActivityStrategy;
import com.utad.mais.proyectoFinal.personajes.*;
public class EnemyFactoryWorld2 implements AbstractEnemyFactory {

	public static EnemyFactoryWorld2 enemyFactoryWorld2 = new EnemyFactoryWorld2(); //Estancia singleton de la factoría
	private FactoryComponent factoryComponent; //decorador de la factoría
	private EnemyFactoryWorld2()
	{
		this.factoryComponent=new MultiplicadorBase();
	}
	public static EnemyFactoryWorld2 getInstance()
	{
		return enemyFactoryWorld2;
	}
	//Método que permite instanciar un Murciélago vil del mundo 2
	@Override
	public FelBat createFelBat(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new FelBatWorld2(100+this.factoryComponent.getFuerza(),200+this.factoryComponent.getAgilidad(),
				0+this.factoryComponent.getIntelecto(),200+this.factoryComponent.getVida(),strategy);
		
	}
	//Método que permite instanciar un Infernal del mundo 2
	@Override
	public Infernal createInfernal(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new InfernalWorld2(600+this.factoryComponent.getFuerza(),10+this.factoryComponent.getAgilidad(),
				0+this.factoryComponent.getIntelecto(),800+this.factoryComponent.getVida(),strategy);
	}
	//Método que permite instanciar un Inquisidor del mundo 1
	@Override
	public Inquisitor createInquisitor(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		return new InquisitorWorld2(10+this.factoryComponent.getFuerza(),20+this.factoryComponent.getAgilidad(),
				300+this.factoryComponent.getIntelecto(),200+this.factoryComponent.getVida(),strategy);
	}
	//Método que permite instanciar un Boss del mundo 2
	public Boss createBoss(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		GameController.addEnemy();
		return new BossWorld2(200+this.factoryComponent.getFuerza(),200+this.factoryComponent.getAgilidad(),
				200+this.factoryComponent.getIntelecto(),1200+this.factoryComponent.getVida(),strategy);
	}
	//Factory method que permite instanciar enemigos de forma aleatoria
	@Override
	public Enemy createEnemy(ActivityStrategy strategy) {
		// TODO Auto-generated method stub
		double random = Math.random();
		GameController.addEnemy();
		if(random>=0.5)
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
