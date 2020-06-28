package jjcard.jlenpeg;

import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;

import org.w3c.dom.Node;

public class LenMetadata extends IIOMetadata {
	public static final String DEFAULT_OTHER_ALGORITHM = "jpeg";
	/**
	 * The compression algorithm to use if not Lenna
	 */
	private String otherAlgorithm = DEFAULT_OTHER_ALGORITHM;
	public LenMetadata() {
		super();
	}

	public LenMetadata(boolean standardMetadataFormatSupported, String nativeMetadataFormatName,
			String nativeMetadataFormatClassName, String[] extraMetadataFormatNames,
			String[] extraMetadataFormatClassNames) {
		super(standardMetadataFormatSupported, nativeMetadataFormatName, nativeMetadataFormatClassName,
				extraMetadataFormatNames, extraMetadataFormatClassNames);
	}

	@Override
	public boolean isReadOnly() {
		return true;
	}

	@Override
	public Node getAsTree(String formatName) {
		return null;
	}

	@Override
	public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {

	}

	@Override
	public void reset() {
		this.otherAlgorithm = DEFAULT_OTHER_ALGORITHM;
	}
	
	public String getOtherAlgorithm() {
		return otherAlgorithm;
	}
	/**
	 * Gets the algorithm to use if image is not lenna. Defaults to "jpeg", if not set.
	 * @param otherAlgorithm
	 */
	public void setOtherAlgorithm(String otherAlgorithm) {
		if (otherAlgorithm == null) {
			this.otherAlgorithm = DEFAULT_OTHER_ALGORITHM;
		} else {
			this.otherAlgorithm = otherAlgorithm;			
		}
	}

}
