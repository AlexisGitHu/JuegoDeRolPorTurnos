package com.utad.mais.proyectoFinal.pattern.singleton;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import com.utad.mais.proyectoFinal.interfazGrafica.*;
import com.utad.mais.proyectoFinal.pattern.abstractFactory.*;
import com.utad.mais.proyectoFinal.pattern.decorator.Equipamiento;
import com.utad.mais.proyectoFinal.pattern.decorator2.MultiplicadorNormal;
import com.utad.mais.proyectoFinal.pattern.state.Paralyzed;
import com.utad.mais.proyectoFinal.pattern.strategy.*;
import com.utad.mais.proyectoFinal.pattern.template.*;
import com.utad.mais.proyectoFinal.personajes.*;

public class GameController 
{
	public static GameController gameController = new GameController(EnemyFactoryWorld1.getInstance());
	private AbstractEnemyFactory abstractEnemyFactory; //Atributo contexto del patrón abstract Factory
	private static Integer enemyNumber; //Número de enemigos actuales del mundo.
	private static final Integer totalEnemies = 11;
	private static Integer puntosTotales; //Atributo que tiene en cuenta la puntuación del jugador.
	private static Integer contadorEnemigosMatados; //Atributo que tiene en cuenta el número de enemigos matados.
	private Personaje jugador; //Atributo jugador
	private Calculadora calculadora = Calculadora.getInstance(); //Instancia la calculadora
	private ProbabilityTemplate strategyToApply; //Atributo del contexto del Template Method
	private List<Enemy> enemigos; //Lista de enemigos actuales del mundo.
	private World actualWorld;//Mundo actual en el que se encuentra el juego.
	private InterfazGrafica gui = InterfazGrafica.getInstance(); //Instancia la interfaz grafica
	
	//Constructor por parámetros del GameController, solo necesita que se le pase por defecto la factoría.
	private GameController(AbstractEnemyFactory abstractEnemyFactory)
	{
		this.abstractEnemyFactory= abstractEnemyFactory;
		this.strategyToApply=new RandomProbabilityGeneratorFair();
		this.setEnemigos(new ArrayList<Enemy>());
		this.actualWorld=World.LEVEL1;
		GameController.enemyNumber=0;
		GameController.puntosTotales=0;
		GameController.contadorEnemigosMatados=0;
		this.jugador=new Jugador();
		
		//Hacemos visible todas las ventanas
    	this.gui.getLogWindow().setVisible(true);
    	this.gui.getStatsWindow().setVisible(true);
    	this.gui.getEnemyWindow().setVisible(true);
    	this.gui.getEnemyPicture().setVisible(true);
    	this.gui.getPlayerPicture().setVisible(true);
    	
    	this.gui.setJugador((Jugador)this.jugador); //Seteamos el jugador
    	
    	//Seteamos las imagenes para los botones para poder diferenciar entre mundos
    	this.setImagesForButtons();
    	
    	//Seteamos la imagen para el jugador
    	this.setImageForPlayer();
    	
    	this.gui.añadirTextoLog("******************\nMUNDO 1\n******************\n");
	}
	
