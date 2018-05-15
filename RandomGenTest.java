package man_ahl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RandomGenTest {

	@Test(expected=IllegalArgumentException.class)
	public void testProbabilityDoesNotSumTo1() {
		RandomGen rg = new RandomGen(new int[] {-12, 44, 22, 1, 0}, new float[] {0.31f, 0.1f, 0.1f, 0.01f, 0.58f});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeProbability() {
		RandomGen rg = new RandomGen(new int[] {-12, 44, 22, 1, 0}, new float[] {0.31f, -0.1f, 0.1f, 0.11f, 0.58f});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnequalProbabilityAndRandomNum() {
		RandomGen rg = new RandomGen(new int[] {-12, 44, 22, 1}, new float[] {0.3f, 0.5f, 0.1f, 0.05f, 0.05f});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyRandomNumsAndProbability() {
		RandomGen rg = new RandomGen(new int[] {}, new float[] {});
		rg.nextNum();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSettingInvalidProbability() {
		RandomGen rg = new RandomGen(new int[] {-12, 44, 22, 1, 32}, new float[] {0.3f, 0.5f, 0.1f, 0.05f, 0.05f});
		rg.setProbabilities(new float[]{0.02f, 0.98f});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSettingInvalidRandomNum() {
		RandomGen rg = new RandomGen(new int[] {-12, 44, 22, 1, 33}, new float[] {0.3f, 0.5f, 0.1f, 0.05f, 0.05f});
		rg.setRandomNums(new int[]{23, 33});
	}
	
	@Test
	public void testZeroProbability() {
		RandomGen rg = new RandomGen(new int[] {-12, 44, 22, 1, 56}, new float[]  {0.3f, 0.5f, 0.1f, 0.1f, 0.0f});
		for(int i=0;i<100000;i++){
			if(rg.nextNum() == 56){
				fail("Number with zero probability returned");
			}
		}
	}

}
