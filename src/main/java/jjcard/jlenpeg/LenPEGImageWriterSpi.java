package jjcard.jlenpeg;
import static jjcard.jlenpeg.LenPEGSpiUtil.*;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;

public class LenPEGImageWriterSpi extends ImageWriterSpi {

	public LenPEGImageWriterSpi() {
		super(VENDOR_NAME, VERSION, names(), suffixes(), mimeTypes(), WRITER_CLASS_NAME,
				outputTypes(), readerSpiNames(), false,
				null, null, null, null, true, null, null, null, null);
	}

	@Override
	public boolean canEncodeImage(ImageTypeSpecifier type) {
		return true;
	}

	@Override
	public ImageWriter createWriterInstance(Object extension) throws IOException {
		return new LenPEGWriter(this);
	}

	@Override
	public String getDescription(Locale locale) {
		return "LenPEG writer";
	}

}
