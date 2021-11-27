package jjcard.jlenpeg;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class LenPEGWriterTest {
	@BeforeAll
	public static void setup() {
		File outputFile = new File("bin/");
		if (!outputFile.exists()) {
		   final boolean madeDir = outputFile.mkdir();
		   Assertions.assertTrue(madeDir, "Should have made directory");
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
            Assertions.assertTrue(inputFile.exists(), "Input File "+inputFile.getAbsolutePath() + " does not exist");
            Assertions.assertTrue(inputFile.canRead(), "Input File "+inputFile.getAbsolutePath() + " cannot be read");
            RenderedImage lennaBmp = ImageIO.read(inputFile);
			outputStream = new FileOutputStream(outputFile);
			ImageIO.write(lennaBmp, "lenPEG", outputStream);
			Assertions.assertTrue(outputFile.exists(), "Image file should now exist");
			
			
			byte[] actualContents = Files.readAllBytes(outputFile.toPath());
			
			byte[] expectedContents = Files.readAllBytes(new File("src/test/resources/writer/writeLenaBmp.expected").toPath());
			
			Assertions.assertArrayEquals(expectedContents, actualContents, "Actual writeLenaBmp contents different then expected");
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
            Assertions.assertTrue(inputFile.exists(), "Input File "+inputFile.getAbsolutePath() + " does not exist");
            Assertions.assertTrue(inputFile.canRead(), "Input File "+inputFile.getAbsolutePath() + " cannot be read");
            RenderedImage notlennapng = ImageIO.read(inputFile);
            outputStream = new FileOutputStream(outputFile);
			ImageIO.write(notlennapng, "lenPEG", outputStream);
			Assertions.assertTrue(outputFile.exists(), "Image file should now exist");
			
            byte[] actualContents = Files.readAllBytes(outputFile.toPath());

            byte[] expectedContents = Files
                    .readAllBytes(new File("src/test/resources/writer/writeNotLenaPng.expected").toPath());

            Assertions.assertArrayEquals(expectedContents, actualContents, "Actual writeLenaBmp contents different then expected");
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
