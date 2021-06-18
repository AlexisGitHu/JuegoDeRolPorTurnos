package com.utad.mais.proyectoFinal.pattern.decorator2;

public class MultiplicadorBase implements FactoryComponent {

	/*
	 * Clase base del patrón decorador2.
	 *  No aporta ningún incremento de la estadísticas, sirve para asegurar que la factoría
	 * se pueda ir decorando de acuerdo al número de veces que se reinicie el juego.
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