	public static GameController getInstance()
	{
		return gameController;
	}
	public static void setInstance(GameController gameController)
	{
		GameController.gameController=gameController;
	}
	public AbstractEnemyFactory getAbstractFactory() 
	{
		return this.abstractEnemyFactory;
	}
	//Método que cambia la factoría que se debe usar para la creación de enemigos y llama al método que permite cambiar el mundo
	public void setAbstractFactory(AbstractEnemyFactory abstractEnemyFactory) 
	{
		this.abstractEnemyFactory = abstractEnemyFactory;
		this.changeWorld();
	}
	private void changeWorld() //Método que permite cambiar el mundo actual.
	{
		if(GameController.getInstance().getAbstractFactory().getClass().equals(EnemyFactoryWorld1.class))
		{
			this.actualWorld=World.LEVEL1;
		}
		else if(GameController.getInstance().getAbstractFactory().getClass().equals(EnemyFactoryWorld2.class))
		{
			this.actualWorld=World.LEVEL2;
		}
		else
		{
			this.actualWorld=World.LEVEL3;
		}
		
		//Seteamos las imagenes para los botones en caso de cambiar de mundo
		this.setImagesForButtons();
	}
	public Personaje getJugador() 
	{
		return jugador;
	}
	public void setJugador(Personaje jugador) 
	{
		this.jugador = jugador;
	}
	public Calculadora getCalculadora() 
	{
		return calculadora;
	}
	public void setCalculadora(Calculadora calculadora) 
	{
		this.calculadora = calculadora;
	}
	//Método que delega por composición la creación de FelBats a abstract factory
	public FelBat createFelBat(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createFelBat(strategy);
	}
	//Método que delega por composición la creación de Infernals a  abstract factory
	public Infernal createInfernal(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createInfernal(strategy);
	}
	//Método que delega por composición la creación de Inquisitors a abstract factory
	public Inquisitor createInquisitor(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createInquisitor(strategy);
	}
	//Método que delega por composición la implementación del factory method de abstract factory
	public Enemy createEnemy(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createEnemy(strategy);
	}
	//Método que delega por composición la creación de Bosses a abstract factory
	public Boss createBoss(ActivityStrategy strategy)
	{
		return this.abstractEnemyFactory.createBoss(strategy);
	}
	
	public void setImagesForButtons()
	{
		//Guardamos en variables las imagenes de cada boton
		Image atacar = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Atacar.png")).getImage();
    	Image defenderme = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Defenderme.png")).getImage();
    	Image curarme = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Curarme.png")).getImage();
    	Image equiparme = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Equiparme.png")).getImage();
    	
    	//Seteamos el icono del boton
    	this.gui.setButtons(new ImageIcon(atacar), new ImageIcon(defenderme), new ImageIcon(curarme), new ImageIcon(equiparme));
	}
	
	public void setImageForEnemy(String name)
	{
		ImageIcon imagen;
		if(name != "Boss") //En caso de no ser boss, la carpeta de la imagen donde se encuentra el enemigo es distinta de la de los bosses
			imagen = new ImageIcon(this.getClass().getResource("/Imagenes/"+name+".jpg"));
		else //Si es boss, tendremos que acceder a la carpeta del mundo actual y elegir el boss, ya que cada mundo tendrá un Boss distinto
			imagen = new ImageIcon(this.getClass().getResource("/Imagenes/world"+this.actualWorld.getLevel()+"/Boss.jpg"));
		
		//Seteamos la imagen del enemigo
		this.gui.setImageEnemy(imagen);
	}
	
	public void setImageForPlayer()
	{
		//Seteamos la imagen del jugador
		ImageIcon imagen = new ImageIcon(this.getClass().getResource("/Imagenes/Jugador.png"));
		this.gui.setImagePlayer(imagen);
	}
	
