public class StaticDistibutionPrimeFinder implements Runnable {
	
	// A and B are a subrange of the total range to check for in the thread
	private int A = 0, B = 0;
	private static volatile int[] primes = new int[1000000];
	private int id;
	
	
	// A - Begin at number
	// range - number of Integer to check, including A
	public StaticDistibutionPrimeFinder(int A, int range, int id) {
		this.A = A;
		this.B = A + range - 1;
		this.id=id;
	}

	@Override
	public void run() {
		for (int i = A; i <= B && getPrime() == 0; i++) {
				if (isPrime(i))
					setPrime(i);
		}
	}

	private synchronized void setPrime(int n) {
		if(getPrime() == 0)
			primes[id] = n;
		
	}
	public synchronized int getPrime() {
		return primes[id];
	}

	private boolean isPrime(int n) {
		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n && getPrime() == 0; i += 2) {
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
