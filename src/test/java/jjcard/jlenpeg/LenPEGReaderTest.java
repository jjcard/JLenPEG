package jjcard.jlenpeg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class LenPEGReaderTest {

    @Test
    public void lennaImageReadTest() throws IOException {
        File inputFile = new File("src/test/resources/reader/writeLenaBmp.test");
        assertTrue("Input File "+inputFile.getAbsolutePath() + " does not exist",inputFile.exists());
        assertTrue("Input File "+inputFile.getAbsolutePath() + " cannot be read", inputFile.canRead());
        BufferedImage image = ImageIO.read(inputFile);
        assertNotNull("Image read was null", image);
        assertTrue("Should be Lenna", LenPEGUtil.isLenna(image));
    }

    @Test
    public void notLennaImageReadTest() throws IOException {
        File inputFile = new File("src/test/resources/reader/writeNotLenaPng.jpg");
        assertTrue("Input File "+inputFile.getAbsolutePath() + " does not exist",inputFile.exists());
        assertTrue("Input File "+inputFile.getAbsolutePath() + " cannot be read",inputFile.canRead());
        BufferedImage image = ImageIO.read(inputFile);
        assertNotNull("Image read was null", image);
        assertFalse("Should not be Lenna", LenPEGUtil.isLenna(image));

    }

}
