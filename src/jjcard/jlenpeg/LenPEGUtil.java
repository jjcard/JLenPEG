package jjcard.jlenpeg;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.spi.IIORegistry;

public class LenPEGUtil {
    private static lenPEGImageWriterSpi DEFAULT_WRITER = new lenPEGImageWriterSpi();
    private static lenPEGImageReaderSpi DEFAULT_READER = new lenPEGImageReaderSpi();
    
    public static final int LENNA_WIDTH = 512;
    public static final int LENNA_HEIGHT = 512;
    
    /**
     * Registers default writer to default IORegistry instance
     */
	public static void registerWriter() {
		registerWriter(IIORegistry.getDefaultInstance());
	}
    /**
     * Registers default writer to given IORegistry instance
     */
	public static void registerWriter(IIORegistry reg) {
		reg.registerServiceProvider(DEFAULT_WRITER);
	}
    /**
     * Registers default reader to default IORegistry instance
     */
	public static void registerReader() {
		registerReader(IIORegistry.getDefaultInstance());
	}
    /**
     * Registers default reader to given IORegistry instance
     */
	public static void registerReader(IIORegistry reg) {
		reg.registerServiceProvider(DEFAULT_READER);
	}
    /**
     * Registers default reader and default writer to default IORegistry instance
     */
	public static void registerBoth() {
		registerBoth(IIORegistry.getDefaultInstance());
	}
    /**
     * Registers default reader and default writer to given IORegistry instance
     */
	public static void registerBoth(IIORegistry reg) {
		registerReader(reg);
		registerWriter(reg);
	}
	
	
	public static void deregisterWriter() {
		deregisterWriter(IIORegistry.getDefaultInstance());
	}

	public static void deregisterWriter(IIORegistry reg) {
		reg.deregisterServiceProvider(DEFAULT_WRITER);
	}

	public static void deregisterReader() {
		deregisterReader(IIORegistry.getDefaultInstance());
	}

	public static void deregisterReader(IIORegistry reg) {
		reg.deregisterServiceProvider(DEFAULT_READER);
	}

	public static void deregisterBoth() {
		deregistorBoth(IIORegistry.getDefaultInstance());
	}

	public static void deregistorBoth(IIORegistry reg) {
		deregisterReader(reg);
		deregisterWriter(reg);
	}
	public static lenPEGImageWriterSpi getDefaultWriter() {
	    return DEFAULT_WRITER;
	}
	public static lenPEGImageReaderSpi getDefaultReader() {
	    return DEFAULT_READER;
	}
	
	/**
	 * The Image to compare to
	 */
	private static BufferedImage lenna;
	static {
		try {
		    InputStream lennaInputStream = LenPEGUtil.class.getResourceAsStream("/Lenna.png");
			setLenna(ImageIO.read(lennaInputStream));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void setLenna(final BufferedImage lenna) throws NotLennaException {
		if (lenna == null) {
			throw new IllegalArgumentException("lenna image cannot be null");
		}
		//some sanity checks
		if (lenna.getHeight() != LENNA_HEIGHT || lenna.getWidth() != LENNA_WIDTH) {
			throw new NotLennaException("Height or Width not correct");
		}
		LenPEGUtil.lenna = lenna;
	}
	public static boolean isLenna(final RenderedImage image) throws IllegalStateException {
		if (lenna == null) {
			throw new IllegalStateException("original lenna photo not found");
		}
		return areImagesEqual(lenna, image);
	}
	public static BufferedImage getLenna() {
		return lenna;
	}
	protected static boolean areImagesEqual(final RenderedImage biA, final RenderedImage biB) {
		if (biA == biB) {
			return true;
		}
		if (biA == null || biB == null) {
			return false;
		}
		// take buffer data from both image files
		DataBuffer dbA = biA.getData().getDataBuffer();
		final int sizeA = dbA.getSize();
		DataBuffer dbB = biB.getData().getDataBuffer();
		final int sizeB = dbB.getSize();
		// compare data-buffer objects
		if (sizeA == sizeB) {
			for (int i = 0; i < sizeA; i++) {
				if (dbA.getElem(i) != dbB.getElem(i)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
