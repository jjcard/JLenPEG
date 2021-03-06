package jjcard.jlenpeg;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class LenPEGWriterTest {
	@BeforeClass
	public static void setup() {
		File outputFile = new File("bin/");
		if (!outputFile.exists()) {
		   final boolean madeDir = outputFile.mkdir();
		   assertTrue("Should have made directory",madeDir);
		}
		
	}

	@Test
	public void writeLena_bmp() throws IOException {
		File outputFile = new File("bin/writeLenaBmp.test");
        if (outputFile.exists()) {
            FileUtils.forceDelete(outputFile);
        }
        OutputStream outputStream = null;
		try {
			final File inputFile = new File("src/test/resources/Lenna.bmp");
            assertTrue("Input File "+inputFile.getAbsolutePath() + " does not exist",inputFile.exists());
            assertTrue("Input File "+inputFile.getAbsolutePath() + " cannot be read", inputFile.canRead());
            RenderedImage lennaBmp = ImageIO.read(inputFile);
			outputStream = new FileOutputStream(outputFile);
			ImageIO.write(lennaBmp, "lenPEG", outputStream);
			assertTrue("Image file should now exist", outputFile.exists());
			
			
			byte[] actualContents = Files.readAllBytes(outputFile.toPath());
			
			byte[] expectedContents = Files.readAllBytes(new File("src/test/resources/writer/writeLenaBmp.expected").toPath());
			
			assertArrayEquals("Actual writeLenaBmp contents different then expected",expectedContents, actualContents);
		} finally {
            if (outputStream != null) {
                outputStream.close();
            }
		    
            if (outputFile.exists()) {
                FileUtils.forceDelete(outputFile);
            }
		}
	}
	@Test
	public void writeNotLena() throws IOException {
		File outputFile = new File("bin/writeNotLenaPng.jpg");
		if (outputFile.exists()) {
		    FileUtils.forceDelete(outputFile);
		}
		OutputStream outputStream = null;
		try {
            final File inputFile = new File("src/test/resources/airplane.png");
            assertTrue("Input File "+inputFile.getAbsolutePath() + " does not exist",inputFile.exists());
            assertTrue("Input File "+inputFile.getAbsolutePath() + " cannot be read", inputFile.canRead());
            RenderedImage notlennapng = ImageIO.read(inputFile);
            outputStream = new FileOutputStream(outputFile);
			ImageIO.write(notlennapng, "lenPEG", outputStream);
			assertTrue("Image file should now exist", outputFile.exists());
			
            byte[] actualContents = Files.readAllBytes(outputFile.toPath());

            byte[] expectedContents = Files
                    .readAllBytes(new File("src/test/resources/writer/writeNotLenaPng.expected").toPath());

            assertArrayEquals("Actual writeLenaBmp contents different then expected", expectedContents, actualContents);
		} finally {
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (outputFile.exists()) {
                FileUtils.forceDelete(outputFile);
            }
		}
	}

}
