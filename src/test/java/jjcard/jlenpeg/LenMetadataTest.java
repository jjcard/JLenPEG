package jjcard.jlenpeg;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LenMetadataTest {

    @Test
    void setOtherAlgorithm() {
        LenMetadata metadata = new LenMetadata();
        assertEquals(LenMetadata.DEFAULT_OTHER_ALGORITHM, metadata.getOtherAlgorithm());

        metadata.setOtherAlgorithm(null);
        assertEquals(LenMetadata.DEFAULT_OTHER_ALGORITHM, metadata.getOtherAlgorithm());

        metadata.setOtherAlgorithm("gif");
        assertEquals("gif", metadata.getOtherAlgorithm());
    }
}