public class StaticDistibutionPrimeFinder implements Runnable {
	
	// A and B are a subrange of the total range to check for in the thread
	private int A = 0, B = 0;
	private static volatile int prime =0;
	
	// A - Begin at number
	// range - number of Integer to check, including A
	public StaticDistibutionPrimeFinder(int A, int range) {
		this.A = A;
		this.B = A + range - 1;
	}

	@Override
	public void run() {
		for (int i = A; i <= B && getPrime() == 0; i++) {
				if (isPrime(i)) 
					setPrime(i);
		}
	}

	private static synchronized void setPrime(int n) {
		if(getPrime() == 0)
			prime = n;
		
	}
	private synchronized static int getPrime() {
		return prime;
	}
	//only use by class caller to enable another distribution
	public synchronized static int getAndResetPrime() throws InterruptedException {
		int n=prime;
		prime=0;
		return n;
	}

	private boolean isPrime(int n) {
		// check if n is a multiple of 2

		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public int getA() {
		return A;
	}

	public void setA(int a) {
		A = a;
	}

	public int getRange() {
		return B;
	}

	public void setRange(int range) {
		this.B = range;
	}
	

}
