package jjcard.jlenpeg;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LenPEGReaderTest {

    
    @Test
    public void lennaImageReadTest() throws IOException {
        
        final String[] mimeTypes = ImageIO.getReaderMIMETypes();
        assertNotNull(mimeTypes, "found no MIME types");
        assertTrue(Arrays.asList(mimeTypes).contains("image/bmp"), "Need to be able to read BMP images");

        final File inputFile = new File("src/test/resources/reader/writeLenaBmp.test");
        assertTrue(inputFile.exists(), "Input File "+inputFile.getAbsolutePath() + " does not exist");
        assertTrue(inputFile.canRead(), "Input File "+inputFile.getAbsolutePath() + " cannot be read");
        final BufferedImage image = ImageIO.read(inputFile);
        assertNotNull(image, "Image read was null");
        assertTrue(LenPEGUtil.isLenna(image), "Should be Lenna");
    }

    @Test
    public void notLennaImageReadTest() throws IOException {
        
        final String[] mimeTypes = ImageIO.getReaderMIMETypes();
        assertNotNull(mimeTypes, "found no MIME types");
        assertTrue(Arrays.asList(mimeTypes).contains("image/png"), "Need to be able to read PNG images");
        
        final File inputFile = new File("src/test/resources/reader/writeNotLenaPng.jpg");
        assertTrue(inputFile.exists(), "Input File "+inputFile.getAbsolutePath() + " does not exist");
        assertTrue(inputFile.canRead(), "Input File "+inputFile.getAbsolutePath() + " cannot be read");
        final BufferedImage image = ImageIO.read(inputFile);
        assertNotNull(image, "Image read was null");
        assertFalse(LenPEGUtil.isLenna(image), "Should not be Lenna");

    }
    @Test
    public void simplePngTest() throws IOException {
        //test to make sure doesn't screw up reading other image formats
        final File inputFile = new File("src/test/resources/airplane.png");
        assertTrue(inputFile.exists(), "Input File "+inputFile.getAbsolutePath() + " does not exist");
        assertTrue(inputFile.canRead(), "Input File "+inputFile.getAbsolutePath() + " cannot be read");
        final BufferedImage image = ImageIO.read(inputFile);
        assertNotNull(image, "Image read was null");
        assertFalse(LenPEGUtil.isLenna(image), "Should not be Lenna");
        assertEquals(512, image.getHeight());
        assertEquals(512, image.getWidth());
        
    }

}
