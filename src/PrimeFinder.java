
public abstract class PrimeFinder {
	protected int id;

	public PrimeFinder(int id) {
		this.id = id;
	}

	abstract public void setPrime(int n);

	abstract public int getPrime();
}
