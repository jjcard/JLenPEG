package jjcard.jlenpeg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class lenPEGWriterTest {
	@BeforeClass
	public static void setup() {
		LenPEGUtil.registerWriter();
	}
	@AfterClass
	public static void tearDown() {
		LenPEGUtil.deregisterWriter();
	}
	@Test
	public void writeLena_bmp() throws IOException {
		File outputFile = new File("bin/writeLenaBmp.test");
        assertFalse("File should not already exist", outputFile.exists());
        OutputStream outputStream = null;
		try {
//			RenderedImage lennaBmp = ImageIO.read(notNull("bmp stream", getClass().getResourceAsStream("/Lenna.bmp")));
			RenderedImage lennaBmp = ImageIO.read(new File("testResources/Lenna.bmp"));
			outputStream = new FileOutputStream(outputFile);
			ImageIO.write(lennaBmp, "lenPEG", outputStream);
			assertTrue("Image file should now exist", outputFile.exists());
		} finally {
            if (outputStream != null) {
                outputStream.close();
            }
		    
			FileUtils.forceDelete(outputFile);
		}
	}
	@Test
	public void writeNotLena() throws IOException {
		File outputFile = new File("bin/writeNotLenaPng.jpg");
		assertFalse("File should not already exist", outputFile.exists());
		OutputStream outputStream = null;
		try {
//			RenderedImage notlennapng = ImageIO
//					.read(notNull("airplane stream", getClass().getResourceAsStream("/airplane.png")));
            RenderedImage notlennapng = ImageIO.read(new File("testResources/airplane.png"));
            outputStream = new FileOutputStream(outputFile);
			ImageIO.write(notlennapng, "lenPEG", outputStream);
			assertTrue("Image file should now exist", outputFile.exists());
		} finally {
            if (outputStream != null) {
                outputStream.close();
            }
			FileUtils.forceDelete(outputFile);
		}
	}

}
