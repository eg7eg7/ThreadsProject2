
public class DynamicDistributionMain {

	public static void main(String[] args) {
		int A = 0, B = 0, N = 1;

		// range to check (A-B) for primes
		// N - number of threads

		try {
			if (args.length >= 3) {
				A = Integer.parseInt(args[0]);
				B = Integer.parseInt(args[1]);
				N = Integer.parseInt(args[2]);

				System.out.println("The first prime found was (dynamic range distribution to threads) : "
						+ firstPrimeDynamicDistribution(A, B, N));
			} else {
				System.out.println("Not enough arguments passed.");
			}

		} catch (NumberFormatException ex) {
			System.out.println("invalid arguments passed." + ex.getMessage());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Object firstPrimeDynamicDistribution(int A, int B, int N) throws InterruptedException {
		System.out.println("Checking primes within the range [" + A + ", " + B + "] with " + N + " threads.\n");
		DDPFThread thread = new DDPFThread(A, B, N);
		thread.start();
		thread.join();
		int prime = thread.getPrime();
		if (prime == 0)
			return "No Prime in range";
		return prime;
	}

}