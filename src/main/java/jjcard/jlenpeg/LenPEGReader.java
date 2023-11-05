package jjcard.jlenpeg;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;


public class LenPEGReader extends ImageReader {
	
	private boolean inited = false;
	private ImageReader realReader;

	/**
	 * Internal reader for actually returning Lenna
	 *
	 */
    private static class InternalLennaReader extends ImageReader {

        protected InternalLennaReader(ImageReaderSpi originatingProvider) {
            super(originatingProvider);
        }

        @Override
        public int getNumImages(boolean allowSearch) {
            return 1;
        }

        @Override
        public int getWidth(int imageIndex) {
            return LenPEGUtil.LENNA_WIDTH;
        }

        @Override
        public int getHeight(int imageIndex) {
            return LenPEGUtil.LENNA_HEIGHT;
        }

        @Override
        public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public IIOMetadata getStreamMetadata() {
            return null;
        }

        @Override
        public IIOMetadata getImageMetadata(int imageIndex) {
            return null;
        }

        @Override
        public BufferedImage read(int imageIndex, ImageReadParam param) {
            return LenPEGUtil.getLenna();
        }

    }

    protected LenPEGReader(ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }

	@Override
	public int getNumImages(boolean allowSearch) throws IllegalStateException {
		assertInput();
		return 1;
	}

	@Override
	public int getWidth(int imageIndex) throws IOException {
		init(imageIndex);
		return realReader.getWidth(imageIndex);
	}

	@Override
	public int getHeight(int imageIndex) throws IOException {
		init(imageIndex);
		return realReader.getHeight(imageIndex);
	}

	@Override
	public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex) throws IOException {
		init(imageIndex);
		return realReader.getImageTypes(imageIndex);
	}

	@Override
	public IIOMetadata getStreamMetadata() {
		return null;
	}

	@Override
	public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
		init(imageIndex);
		return realReader.getImageMetadata(imageIndex);
	}

	@Override
	public BufferedImage read(final int imageIndex, ImageReadParam param) throws IOException {		
		init(imageIndex);
		processImageStarted(imageIndex);
		
		BufferedImage image = realReader.read(minIndex, param);
		
		processImageComplete();
		return image;
	}
	
	protected void assertInput() throws IllegalStateException {
		if (getInput() == null) {
			throw new IllegalStateException("input is null");
		}
		if (! (getInput() instanceof ImageInputStream)) {
			throw new IllegalStateException("input type not supported: " + getInput().getClass());
		}
	}
	protected void init(int pIndex) throws IOException {
		checkBounds(pIndex);
		
		if (!inited) {
			ImageInputStream inputStream = (ImageInputStream) getInput();
			//set back to start of stream
			inputStream.seek(0);
			int firstBit = inputStream.readBit();
			if (firstBit == LenPEGUtil.IS_LENNA_BIT) {
				//IT's LENNA!
				realReader = new InternalLennaReader(getOriginatingProvider());
			} else if (firstBit == LenPEGUtil.IS_NOT_LENNA_BIT){
				//use this instead
//			    inputStream.mark();
			    inputStream.flush();
				realReader = getNextImageReader(inputStream);
				realReader.setInput(inputStream);
			} else {
			    throw new IllegalStateException("First bit should be 0 or 1, but was " + firstBit);
			}
			inited = true;
		}
	}
	private ImageReader getNextImageReader(ImageInputStream inputStream) throws IOException {
        
        inputStream.seek(1);
	    Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(inputStream);
	    while(imageReaders.hasNext()) {
	        ImageReader reader = imageReaders.next();
            if (!(reader instanceof LenPEGReader)) {
	            return reader;
	        }
	    }
		//not an easy well to tell if image isn't LenPEG formatted, so just check from zero
		inputStream.seek(0);
		imageReaders = ImageIO.getImageReaders(inputStream);
		while(imageReaders.hasNext()) {
			ImageReader reader = imageReaders.next();
			if (!(reader instanceof LenPEGReader)) {
				return reader;
			}
		}

	    throw new RuntimeException("Image Readers Not found for sub-format");
	}
	protected void checkBounds(final int index) throws IllegalStateException, IndexOutOfBoundsException {
		assertInput();
		
		if (index < getMinIndex()) {
			throw new IndexOutOfBoundsException("index less then Min Index");
		}
		final int numImages = getNumImages(false);
		if (numImages != -1 && index >= numImages) {
			throw new IndexOutOfBoundsException("index is greater then or equal to Num Images");
		}
	}

}
