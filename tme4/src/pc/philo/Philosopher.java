package pc.philo;

public class Philosopher implements Runnable {
	private Fork left;
	private Fork right;

	public Philosopher(Fork left, Fork right) {
		this.left = left;
		this.right = right;
	}

	public void run() {
		
		while (! Thread.interrupted()) {
			think() ; 
			// Tentative de prise des baguettes 
			left.acquire(); 
			System.out.println(Thread.currentThread().getName() + " has one fork");
			right.acquire(); 
			System.out.println(Thread.currentThread().getName() + " has one fork");
			
			// le philosophe mange
			eat() ;
			
			// Dépot des baguettes
			left.release(); 
			right.release(); 
			System.out.println(Thread.currentThread().getName() + " dropped both forks");
		}
		// System.out.println(Thread.currentThread().getName() + " has one fork");
	}

	private void eat() {
		System.out.println(Thread.currentThread().getName() + " is eating");
	}

	private void think() {
		System.out.println(Thread.currentThread().getName() + " is thinking");
	}
}
