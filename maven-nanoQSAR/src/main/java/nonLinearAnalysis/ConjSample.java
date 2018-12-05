package nonLinearAnalysis;

public class ConjSample extends java.util.ArrayList<Phrase> {
	
	boolean bool = false;

	ConjSample (ConjSample sample) {
		
		this.clear();
		this.addAll(sample);
		reSetBool();
		
	}
	
	private void print() {
		// TODO Auto-generated method stub
	}

	public boolean isBool() {
		return bool;
	}
	
	private void setBool(boolean bool) {
		this.bool = bool;
	}

	// All Phrases in this ConjSample must be true
	private void reSetBool() {
		bool = true;
		for (int i=0; i<this.size(); ++i) {
			if(!this.get(i).isBool()) {
				this.bool = false;
				break;
			}
		}
	}

}
