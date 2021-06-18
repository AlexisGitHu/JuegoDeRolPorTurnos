package com.utad.mais.proyectoFinal.pattern.decorator2;

public class MultiplicadorBase implements FactoryComponent {

	/*
	 * Clase base del patr�n decorador2.
	 *  No aporta ning�n incremento de la estad�sticas, sirve para asegurar que la factor�a
	 * se pueda ir decorando de acuerdo al n�mero de veces que se reinicie el juego.
	 */
	@Override
	public Integer getVida() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVida(Integer vida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getFuerza() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFuerza(Integer fuerza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getIntelecto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIntelecto(Integer intelecto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getAgilidad() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAgilidad(Integer agilidad) {
		// TODO Auto-generated method stub
		
	}

}
