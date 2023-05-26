package battlefield;

import java.util.*;

public class Battlefield {

	static final private Class<?>[] TIPOLOGIE = { Walker.class, Chaser.class } ;

	static final private int NUMERO_TIPOLOGIE = TIPOLOGIE.length;

	static final private double OCCUPAZIONE_INIZIALE = 0.01d; // 1%

	private int dim;

	private Random random;
	
	private Map<Position, Robot> posizione2robot;


	public Battlefield(int dimensione) {
		this.dim = dimensione;
		this.posizione2robot = new HashMap<>();
		this.random = new Random();
	}

	public void addWalker(Walker w) {
		this.posizione2robot.put(w.getPosizione(), w);
	}

	public void addChaser(Chaser c) {
		this.posizione2robot.put(c.getPosizione(), c);
	}
	
	public Robot getRobot(Position p) {
	    return posizione2robot.get(p);
	}

	public Collection<Robot> getAllRobots() {
	    return this.posizione2robot.values();
	}

	public Walker getWalker(Position p) {
	    Robot robot = posizione2robot.get(p);
	    if (robot instanceof Walker) {
	        return (Walker) robot;
	    }
	    else
	    	return null;
	}

	public Chaser getChaser(Position p) {
	    Robot robot = posizione2robot.get(p);
	    if (robot instanceof Chaser) {
	        return (Chaser) robot;
	    }
	    return null;
	}

	public Collection<Walker> getAllWalkers() {
	    Collection<Walker> walkers = new ArrayList<>();
	    for (Robot robot : posizione2robot.values()) {
	        if (robot instanceof Walker) {
	            walkers.add((Walker) robot);
	        }
	    }
	    return walkers;
	}

	public Collection<Chaser> getAllChasers() {
	    Collection<Chaser> chasers = new ArrayList<>();
	    for (Robot robot : posizione2robot.values()) {
	        if (robot instanceof Chaser) {
	            chasers.add((Chaser) robot);
	        }
	    }
	    return chasers;
	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Set<?>> raggruppaRobotPerTipo() {
		Map<Class, Set<Robot>> raggruppati = new HashMap<>();
		for(Robot robot : TIPOLOGIE) {
			Set<Robot> setDiRobot = raggruppati.get(robot);
			if(setDiRobot==null) {
				setDiRobot = new HashSet<>();
				setDiRobot.add(robot);
			}
			
		}
		return raggruppati;
	}
	
	public List<?> getRobotOrdinatiPerPosizione() {
		// (vedi DOMANDA 4)
		return null;
	}
	
	public SortedSet<?> getRobotOrdinatiPerLongevita() {
		// (vedi DOMANDA 6)
		return null;
	}
	
	public List<Position> adiacenti(Position perno) {
		final List<Position> adiacenti = new LinkedList<>();
		int x = perno.getX();
		int y = perno.getY();

		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				Position adiacente = new Position(x+i, y+j);
				if (inCampo(adiacente))
					adiacenti.add(adiacente);
			}
		}
		adiacenti.remove(perno);  // coincidono: quindi non sono adiacenti
		
		Collections.shuffle(adiacenti); /* ordine casuale */
		return adiacenti;
	}

	private boolean inCampo(Position p) {
		return  p.getX()>=0 && p.getX()<this.dim && 
				p.getY()>=0 && p.getY()<this.dim  ;
				
	} 

	public Position posizioneLiberaVicino(Position posizione) {
		for(Position p : this.adiacenti(posizione)) {
			if (this.isLibera(p)) {
				return p;
			}
		}
		return null;
	}

	public boolean isLibera(Position posizione) {
		return ( this.getWalker(posizione)==null && this.getChaser(posizione)==null);
	}

	public int getDimensione() {
		return this.dim;
	}

	public void riempi() {
		long numeroIniziale = Math.round(OCCUPAZIONE_INIZIALE * dim * dim);
		for(int i=0 ; i<numeroIniziale; i++) {
			int x = this.random.nextInt(this.dim);
			int y = this.random.nextInt(this.dim);
			Position posizione = new Position(x, y);
			if (this.isLibera(posizione)) {
				switch (this.random.nextInt(NUMERO_TIPOLOGIE)) {
				case 0: Chaser chaser = new Chaser(posizione);
						this.addChaser(chaser);
				break;
				case 1: Walker walker = new Walker(posizione);
						this.addWalker(walker);
				break;
				//case: NUMERO_TIPOLOGIE-1...
				default: throw new IllegalStateException();
				} 
			}
		}
	}

}
