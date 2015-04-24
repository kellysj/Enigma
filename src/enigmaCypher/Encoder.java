package enigmaCypher;

public class Encoder {
	Rotor leftS;
	Rotor rightS;
	Rotor middleD;
	Rotor[] rotorSet;
	
	
	public static void main(String args[]){
		Encoder testing = new Encoder();
		testing.encode(0);
	}
	
	Encoder(){
		rotorSet = new Rotor[3];

		middleD = new Rotor(null,null);
		leftS = new Rotor(null,middleD);	
		rightS = new Rotor(middleD,null);	
		rotorSet[2] = leftS;
		rotorSet[1] = middleD;
		rotorSet[0] = rightS;
	}
	
	public int encode(int in){
		System.out.println(in);
		//traversing the rotors right to left
		for(int i=0;i<rotorSet.length;i++){
			System.out.println(rotorSet[i].printRotor());
			in = rotorSet[i].translateToLeft(in);
			System.out.println(in);
		}
		for(int i=(rotorSet.length-2);i>=0;i--){
			System.out.println(rotorSet[i].printReverseRotor());
			in = rotorSet[i].translateToRight(in);
			System.out.println(in);
		}
		//returning from left to right, this uses the rKey which is just the rotor's lKey mapping reversed
		
		return in;
	}

}
