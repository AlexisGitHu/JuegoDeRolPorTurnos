package com.utad.mais.proyectoFinal.test;

import com.utad.mais.proyectoFinal.pattern.singleton.*;

public class Test
{
	public static void main(String[] args)
	{
		GameController game = GameController.getInstance();
		game.play();
	}
}
