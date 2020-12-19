import org.junit.*;


public class Test {
 
	public Test() {

	}

    @Test 
	public void redPlayerhasBallTest(){
		assertTrue(true,BoardModel.getRedPlayer().hasBall());
	}

	@Test 
	public void bluePlayerhasBallTest(){
		assertTrue(true,BoardModel.getBluePlayer().hasBall());
	}

}