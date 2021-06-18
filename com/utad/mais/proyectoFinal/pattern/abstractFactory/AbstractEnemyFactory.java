package com.utad.mais.proyectoFinal.pattern.abstractFactory;

import com.utad.mais.proyectoFinal.pattern.strategy.*;
import com.utad.mais.proyectoFinal.personajes.*;
public interface AbstractEnemyFactory 
{
	//Método que permite instanciar un enemigo de tipo murciélago vil
	public FelBat createFelBat(ActivityStrategy strategy);
	//Método que permite instanciar un enemigo de tipo Infernal
	public Infernal createInfernal(ActivityStrategy strategy);
	//Método que permite instanciar un enemigo de tipo Inquisidor
	public Inquisitor createInquisitor(ActivityStrategy strategy);
	//Factory method que permite instanciar enemigos base de forma aleatoria
	public Enemy createEnemy(ActivityStrategy strategy);
	//Método que permite instanciar un enemigo de tipo Boss
	public Boss createBoss(ActivityStrategy strategy);
}
