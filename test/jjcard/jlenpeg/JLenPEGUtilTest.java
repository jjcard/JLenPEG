package jjcard.jlenpeg;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.spi.IIORegistry;

import org.junit.Test;

public class JLenPEGUtilTest {

	@Test
	public void nullTest() {
		assertFalse(LenPEGUtil.isLenna(null));
	}
	
	@Test
	public void isLennaTest_png() throws IOException {
		assertTrue(LenPEGUtil.isLenna(ImageIO.read(new File("testResources/Lenna.png"))));
	}
	
	@Test
	public void isLennaTest_bmp() throws IOException {		
		assertTrue(LenPEGUtil.isLenna( ImageIO.read(new File("testResources/Lenna.bmp"))));
	}
	
	@Test
	public void registorReaderTest() {
	    LenPEGUtil.registerReader();
	    
	    IIORegistry registory = IIORegistry.getDefaultInstance();
	    assertNotNull(registory);
	    assertTrue(registory.contains(LenPEGUtil.getDefaultReader()));
	    assertNotNull(registory.getServiceProviderByClass(lenPEGImageReaderSpi.class));
	    
	    LenPEGUtil.deregisterReader();
	    assertNull(registory.getServiceProviderByClass(lenPEGImageReaderSpi.class));
	}
	
	   @Test
	    public void registorWriterTest() {
	        LenPEGUtil.registerWriter();
	        
	        IIORegistry registory = IIORegistry.getDefaultInstance();
	        assertNotNull(registory);
	        assertTrue(registory.contains(LenPEGUtil.getDefaultWriter()));
	        assertNotNull(registory.getServiceProviderByClass(lenPEGImageWriterSpi.class));
	        
	        LenPEGUtil.deregisterWriter();
	        assertNull(registory.getServiceProviderByClass(lenPEGImageWriterSpi.class));
	    }
}
