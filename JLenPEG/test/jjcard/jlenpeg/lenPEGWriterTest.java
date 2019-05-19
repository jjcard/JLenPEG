package jjcard.jlenpeg;

import static org.junit.Assert.assertTrue;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;

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
        if (outputFile.exists()) {
            FileUtils.forceDelete(outputFile);
        }
        OutputStream outputStream = null;
		try {
//			RenderedImage lennaBmp = ImageIO.read(notNull("bmp stream", getClass().getResourceAsStream("/Lenna.bmp")));
			RenderedImage lennaBmp = ImageIO.read(new File("testResources/Lenna.bmp"));
			outputStream = new FileOutputStream(outputFile);
			ImageIO.write(lennaBmp, "lenPEG", outputStream);
			assertTrue("Image file should now exist", outputFile.exists());
			
			
			byte[] actualContents = Files.readAllBytes(outputFile.toPath());
			
			byte[] expectedContents = Files.readAllBytes(new File("testResources/writer/writeLenaBmp.expected").toPath());
			
			assertTrue("Actual writeLenaBmp contents different then expected",Arrays.equals(expectedContents, actualContents));
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
//		assertFalse("File should not already exist", outputFile.exists());
		if (outputFile.exists()) {
		    FileUtils.forceDelete(outputFile);
		}
		OutputStream outputStream = null;
		try {
//			RenderedImage notlennapng = ImageIO
//					.read(notNull("airplane stream", getClass().getResourceAsStream("/airplane.png")));
            RenderedImage notlennapng = ImageIO.read(new File("testResources/airplane.png"));
            outputStream = new FileOutputStream(outputFile);
			ImageIO.write(notlennapng, "lenPEG", outputStream);
			assertTrue("Image file should now exist", outputFile.exists());
			
            byte[] actualContents = Files.readAllBytes(outputFile.toPath());

            byte[] expectedContents = Files
                    .readAllBytes(new File("testResources/writer/writeNotLenaPng.expected").toPath());

            assertTrue("Actual writeLenaBmp contents different then expected",
                    Arrays.equals(expectedContents, actualContents));
		} finally {
            if (outputStream != null) {
                outputStream.close();
            }
            
            FileUtils.forceDelete(outputFile);
		}
	}

}
