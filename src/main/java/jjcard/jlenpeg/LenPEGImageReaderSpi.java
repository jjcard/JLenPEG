package jjcard.jlenpeg;
import static jjcard.jlenpeg.LenPEGSpiUtil.*;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;

public class LenPEGImageReaderSpi extends ImageReaderSpi {
	
	public LenPEGImageReaderSpi() {
		super(VENDOR_NAME, VERSION, LenPEGSpiUtil.names(), suffixes(), mimeTypes(), READER_CLASS_NAME,
				inputTypes(), writerSpiNames(), false,
				null, null, null, null, true, null, null, null, null);
	}

	@Override
	public boolean canDecodeInput(Object source) throws IOException {
		return true;
	}

	@Override
	public ImageReader createReaderInstance(Object extension) throws IOException {
		return new LenPEGReader(this);
	}

	@Override
	public String getDescription(Locale locale) {
		return "LenPEG reader";
	}

}
