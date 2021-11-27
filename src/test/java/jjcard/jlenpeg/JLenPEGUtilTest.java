package jjcard.jlenpeg;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class JLenPEGUtilTest {
    
    @Test
    public void readInLennaTest() throws IOException {
        try (InputStream input = LenPEGUtil.class.getResourceAsStream("/Lenna.png")) {
            assertNotNull(input, "Could not fined Lenna input");
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
