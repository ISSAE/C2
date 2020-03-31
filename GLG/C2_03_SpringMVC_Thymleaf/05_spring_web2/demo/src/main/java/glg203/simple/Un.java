package glg203.simple;

public class Un {
	private int a;
	private int b;

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "a= "+a + ", b=" + b;
	}
}
