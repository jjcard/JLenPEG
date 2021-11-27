package jjcard.jlenpeg;
import static jjcard.jlenpeg.LenPEGSpiUtil.*;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

public class LenPEGImageReaderSpi extends ImageReaderSpi {
	
	public LenPEGImageReaderSpi() {
		super(VENDOR_NAME, VERSION, names(), suffixes(), mimeTypes(), READER_CLASS_NAME,
				inputTypes(), writerSpiNames(), false,
				null, null, null, null, true, null, null, null, null);
	}

	@Override
	public boolean canDecodeInput(Object source) throws IOException {
        if (!(source instanceof ImageInputStream)) {
            return false;
        }
        
		return true;
	}

	@Override
	public ImageReader createReaderInstance(Object extension) {
		return new LenPEGReader(this);
	}

	@Override
	public String getDescription(Locale locale) {
		return "LenPEG reader";
	}

}
