
/*
 * 
 * 
 * 
 * 
 * 
 *  EDEN DUPONT ID 204808596
 * 
 * 
 * 
 * 
 * 
*/

public class MultiThreadProject2 {

	public static void main(String[] args) {
		int A = 0, B = 0, N = 1;

		// range to check (A-B) for primes
		// N - number of threads

		try {
			if (args.length >= 3) {
				A = Integer.parseInt(args[0]);
				B = Integer.parseInt(args[1]);
				N = Integer.parseInt(args[2]);
				System.out.println("The first prime found was (static range distribution to threads) : " + firstPrimeStaticDistribution(A, B, N));
				System.out.println("The first prime found was (dynamic range distribution to threads) : " + firstPrimeDynamicDistribution(A, B, N));
			} else {
				System.out.println("Not enough arguments passed.");
			}

		} catch (NumberFormatException ex) {
			System.out.println("invalid arguments passed." + ex.getMessage());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static int firstPrimeDynamicDistribution(int a, int b, int n) throws InterruptedException {
		System.out.println("\n\nChecking primes within the range [" + a + ", " + b + "] with " + n + " threads.\n");
		DynamicDistributionPrimeFinder[] DDPF = new DynamicDistributionPrimeFinder[n];
		Thread[] threads = new Thread[n];
		for(int i=0;i<n;i++)
		{
			DDPF[i] = new DynamicDistributionPrimeFinder();
			threads[i]=new Thread(DDPF[i]);
		}
		
		while(DynamicDistributionPrimeFinder.getPrime() == 0 && a<=b)
		{
			for(int i=0;i<n;i++)
			{
				if((!threads[i].isAlive()) && DynamicDistributionPrimeFinder.getPrime() == 0 && a<=b)
				{
					DDPF[i].checkIfPrime(a);
					a++;
					threads[i]=new Thread(DDPF[i]);	
					threads[i].start();
				}
			}
		}
		//threads are guaranteed to die naturally when a prime is found
		waitForAllThreadsToFinish(threads);
		return DynamicDistributionPrimeFinder.getAndResetPrime();
	}

	private static void waitForAllThreadsToFinish(Thread[] threads) throws InterruptedException {
		for(int i=0;i<threads.length;i++)
		{
			if(threads[i].isAlive())
				threads[i].join();
		}
		
	}

	// returns the first integer found in the range by all threads, or a message of type
	// String if no prime is found
	public static Object firstPrimeStaticDistribution(int A, int B, int N) throws InterruptedException {
		System.out.println("Checking primes within the range [" + A + ", " + B + "] with " + N + " threads.\n");
		Thread[] threads = new Thread[N];
		StaticDistibutionPrimeFinder[] SDPF = new StaticDistibutionPrimeFinder[N];
		int range = ((B - A + 1) / N);
		int subrangeA;

		for (int i = 0; i < N; i++) 
		{
			// makes sure the last thread will calculate the rest of the
			// integers if they don't divide to N threads
			// creates a job for thread i with subrange of A-B
			subrangeA = A + i * range;
			if (i == (N - 1))
				range = B - subrangeA + 1;
			SDPF[i] = new StaticDistibutionPrimeFinder(subrangeA, range);
			threads[i] = new Thread(SDPF[i]);
			threads[i].start();

		}
		for (int i = 0; i < threads.length; i++)
		{
			threads[i].join();
			// makes sure all threads finish before moving on
		}
		int prime = StaticDistibutionPrimeFinder.getAndResetPrime();
		if (prime == 0)
			return "No Prime in range";
		return prime;
	}
}
