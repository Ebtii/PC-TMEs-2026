package pc.philo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
	private Lock mutex ; 
	
	public Fork() {
		this.mutex = new ReentrantLock() ; 
	}
	
	public void acquire () {
		mutex.lock() ; 
    }
	
	
	public void release () {
		mutex.unlock() ; 
	}
}