	public void play() //método fachada de la clase gameController, solo se necesita ejecutar este método para empezar a jugar.
	{
		this.generateEnemies(); //generamos los enemigos del mundo actual.
		
		while(enemyNumber!=0 || this.actualWorld!=World.LEVEL3 ) //Bucle que comprueba que no haya terminado el juego
		{
			this.checkPlayerWorld(); //Método que comprueba y cambia el mundo en el que se encuentra el jugador en función de los enemigos matados.
			
			this.setImageForEnemy(this.enemigos.get(contadorEnemigosMatados).getName()); //Seteamos la imagen del enemigo
			
			System.out.println(this.enemigos.get(contadorEnemigosMatados).getName()+" se enfrenta con "+this.jugador.getName());
			this.gui.añadirTextoLog("\n"+this.enemigos.get(contadorEnemigosMatados).getName()+" se enfrenta con "+this.jugador.getName() + "\n"); //Anexamos al log un texto
			
			this.mostrarTodo(); //Mostramos las estadísticas de todos los personajes actuales.
			
			this.applyEnemyCommand();//Llamamos al método que se encarga de decirle al enemigo que realice su acción
			
			this.applyPlayerCommand();//Llamos al método que se encarga de decirle al jugador que realice su acción
		}
		
		
		if(this.continuarJugando()) //Condicional que llama al método que permite elegir al jugador si continua jugando
		{
			this.play(); //Llamada al método recursivo play que permite seguir jugando con un incremento de la dificultad.
		}
		else
		{
			this.victory();//Llamada al método que permite poner fin al juego indicando la puntuación y si ha ganado.
		}
		
	}
	public ProbabilityTemplate getStrategyToApply() 
	{
		return strategyToApply;
	}
	public void setStrategyToApply(ProbabilityTemplate strategyToApply) 
	{
		this.strategyToApply = strategyToApply;
	}
	public static Integer getEnemyNumber() 
	{
		return enemyNumber;
	}
	public static void setEnemyNumber(Integer enemyNumber) 
	{
		GameController.enemyNumber = enemyNumber;
	}
	public static void addEnemy() //Método que añade enemigos al contador de enemigos actuales
	{
		enemyNumber++;
	}
	public List<Enemy> getEnemigos() 
	{
		return enemigos;
	}
	public void setEnemigos(List<Enemy> enemigos) 
	{
		this.enemigos = enemigos;
	}
	private void generateEnemies() //Método que se encarga generar los enemigos
	{
		if(!enemigos.isEmpty()) //Condicional que borra los elementos enemigos actuales de la lista de enemigos y pone el contador a 0 de enemigos matados a 0.
		{
			enemigos.clear();
			contadorEnemigosMatados=0;
		}
		for(int i =0; i<totalEnemies-1;i++) //Bucle que se encarga de generar los 10 enemigos del mundo de forma aleatoria
		{
			enemigos.add(this.createEnemy(this.strategyToApply.applyTemplateMethod()));
		}
		enemigos.add(this.createBoss(this.strategyToApply.applyTemplateMethod())); //Método que se encarga de generar al boss del mundo actual.
	}
	private boolean continuarJugando() //Método que se encarga de preguntar al player si sigue jugando o no
	{
		this.reiniciarEnemigo();
		
		int accion = -1;
		boolean continuePlaying = false;
		System.out.println("¿Desea continuar jugando?\nPulse 1 para continuar.\nPulse 2 para finalizar.");
		this.gui.muestraPosibilidadFinal(); //Mostramos las distintas posibilidades de final
		
		//Obtenemos la opcion de finalizar y controlamos los errores
		if(this.gui.getOpcionFinalizar() > 3 || this.gui.getOpcionFinalizar() < 0)
		{
			this.gui.setTextoLog("ERROR: Opcion no valida, seleccione entre 1 y 2\n"); //Seteamos el texto de logs
			this.continuarJugando(); //Volvemos a pedir el input hasta q sea válido
		}
		
		accion = this.gui.getOpcionFinalizar();
				
		switch(accion) 
		{
			case 1: //En caso de ser 1, reiniciamos todo
				continuePlaying=true;
				//Reseteo e incremento de la dificultad
				this.setStrategyToApply(new RandomProbabilityGeneratorUnfair());
				EnemyFactoryWorld1.getInstance().setFactoryComponent(
						new MultiplicadorNormal(EnemyFactoryWorld1.getInstance().getFactoryComponent()));
				EnemyFactoryWorld2.getInstance().setFactoryComponent(
						new MultiplicadorNormal(EnemyFactoryWorld2.getInstance().getFactoryComponent()));
				EnemyFactoryWorld3.getInstance().setFactoryComponent(
						new MultiplicadorNormal(EnemyFactoryWorld3.getInstance().getFactoryComponent()));
				this.setAbstractFactory(EnemyFactoryWorld1.getInstance());
				this.setActualWorld(World.LEVEL1); //Seteamos de nuevo el mundo 1
				this.gui.setTextoLog("******************\nMUNDO 1\n****************\n"); //Seteamos el texto de los logs a mundo 1
				((Jugador)this.jugador).setequipamiento(new Equipamiento());//resetear equipamiento

				break;
			case 2: //En caso de ser 2, no queremos seguir.
				continuePlaying=false;
				break;
			default:
				break;
				
		}
		
		return continuePlaying;
	}
	
