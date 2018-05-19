

public class DynamicDistributionPrimeFinder implements Runnable {

	private static volatile int[] primes = new int[10000];	//each index is the id of a group of thread within a range - limited to 10,000 ranges at a time
	private int num;	//the number to be checked
	private int instanceId;	//id of main thread executing this thread
	


	public DynamicDistributionPrimeFinder(int id) {
		num = 0;
		instanceId=id;
	}

	public void run() {
		if (isPrime(num))
			setPrime(num);
	}

	public synchronized int getPrime() {
		return primes[instanceId];
	}

	private synchronized void setPrime(int n) {
		if (getPrime() == 0)
			primes[instanceId] = n;
		
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
	
	public void setNextNumToCheck(int n)
	{
		num=n;
	}
	
	
	public int getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}
}
