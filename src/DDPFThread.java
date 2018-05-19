
public class DDPFThread extends Thread {

	private int prime = 0;
	private int a; // find primes from a
	private int next;
	private int b; // find primes until b
	private int n; // use n threads
	private static int nextId=0;
	private int id;
	private DynamicDistributionPrimeFinder[] DDPF=null;
	private Thread[] threads=null;
	
	public DDPFThread(int a, int b, int n) {
		this.a = a;
		next = this.a;
		this.b = b;
		this.n = n;
		id=nextId++;
		DDPF = new DynamicDistributionPrimeFinder[n];
		threads = new Thread[n];
	}

	@Override
	public void run() {

		initializeThreadsToReady();

		while (getPrime() == 0 && next <= b) {

			for (int i = 0; i < n; i++) {
				if ((!threads[i].isAlive()) && getPrime() == 0 && next <= b) {
					DDPF[i].setNextNumToCheck(next);
					next++;
					threads[i] = new Thread(DDPF[i]);
					threads[i].start();
				}
			}
			prime = DDPF[0].getPrime();
		}
		waitForAllThreads(threads);
	}

	private void waitForAllThreads(Thread[] threads) {
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

	private synchronized void initializeThreadsToReady() {
		for (int i = 0; i < n; i++) {
			DDPF[i] = new DynamicDistributionPrimeFinder(id);
			threads[i] = new Thread(DDPF[i]);
		}
	}

	public int getPrime() {
		return prime;
	}
}
