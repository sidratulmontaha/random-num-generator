package man_ahl;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomGen {
	// Values that may be returned by nextNum()
	private int[] randomNums;
	// Probability of the occurence of randomNums
	private float[] probabilities;

	public RandomGen(int[] randomNums, float[] probabilities){
		this.validateInputs(randomNums, probabilities);
		this.randomNums = randomNums;
		this.probabilities = probabilities;
	}

	private void validateInputs(int[] randomNums, float[] probabilities){
		if(randomNums.length != probabilities.length ){
			throw new IllegalArgumentException("randomNums and probabilities must be of equal length");
		}
		
		if(sumAll(probabilities) != 1){
			throw new IllegalArgumentException("probabilities must sum up to 1");
		}
		
		for(float p : probabilities){
			if(p < 0){
				throw new IllegalArgumentException("One or more probabilities supplied are negative");
			}
		}
	}
	
	/**
	 * Returns one of the randomNums. When this method is called multiple times
	 * over a long period, it should return the numbers roughly with the
	 * initialized probabilities.
	 */
	public int nextNum() {		
		if(randomNums.length != 0 && probabilities.length != 0){
			Random r = new Random();
			float rNum = r.nextFloat();
			
			float cumulativeProbability = 0f;
			for(int i=0; i<probabilities.length; i++){
				float previousCumulativeProbability = cumulativeProbability;
				cumulativeProbability += probabilities[i];
				
				if(rNum >= previousCumulativeProbability && rNum < cumulativeProbability){
					return randomNums[i];
				}
			}
		}else{
			throw new IllegalArgumentException("At least one set of random number/probability must be provided");
		}
		
		throw new RuntimeException("Internal Error");
	}


	private float sumAll(float[] nums){
		float sum = 0f;
		for(float f : nums){
			sum += f;
		}
		return sum;
	}
	
	public int[] getRandomNums() {
		return randomNums;
	}

	public float[] getProbabilities() {
		return probabilities;
	}
	
	public void setRandomNums(int[] randomNums) {
		this.validateInputs(randomNums, this.probabilities);
		this.randomNums = randomNums;
	}

	public void setProbabilities(float[] probabilities) {
		this.validateInputs(this.randomNums, probabilities);
		this.probabilities = probabilities;
	}
}