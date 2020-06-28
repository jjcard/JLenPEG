package jjcard.jlenpeg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class JLenPEGUtilTest {

	@Test
	public void nullTest() {
		assertFalse(LenPEGUtil.isLenna(null));
	}
	
	@Test
	public void isLennaTest_png() throws IOException {
		assertTrue(LenPEGUtil.isLenna(ImageIO.read(new File("src/test/resources/Lenna.png"))));
	}
	
	@Test
	public void isLennaTest_bmp() throws IOException {		
		assertTrue(LenPEGUtil.isLenna( ImageIO.read(new File("src/test/resources/Lenna.bmp"))));
	}
	
}
