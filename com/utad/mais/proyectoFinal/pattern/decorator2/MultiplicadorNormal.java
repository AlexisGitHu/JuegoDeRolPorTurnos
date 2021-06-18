package com.utad.mais.proyectoFinal.pattern.decorator2;

public class MultiplicadorNormal implements FactoryComponentDecorator {

	/*
	 * Clase decorador del patrón decorador2. 
	 * 
	 * Aporta un incremento a las estadísticas base en la instanciacíon de los enemigos.
	 * 
	 */
	
	private FactoryComponent factoryComponent; //Objeto base o decorador al que decora el objeto decorador.
	
	//Constructor que asegura que el objeto siempre tenga un objeto al que esté decorando.
	public MultiplicadorNormal(FactoryComponent factoryComponent)
	{
		this.factoryComponent=factoryComponent;
	}
	@Override
	public Integer getVida() {
		// TODO Auto-generated method stub
		return 10+this.factoryComponent.getVida();
	}

	@Override
	public void setVida(Integer vida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getFuerza() {
		// TODO Auto-generated method stub
		return 10+this.factoryComponent.getFuerza();
	}

	@Override
	public void setFuerza(Integer fuerza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getIntelecto() {
		// TODO Auto-generated method stub
		return 10+this.factoryComponent.getIntelecto();
	}

	@Override
	public void setIntelecto(Integer intelecto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getAgilidad() {
		// TODO Auto-generated method stub
		return 10+this.factoryComponent.getAgilidad();
	}

	@Override
	public void setAgilidad(Integer agilidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FactoryComponent getFactoryComponent() {
		// TODO Auto-generated method stub
		return this.factoryComponent;
	}

}
