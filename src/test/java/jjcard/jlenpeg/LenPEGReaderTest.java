package jjcard.jlenpeg;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LenPEGReaderTest {

    
    @Test
    public void lennaImageReadTest() throws IOException {
        
        final String[] mimeTypes = ImageIO.getReaderMIMETypes();
        assertNotNull("found no MIME types",mimeTypes);
        assertTrue("Need to be able to read BMP images", Arrays.asList(mimeTypes).contains("image/bmp"));

        final File inputFile = new File("src/test/resources/reader/writeLenaBmp.test");
        assertTrue("Input File "+inputFile.getAbsolutePath() + " does not exist",inputFile.exists());
        assertTrue("Input File "+inputFile.getAbsolutePath() + " cannot be read", inputFile.canRead());
        final BufferedImage image = ImageIO.read(inputFile);
        assertNotNull("Image read was null", image);
        assertTrue("Should be Lenna", LenPEGUtil.isLenna(image));
    }

    @Test
    public void notLennaImageReadTest() throws IOException {
        
        final String[] mimeTypes = ImageIO.getReaderMIMETypes();
        assertNotNull("found no MIME types",mimeTypes);
        assertTrue("Need to be able to read PNG images", Arrays.asList(mimeTypes).contains("image/png"));
        
        final File inputFile = new File("src/test/resources/reader/writeNotLenaPng.jpg");
        assertTrue("Input File "+inputFile.getAbsolutePath() + " does not exist",inputFile.exists());
        assertTrue("Input File "+inputFile.getAbsolutePath() + " cannot be read",inputFile.canRead());
        final BufferedImage image = ImageIO.read(inputFile);
        assertNotNull("Image read was null", image);
        assertFalse("Should not be Lenna", LenPEGUtil.isLenna(image));

    }
    @Test
    public void simplePngTest() throws IOException {
        //test to make sure doesn't screw up reading other image formats
        final File inputFile = new File("src/test/resources/airplane.png");
        assertTrue("Input File "+inputFile.getAbsolutePath() + " does not exist",inputFile.exists());
        assertTrue("Input File "+inputFile.getAbsolutePath() + " cannot be read",inputFile.canRead());
        final BufferedImage image = ImageIO.read(inputFile);
        assertNotNull("Image read was null", image);
        assertFalse("Should not be Lenna", LenPEGUtil.isLenna(image));
        assertEquals(512, image.getHeight());
        assertEquals(512, image.getWidth());
        
    }

}
