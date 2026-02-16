package pc.philo;

public class TestPhilo {

	public static void main (String [] args) {
		final int NB_PHIL = 5;
		Thread [] tPhil = new Thread[NB_PHIL];
		Fork [] tChop = new Fork[NB_PHIL];

		for (int i = 0 ; i < NB_PHIL ; i++) {
			tChop[i] = new Fork() ;
		}
		
		for (int i = 0 ; i < NB_PHIL ; i++) {
			Philosopher phil;
			if (i < NB_PHIL -1) {
				phil = new Philosopher(tChop[i], tChop[i+1]) ;
				
			} else {
				// Configuration du dernier philosophe (acquisition dans l'ordre croissant des indices)
				phil = new Philosopher(tChop[0], tChop[NB_PHIL-1]) ; 
				
			}
			
			tPhil[i] = new Thread(phil) ;
			tPhil[i].start();
		}
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Thread t : tPhil) {
			try {
				t.interrupt();
				t.join() ;
			} catch (InterruptedException e){
				e.printStackTrace() ; 
			}
		}
		System.out.println("Fin du programme");
	}
}