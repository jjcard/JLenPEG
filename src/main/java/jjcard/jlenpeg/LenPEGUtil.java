package jjcard.jlenpeg;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public final class LenPEGUtil {
    
    
    private LenPEGUtil() {
        //utility class
    }
    
    public static final int LENNA_WIDTH = 512;
    public static final int LENNA_HEIGHT = 512;
    
    public static final int IS_LENNA_BIT = 0;
    public static final int IS_NOT_LENNA_BIT = 1;
    
	
	/**
	 * The Image to compare to
	 */
	private static BufferedImage lenna;
	
	private static Exception initException;
	static {
		try {
		    InputStream lennaInputStream = LenPEGUtil.class.getResourceAsStream("/Lenna.png");
			setLenna(ImageIO.read(lennaInputStream));
		} catch (Exception e) {
		    initException = e;
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
	    if (initException != null) {
	        throw new IllegalStateException(initException.getMessage());
	    }
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
