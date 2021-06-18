package com.utad.mais.proyectoFinal.personajes;

import com.utad.mais.proyectoFinal.pattern.decorator.*;
import com.utad.mais.proyectoFinal.pattern.singleton.*;
import com.utad.mais.proyectoFinal.pattern.state.*;

public class Jugador implements Personaje
{
	/*
	 * Clase jugador que implementa la interfaz Personaje. 
	 * 
	 * Los métodos caracterizadores son, en general, los getters y setters de todos sus atributos, 
	 * métodos especiales que modifican sus estadísticas base y los getters de su estado actual y todos los estados posibles
	 * puesto que cualquier personaje es el contexto del patrón State.
	 * 
	 * Además, guarda una referencia al GameController.
	 * 
	 * Al ser una clase controlada por el usuario no implementa una estrategia que determine sus acciones
	 * y no tiene una característica especial. No obstante, sí tiene un atributo que representa el equipamiento del personaje
	 * e incrementa sus estadísticas acorde a los objetos equipados (patrón decorador).
	 * 
	 * El resto de atributos son los determinados por los métodos de la interfaz Personaje.
	 * 
	 * Los getters de sus atributos devuelven sus estadísticas base más las estadísticas de los objetos que lleva equipados,
	 * ya que el equipamient no afecta a las estadísticas base.
	 */
	private Integer fuerza;
	private Integer agilidad;
	private Integer intelecto;
	private Integer resistenciaFisica;
	private Integer resistenciaMagica;
	private Integer vida;
	private String name;
	private GameController gameController;
	private final Integer vidaInicial;
	private HealthState actualState;
	private Healthy healthyState;
	private Injured injuredState;
	private SeverelyInjured severelyInjuredState;
	private Paralyzed paralyzedState;
	private Dead deadState;
	
	private ArmaComponent equipamiento;
	
	//Constructor por defecto, cualquier jugador que se instancie deberá tener las estadísticas base descritas en este constructor.
	public Jugador() 
	{
		this.fuerza = 200;
		this.agilidad = 100;
		this.intelecto = 100;

		this.resistenciaFisica = 100;
		this.resistenciaMagica = 100;
		this.vida = 350;
		this.vidaInicial=350;
		this.name = "Player";
		this.healthyState = new Healthy(this);
		this.injuredState = new Injured(this);
		this.severelyInjuredState = new SeverelyInjured(this);
		this.paralyzedState = new Paralyzed(this);
		this.deadState = new Dead(this);
		this.actualState = healthyState;
		this.equipamiento = new Equipamiento();
	}
	
	public Integer getFuerza() 
	{
		return (this.equipamiento.getFuerza()+this.fuerza);
	}
	
	public void setFuerza(Integer fuerza) 
	{
		this.fuerza = fuerza;
	}
	
	public Integer getAgilidad() 
	{
		return (this.equipamiento.getAgilidad()+agilidad);
	}
	
	public void setAgilidad(Integer agilidad) 
	{
		this.agilidad = agilidad;
	}
	
	public Integer getIntelecto() 
	{
		return (this.equipamiento.getIntelecto()+intelecto);
	}
	
	public void setIntelecto(Integer intelecto) 
	{
		this.intelecto = intelecto;
	}
	
	public Integer getResistenciaFisica() 
	{
		return resistenciaFisica;
	}
	
	public void setResistenciaFisica(Integer resistenciaFisica) 
	{
		this.resistenciaFisica = resistenciaFisica;
	}
	
	public Integer getResistenciaMagica() 
	{
		return resistenciaMagica;
	}
	
	public void setResistenciaMagica(Integer resistenciaMagica) 
	{
		this.resistenciaMagica = resistenciaMagica;
	}
	
	public Integer getVida() 
	{
		return vida;
	}
	public Integer getVidaInicial()
	{
		return this.vidaInicial;
	}
	public void setVida(Integer vida)
	{
		if(vida>this.getVidaInicial()) //Normalización de los valores de vida permitidos [0-VidaInicial]
		{
			vida=this.getVidaInicial();
		}
		this.vida = vida;
		
	}
	public void addFuerza(Integer fuerza)
	{
		this.fuerza+=fuerza;
	}
	public void addAgilidad(Integer agilidad)
	{
		this.agilidad+=agilidad;
	}
	public void addIntelecto(Integer intelecto)
	{
		this.intelecto+=intelecto;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public GameController getGameController() 
	{
		return gameController;
	}
	
	public void setGameController(GameController gameController) 
	{
		this.gameController = gameController;
	}
	
	public HealthState getActualState() 
	{
		return actualState;
	}
	
	public void setActualState(HealthState actualState) 
	{
		this.actualState = actualState;
	}
	
	public Healthy getHealthyState()
	{
		return healthyState;
	}
	
	public void setHealthyState(Healthy healthyState) 
	{
		this.healthyState = healthyState;
	}
	
	public Injured getInjuredState() 
	{
		return injuredState;
	}
	
	public void setInjuredState(Injured injuredState) 
	{
		this.injuredState = injuredState;
	}
	
	public SeverelyInjured getSeverelyInjuredState() 
	{
		return severelyInjuredState;
	}
	
	public void setSeverelyInjuredState(SeverelyInjured severelyInjuredState) 
	{
		this.severelyInjuredState = severelyInjuredState;
	}
	
	public Paralyzed getParalyzedState() 
	{
		return paralyzedState;
	}
	
	public void setParalyzedState(Paralyzed paralyzedState) 
	{
		this.paralyzedState = paralyzedState;
	}
	
	public Dead getDeadState()
	{
		return deadState;
	}
	
	public void setDeadState(Dead deadState)
	{
		this.deadState = deadState;
	}
	
	
	public void process()
	{
		this.actualState.process();
	}

	public ArmaComponent getequipamiento()
	{
		return equipamiento;
	}

	public void setequipamiento(ArmaComponent equipamiento) 
	{
		this.equipamiento = equipamiento;
	}
	
	public void equiparseObjeto(Integer accion)
	{
			switch(accion)
			{
			 	case 1:
			 		this.equipamiento=new EspadaComponentDecorator(this.equipamiento);
			 		break;
			 	case 2:
			 		this.equipamiento=new PocionComponentDecorator(this.equipamiento);
			 		break;
			 	case 3:
			 		this.equipamiento=new ZapatillasComponentDecorator(this.equipamiento);
			 		break;
			 	default:
			 		break;
			}
		
	}
}