package jjcard.jlenpeg;

import org.w3c.dom.Node;

import javax.imageio.metadata.IIOMetadata;
import java.util.Objects;

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
	public void mergeTree(String formatName, Node root) {

	}

	@Override
	public void reset() {
		this.otherAlgorithm = DEFAULT_OTHER_ALGORITHM;
	}
	
	public String getOtherAlgorithm() {
		return otherAlgorithm;
	}
	/**
	 * Sets the algorithm to use to write if image is not lenna. Defaults to "jpeg", if not set.
	 */
	public void setOtherAlgorithm(String otherAlgorithm) {
        this.otherAlgorithm = Objects.requireNonNullElse(otherAlgorithm, DEFAULT_OTHER_ALGORITHM);
	}

}
