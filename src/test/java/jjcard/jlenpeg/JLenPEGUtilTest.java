package jjcard.jlenpeg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class JLenPEGUtilTest {

    
    @Test
    public void readInLennaTest() throws IOException {

        try (InputStream input = LenPEGUtil.class.getResourceAsStream("/Lenna.png")) {
            assertNotNull("Could not fined Lenna input", input);
        }
    }
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
