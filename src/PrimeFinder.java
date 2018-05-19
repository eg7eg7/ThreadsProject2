/*
 * 
 * 
 * EDEN DUPONT ID 204808596
 * 
 */
public abstract class PrimeFinder implements Runnable{
	protected int id;
	public final static int MAX_THREADS = 10000;
	
	//id of thread group doing one job
	public PrimeFinder(int id) {
		this.id = id;
	}

	abstract public void setPrime(int n);

	abstract public int getPrime();
}
