package nonLinearAnalysis;

public class Phrase extends Object {
	
	private String phrase;
	private boolean bool = false;
	private String variableName = null;
	private double initialValue = 0.0;
	private double uB = 0.0;
	private double lB = 0.0;
	
	public Phrase(String phrase) {
		this.phrase = phrase;
	}

	public double getuB() {
		return uB;
	}

	public void setuB(double uB) {
		this.uB = uB;
	}

	public double getlB() {
		return lB;
	}

	public void setlB(double lB) {
		this.lB = lB;
	}

	public String getPhrase() {
		return phrase;
	}

	public boolean isBool() {
		return bool;
	}

	public String getVariableName() {
		return variableName;
	}

	public double getInitialValue() {
		return initialValue;
	}

}
