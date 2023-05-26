package battlefield;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	static final private int PASSI 		= 40;
	static final private int DIMENSIONE = 50;

	private Battlefield field;
	private Display display;
	
	public Main() {
		this.field = new Battlefield(DIMENSIONE);
		this.display = new Display(DIMENSIONE);
		this.field.riempi();
	}

	public void run() {
		for(int i=0; i<PASSI; i++) {
			
			
			final List<Chaser> allChasers = new ArrayList<>(this.field.getAllChasers());
			Collections.shuffle(allChasers);
			for(Chaser c : allChasers) {
				c.passo(field);
			}
			
			final List<Walker> allWalkers = new ArrayList<>(this.field.getAllWalkers());
			Collections.shuffle(allWalkers);
			for(Walker w : allWalkers) {
				w.passo(field);
			}
			
			this.display.aggiornaSchermata(i, this.field);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Main().run();
	}

}
