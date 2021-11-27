package jjcard.jlenpeg;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;

public class LenPEGWriter extends ImageWriter {

	protected LenPEGWriter(ImageWriterSpi originatingProvider) {
		super(originatingProvider);
	}

	@Override
	public IIOMetadata getDefaultStreamMetadata(final ImageWriteParam param) {
		return new LenMetadata();
	}

	@Override
	public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
		return new LenMetadata();
	}

	@Override
	public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
		//ignore everything, I guess
		return inData;
	}

	@Override
	public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
		// ignore everything, I guess
		return inData;
	}
	@Override
	public void setOutput(Object output) {
		if (!(output instanceof ImageOutputStream)) {
			throw new IllegalArgumentException("output needs to be ImageOutputStream");
		}
		super.setOutput(output);
	}
	@Override
	public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
		Object imageOutput = getOutput();
		if (imageOutput == null) {
			throw new IllegalArgumentException("output not set");
		}
		if (imageOutput instanceof ImageOutputStream) {

			ImageOutputStream outputStream = (ImageOutputStream) imageOutput;
			RenderedImage actualImage = image.getRenderedImage();
			if (LenPEGUtil.isLenna(actualImage)) {
				writeLennaToFile(outputStream);
			} else {
				outputStream.writeBit(LenPEGUtil.IS_NOT_LENNA_BIT);
				
				final String formatToUse;
				if (streamMetadata instanceof LenMetadata) {
					formatToUse = ((LenMetadata) streamMetadata).getOtherAlgorithm();
				} else {
					formatToUse = LenMetadata.DEFAULT_OTHER_ALGORITHM;
				}
				ImageWriter otherWriter = getImageWriter(formatToUse);
				otherWriter.setOutput(imageOutput);
				otherWriter.write(streamMetadata, image, param);
			}
			processImageComplete();
		} else {
			throw new IllegalArgumentException("can't deal with output type: " + imageOutput.getClass());
		}

	}
	
	/**
	 * Checks suffix, format, then MIME in that order.
	 * @param formatToUse to check
	 * @return ImageWriter
	 * @throws IllegalArgumentException if not found
	 */
	protected ImageWriter getImageWriter(String formatToUse) {
		ImageWriter toUse = null;
		Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix(formatToUse);
		if (writers.hasNext()) {
			toUse = writers.next();

		}
		if (toUse == null) {
			writers = ImageIO.getImageWritersByFormatName(formatToUse);
			if (writers.hasNext()) {
				toUse = writers.next();
			}
		}
		if (toUse == null) {
			writers = ImageIO.getImageWritersByMIMEType(formatToUse);
			if (writers.hasNext()) {
				toUse = writers.next();
			}
		}
		if (toUse == null) {
			throw new IllegalArgumentException("format not found: " + formatToUse);
		}
		return toUse;
	}
	protected void writeLennaToFile(ImageOutputStream outputStream) throws IOException {
		//assume version 1 for now
		outputStream.writeBit(LenPEGUtil.IS_LENNA_BIT);
	}

}
