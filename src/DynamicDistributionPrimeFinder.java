/*
 * 
 * 
 * EDEN DUPONT ID 204808596
 * 
 */
public class DynamicDistributionPrimeFinder extends PrimeFinder {

	private int num; // the number to be checked
	private volatile static int[] primes = new int[MAX_THREADS];
	// each index is the id of a group of thread within a range -
	// limited to 10,000 ranges at a time

	public DynamicDistributionPrimeFinder(int id) {
		super(id);
		num = 0;
	}

	public void run() {
		if (isPrime(num))
			setPrime(num);
	}

	@Override
	public synchronized int getPrime() {
		return primes[id];
	}

	@Override
	public synchronized void setPrime(int n) {
		if (getPrime() == 0)
			primes[id] = n;
	}

	private boolean isPrime(int n) {
		// check if n is a multiple of 2

		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		// method stop checking for more numbers if one was already found
		for (int i = 3; i * i <= n && getPrime() == 0; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public void setNextNumToCheck(int n) {
		num = n;
	}

	public int getJobId() {
		return id;
	}

	public void setJobId(int id) {
		this.id = id;
	}
}
