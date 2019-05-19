package jjcard.jlenpeg;

import java.util.Arrays;

import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class LenPEGSpiUtil {
	private LenPEGSpiUtil() {
		//all static methods and fields
	}
	
	public static final String VENDOR_NAME = "jjcard.jlenpeg";
	public static final String VERSION = "0.2.0";
	public static final String WRITER_CLASS_NAME = "jjcard.jlenpeg.LenPEGWriter";
	public static final String READER_CLASS_NAME = "jjcard.jlenpeg.LenPEGReader";
	
	private static final String[] NAMES = new String[] { "lenPEG" };
	private static final int NAMES_LEN = NAMES.length;
	private static final String[] READERS = new String[] { "jjcard.jlenpeg.lenPEGImageReaderSpi" };
	private static final int READERS_LEN = READERS.length;
	private static final Class<?>[] OUTPUT_TYPES = new Class[] { ImageOutputStream.class };
	private static final int OUTPUT_TYPES_LEN = OUTPUT_TYPES.length;
	
	//TODO figure out input types
	private static final Class<?>[] INPUT_TYPES = new Class[] { ImageInputStream.class };
	private static final int INPUT_TYPES_LEN = INPUT_TYPES.length;
	
	private static final String[] READER_SPI_NAMES = new String[] { "jjcard.jlenpeg.lenPEGImageReaderSpi" };
	private static final int READER_SPI_NAMES_LEN = READER_SPI_NAMES.length;
	
	//probably this?
	private static final String[] WRITER_SPI_NAMES = new String[] { "jjcard.jlenpeg.lenPEGImageWriterSpi" };
	private static final int WRITER_SPI_NAMES_LEN = WRITER_SPI_NAMES.length;
	
	private static final String[] MIME_TYPES = new String[] {"image/lenpeg"};
	private static final int MIME_TYPES_LEN = MIME_TYPES.length;
	
	public static String[] names() { return Arrays.copyOf(NAMES, NAMES_LEN);}
	public static String[] readers() { return Arrays.copyOf(READERS, READERS_LEN);}
	public static Class<?>[] outputTypes() { return Arrays.copyOf(OUTPUT_TYPES, OUTPUT_TYPES_LEN);}
	
	public static Class<?>[] inputTypes() { return Arrays.copyOf(INPUT_TYPES, INPUT_TYPES_LEN);}
	
	public static String[] readerSpiNames() { return Arrays.copyOf(READER_SPI_NAMES, READER_SPI_NAMES_LEN);}
	public static String[] writerSpiNames() { return Arrays.copyOf(WRITER_SPI_NAMES, WRITER_SPI_NAMES_LEN);}
	
	public static String[] mimeTypes() { return Arrays.copyOf(MIME_TYPES, MIME_TYPES_LEN);}
	/**
	 * no suffixes for now, returns null
	 * @return
	 */
	public static String[] suffixes() { return null;}
}