	private void checkPlayerWorld() //Método que se encarga de comprobar si se debe cambiar el mundo o no.
	{
		if(GameController.enemyNumber==0 && this.actualWorld==World.LEVEL1)
		{
			System.out.println("Cambiando de mundo al 2");
			this.gui.setTextoLog("******************\nMUNDO 2\n******************\n"); //Seteamos el texto de logs al mundo2
			this.actualWorld=World.LEVEL2;
			this.setAbstractFactory(EnemyFactoryWorld2.getInstance());
			this.generateEnemies();
		}
		else if(GameController.enemyNumber==0 && this.actualWorld==World.LEVEL2)
		{
			System.out.println("Cambiando de mundo al 3");
			this.gui.setTextoLog("******************\nMUNDO 3\n******************\n"); //Seteamos el texto de logs al mundo3
			this.actualWorld=World.LEVEL3;
			this.setAbstractFactory(EnemyFactoryWorld3.getInstance());
			this.generateEnemies();
		}
	}
	private void applyPlayerCommand()
	{
		if(this.isParalizado(this.jugador)==false)
		{
			int accion;
			
			synchronized(this.gui) //Nos sincronizamos con la interfaz gráfica para esperar una respuesta hasta que se nos notifique
			{
				try 
				{
					this.gui.wait();
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				accion = this.gui.getOpcionGeneral(); //Una vez notificados obtenemos la opcion elegida
			}
			
			switch(accion)
			{
				case 1:
					this.gui.añadirTextoLog(this.jugador.getName()+" atacó a "+this.enemigos.get(contadorEnemigosMatados).getName() + "\n"); //Anexamos el texto de logs
					this.calculadora.calculaDañoRecibido(this.jugador, this.enemigos.get(contadorEnemigosMatados)); //Calculamos el daño recibido por el enemigo
					break;
				case 2:
					this.gui.añadirTextoLog(this.jugador.getName()+" se curó a sí mismo\n"); //Anexamos el texto de logs
					this.calculadora.calculaCuraRecibida(this.jugador); //Calculamos la cura recibida
					break;
				case 3:
					if(this.gui.getOpcionEquipacion() < 4 && this.gui.getOpcionEquipacion() > 0) //Control de errores en la opcion del equipamiento
					{
						Integer equipamiento = this.gui.getOpcionEquipacion(); //Obtenemos el equipamiento
						((Jugador)this.jugador).equiparseObjeto(equipamiento); //Equipamos al jugador con el equipamiento debido
						this.gui.añadirTextoLog("El equipo actual de "+jugador.getName()+" es: "+((Jugador)this.jugador).getequipamiento().getDesc() + "\n"); //Anexamos el texto de logs
					}
					else
					{
						this.gui.añadirTextoLog("Equipamiento no valido...\n"); //Anexamos el texto de logs
					}
					break;
				case 4:
					this.calculadora.calculaDefendido(this.jugador, this.enemigos.get(contadorEnemigosMatados)); //Calculamos el daño al enemigo y cura recibida
					break;
				default:
					break;
			}
			this.checkEnemyState();
		}
	}
	
	private void applyEnemyCommand() //Método que se encarga de aplicar la acción de la estrategia dele enemigo y comprobar el estado del jugador acto seguido
	{
		if(this.isParalizado(this.enemigos.get(contadorEnemigosMatados))==false) //Si el enemigo está paralizado, entonces no se aplica ninguna acción
		{
			this.enemigos.get(contadorEnemigosMatados).applyStrategy(this.jugador);
		}
		this.checkPlayerState();//Método que se encarga de comprobar el estado del jugador.
		this.mostrarTodo(); //Mostramos todas las estadísticas tanto del jugador como del enemigo
	}
	
	private void checkEnemyState() //Método que se encarga de comprobar el estado del enemigo
	{
		System.out.println("Estado enemigo: ");
		this.enemigos.get(contadorEnemigosMatados).getActualState().process();

	}
	
	private void checkPlayerState() //Método que se encarga de comprobar el estado del jugador
	{
		System.out.println("Estado jugador: ");
		
		this.jugador.getActualState().process();
	}
	
	private void mostrarTodo()
	{
		//Seteamos el nombre, estadísticas, estado del jugador y número de enemigos matados en el mundo X
		this.gui.setTextoStats("Nombre: " + this.jugador.getName()+"\nVida: "+this.jugador.getVida()+"\n");
		this.gui.añadirTextoStats("Agilidad: " + this.jugador.getAgilidad() + "\n"+
				"Fuerza: " + this.jugador.getFuerza() + "\n"+
				"Intelecto: " + this.jugador.getIntelecto() + "\n");
		this.gui.añadirTextoStats("Estado jugador: "+this.jugador.getActualState().getClass().getSimpleName()+"\n");

		this.gui.añadirTextoStats("Enemigos matados en el mundo: " + contadorEnemigosMatados + "\n");
		
		//Seteamos el nombre, estadísticas y estado del enemigo
		this.gui.setTextoEnemy("Nombre: " +this.enemigos.get(contadorEnemigosMatados).getName()+"\nVida: "+this.enemigos.get(contadorEnemigosMatados).getVida()+ "\n");
		this.gui.añadirTextoEnemy("Estado actual: " +this.enemigos.get(contadorEnemigosMatados).getActualState().getClass().getSimpleName()+ "\n");
		
	}
	
	private void reiniciarEnemigo()
	{
		this.enemigos.get(contadorEnemigosMatados - 1).setVida(0); //Seteamos la vida del último enemigo a 0
		
		//Actualizamos las estadísticas del enemigo
		this.gui.setTextoEnemy("Nombre: " +this.enemigos.get(contadorEnemigosMatados-1).getName()+"\nVida: "+this.enemigos.get(contadorEnemigosMatados-1).getVida()+ "\n");
		this.gui.añadirTextoEnemy("Estado actual: " +this.enemigos.get(contadorEnemigosMatados-1).getActualState().getClass().getSimpleName()+ "\n");
	}
	
	public void gameOver()
	{
		this.jugador.setVida(0); //Seteamos la vida del jugador a 0
		this.mostrarTodo(); //Mostramos todas las estadísitcas actulaizadas
		
		this.gui.gameOver(); //Mostramos el game Over de la interfaz gráfica
		System.out.println(this.jugador.getName()+" has sido derrotado por las hordas del infierno");
		System.out.println("Puntuación total: "+GameController.getPuntosTotales());
		System.out.println("----------------------------------------------\n"
											+ "GAME OVER\n"
						  +"-----------------------------------------------");
		
		System.exit(0); //Salimos del programa
	}
	private boolean isParalizado(Personaje personaje) //Método que se encarga de comprobar si el personaje está paralizado o no
	{
		if(personaje.getActualState().getClass().equals(Paralyzed.class))
		{
			System.out.println(personaje.getName()+" está paralizado y no pudo realizar acción");
			this.gui.añadirTextoLog(personaje.getName()+" está paralizado y no pudo realizar acción\n"); //Anexamos el texto en el log
			return true;
		}
		else
		{
			return false;
		}
	}
	private void victory()
	{
		this.gui.victory(); //Mostramos el victory de la interfaz gráfica
		
		System.out.println("----------------------------------------------\n"
											+ "HAS GANADO\n"
							+"-----------------------------------------------");
		System.out.println(this.jugador.getName()+" ERES UN AUTÉNTICO DOOM SLAYER");
		System.out.println("Puntuación total: "+GameController.getPuntosTotales());
		
		System.exit(0); //Salimos del programa
	}
	public static Integer getPuntosTotales() 
	{
		return puntosTotales;
	}
	public static void setPuntosTotales(Integer puntosTotales)
	{
		GameController.puntosTotales = puntosTotales;
	}
	public static void addPuntosTotales(Integer puntosASumar)
	{
		GameController.puntosTotales+=puntosASumar;
	}
	public static Integer getContadorEnemigosMatados()
	{
		return GameController.contadorEnemigosMatados;
	}
	public static void setContadorEnemigosMatados(Integer contadorEnemigosMatados)
	{
		GameController.contadorEnemigosMatados=contadorEnemigosMatados;
	}
	public static void addEnemigoMatado()
	{
		GameController.contadorEnemigosMatados++;
	}
	public static void restaEnemyNumber() //Método que se encarga de restar el número de enemigos actuales del mundo
	{
		GameController.enemyNumber--;
	}
	public void setActualWorld(World actualWorld)
	{
		this.actualWorld=actualWorld;
		this.setImagesForButtons(); //Seteamos las imagenes de los botones en caso de que se actualice el mundo
	}
	public World getActualWorld()
	{
		return this.actualWorld;
	}
	
}
