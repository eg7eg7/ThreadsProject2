/*
 * 
 * 
 * EDEN DUPONT ID 204808596
 * 
 */
//Thread manages Dynamic Distribution to search for prime
public class DDPFThread extends Thread {

	private int prime = 0;
	private int A; // find primes from a
	private int next;
	private int B; // find primes until b
	private int N; // use n threads
	private static int nextId = 0;
	private int id;
	private DynamicDistributionPrimeFinder[] DDPF = null;
	private Thread[] threads = null;

	public DDPFThread(int a, int b, int n) {
		this.A = a;
		next = this.A;
		this.B = b;
		this.N = n;
		id = nextId++;
		DDPF = new DynamicDistributionPrimeFinder[n];
		threads = new Thread[n];
	}

	@Override
	public void run() {

		initializeThreadsToReady();

		while ((getPrime() == 0 && next <= B) || ThreadsAlive()) {
			for (int i = 0; i < N; i++) {
				if ((!threads[i].isAlive()) && getPrime() == 0 && next <= B) {
					DDPF[i].setNextNumToCheck(next++);
					threads[i] = new Thread(DDPF[i]);
					threads[i].start();
					
				}
			}
			prime = DDPF[0].getPrime();
		}

		if (prime == 0)
			System.out.println("No prime found within range " + A + "-" + B);
		else
			System.out.println("The first prime found is " + prime);
	}

	private boolean ThreadsAlive() {
		for (int i = 0; i < threads.length; i++)
			if (threads[i].isAlive())
				return true;
		return false;
	}

	private synchronized void initializeThreadsToReady() {
		for (int i = 0; i < N; i++) {
			DDPF[i] = new DynamicDistributionPrimeFinder(id);
			threads[i] = new Thread(DDPF[i]);
		}
	}

	public int getPrime() {
		return prime;
	}
}
