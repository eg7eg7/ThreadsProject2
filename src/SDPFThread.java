/*
 * 
 * 
 * EDEN DUPONT ID 204808596
 * 
 */
//Thread manages Static Distribution to search for prime
public class SDPFThread extends Thread {
	private int prime = 0;
	private int A; // find primes from a
	private int B; // find primes until b
	private int N; // use n threads
	private static int nextId = 0;
	private int id;
	private StaticDistibutionPrimeFinder[] SDPF = null;
	private Thread[] threads = null;

	public SDPFThread(int a, int b, int n) {
		this.A = a;
		this.B = b;
		this.N = n;
		id = nextId++;
		SDPF = new StaticDistibutionPrimeFinder[n];
		threads = new Thread[n];
	}

	@Override
	public void run() {

		int range = ((B - A + 1) / N);
		int subrangeA;

		for (int i = 0; i < N; i++) {
			// makes sure the last thread will calculate the rest of the
			// integers if they don't divide to N threads
			// creates a job for thread i with subrange of A-B
			subrangeA = A + i * range;
			if (i == (N - 1))
				range = B - subrangeA + 1;
			SDPF[i] = new StaticDistibutionPrimeFinder(subrangeA, range, id);
			threads[i] = new Thread(SDPF[i]);
			threads[i].start();

		}
		waitForAllThreads();
		prime = SDPF[0].getPrime();
	}

	private void waitForAllThreads() {
		// threads are guaranteed to die naturally when a prime is found
		try {
			for (int i = 0; i < threads.length; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			DDPFThread.currentThread().interrupt();
		}

	}

	public int getPrime() {
		return prime;
	}
}
