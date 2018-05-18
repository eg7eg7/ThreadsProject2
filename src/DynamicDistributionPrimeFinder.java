
public class DynamicDistributionPrimeFinder implements Runnable {

	private static volatile int prime = 0;
	private int num;

	public DynamicDistributionPrimeFinder() {
		num = 0;
	}

	public void run() {
		if (isPrime(num))
			setPrime(num);
	}

	public synchronized static int getPrime() {
		return prime;
	}

	public synchronized static int getAndResetPrime() {
		int n = getPrime();
		prime = 0;
		return n;
	}

	public static synchronized void setPrime(int n) {
		if (getPrime() == 0)
			prime = n;
		
	}

	private boolean isPrime(int n) {
		// check if n is a multiple of 2

		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		//method stop checking for more numbers if one was already found
		for (int i = 3; i * i <= n && getPrime() == 0; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	public void checkIfPrime(int n)
	{
		num=n;
	}
}
