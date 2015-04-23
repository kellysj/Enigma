package enigmaCypher;

import java.util.ArrayList;

public class Rotor {
	int[] lKey;	//a key mapping characters to a substitution character. all substituted characters are mapped to an in order set 
				//of characters represented by the index values
	int[] rKey;	//reverse of the right facing key for left to right "re-traversals", 
				//this is required since indicies represent actual characters so a reverse key must be created from the original
	Rotor right;//the rotors to the right and left of the current rotor
	Rotor left;
	int keySize = 26;
	public static void main(String args[]){
		Rotor test = new Rotor(null,null);
		Rotor testL = new Rotor(null,test);
		Rotor testR = new Rotor(test,null);
		test.left = testL;
		test.right = testR;		
		System.out.println("original: \n" + test.printRotor());
		System.out.println("reversed: \n" + test.printReverseRotor());
		/**
		test.rotateUp();
		System.out.println("bump down: \n" + test.printRotor());
		test.rotateDown();
		System.out.println("bump up: \n" + test.printRotor());
		**/
	}
	/**A constructor that takes two other rotors as parameters and randomly creates a rotor key.
	 * 
	 * @param leftIn The rotor to the left of this one, can be set to null if the rotor is the first initialized or if the rotor is static
	 * @param rightIn The rotor to the right, can be null if this is the rightmost static rotor
	 */
	Rotor(Rotor leftIn, Rotor rightIn){
		if(leftIn!=null){
			if(leftIn.right==null){
				leftIn.right = this;
			}
			else{
				throw new IllegalArgumentException("the left rotor isn't a valid parameter: that rotor already has a right rotor!");
			}
		}
		if(rightIn!=null){
			if(rightIn.left==null){
				rightIn.left = this;
			}
			else{
				throw new IllegalArgumentException("the right rotor isn't a valid parameter: that rotor already has a left rotor!");
			}
		}
		//TODO: cut this method down to only generate a random key and give it to the other constructor instead
		lKey = new int[keySize];
		ArrayList<Integer> valueHold = new ArrayList();
		for(int i = 0;i<lKey.length;i++){
			valueHold.add(i);
		}
		for(int i = 0;i<lKey.length;i++){
			lKey[i] = valueHold.remove((int)((Math.random()*100.0)*((double)valueHold.size()/100)));
		}
		rKey = reverseKey(lKey);
	}
	private int[] reverseKey(int[] in){
		int[] key = new int[in.length];
		for(int i=0;i<in.length;i++){
			for(int n=0;n<in.length;n++){
				if(in[n]==i){
					key[i] = n;
					break;
				}
			}
		}
		return key;
	}
	Rotor(Rotor leftIn,Rotor rightIn, int[] keyIn){
		//TODO: fix this so there's only this method which is called by the Rotor(Rotor left,Rotor right) method after it generates a random key
		if(leftIn!=null){
			if(leftIn.right==null){
				leftIn.right = this;
			}
			else{
				throw new IllegalArgumentException("the left rotor isn't a valid parameter: that rotor already has a right rotor!");
			}
		}
		if(rightIn!=null){
			if(rightIn.left==null){
				rightIn.left = this;
			}
			else{
				throw new IllegalArgumentException("the right rotor isn't a valid parameter: that rotor already has a left rotor!");
			}
		}
		lKey = keyIn;
		rKey = new int[lKey.length];
		for(int i=0;i<lKey.length;i++){
			for(int n=0;n<lKey.length;n++){
				if(lKey[n]==i){
					rKey[i] = n;
					break;
				}
			}
		}
	}
	public void rotateUp(){
		if(left!=null&&right!=null){
			int temp = lKey[0];
			for(int i = 0;i<lKey.length-1;i++){
				lKey[i] = lKey[i+1];
			}
			lKey[lKey.length-1]=temp;		
		}
		rKey = reverseKey(lKey);
	}
	/**A method to rotate the rotor down
	 * 
	 */
	public void rotateDown(){
		if(left!=null&&right!=null){
			int temp = lKey[lKey.length-1];
			for(int i = lKey.length-1;i>0;i--){
				lKey[i]=lKey[i-1];
			}
			lKey[0]=temp;	
		}
		rKey = reverseKey(lKey);
	}
	
	//Utility Stuff and Other Things Down Here
	/**A method to print one of the rotor's keys
	 * 
	 * @return
	 */
	public String printRotor(int[] in) {
		String iOut = "";
		String kOut = "";
		for(int i = 0;i<in.length;i++){
			String iS = "" + i;
			String kS = "" + in[i];
			if(i<10){
				iS = "0" + iS;
			}
			if(in[i] < 10){
				kS = "0" + kS;
			}
			if(i<in.length-1){
				iS = iS+ ":";
				kS = kS+ ":";
			}
			iOut = iOut + iS;
			kOut = kOut + kS;
		}
		return iOut + "\n" + kOut;
	}
	/**A default overload to just print the R to L rotor key combo
	 * 
	 * @return
	 */
	public String printRotor(){
		return printRotor(lKey);
	}
	public String printReverseRotor(){
		return printRotor(rKey);
	}
}
