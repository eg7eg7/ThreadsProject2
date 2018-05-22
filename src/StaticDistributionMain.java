/*
 * 
 * 
 * EDEN DUPONT ID 204808596
 * 
 */
public class StaticDistributionMain {

	public static void main(String[] args) {
		int A = 0, B = 0, N = 1;

		// range to check (A-B) for primes
		// N - number of threads

		try {
			if (args.length >= 3) {
				A = Integer.parseInt(args[0]);
				B = Integer.parseInt(args[1]);
				N = Integer.parseInt(args[2]);
				firstPrimeStaticDistribution(A, B, N);

			} else {
				System.out.println("Not enough arguments passed.");
			}

		} catch (NumberFormatException ex) {
			System.out.println("invalid arguments passed." + ex.getMessage());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// returns the first integer found in the range by all threads, or a message of
	// type
	// String if no prime is found
	public static void firstPrimeStaticDistribution(int A, int B, int N) throws InterruptedException {
		System.out.println("Static Distribution implementation\nChecking primes within the range [" + A + ", " + B + "] with " + N + " threads.\n");
		SDPFThread thread = new SDPFThread(A, B, N);
		thread.start();
		thread.join();
	}
}