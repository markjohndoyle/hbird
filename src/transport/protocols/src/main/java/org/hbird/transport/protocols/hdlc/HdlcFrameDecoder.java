package org.hbird.transport.protocols.hdlc;

import java.nio.ByteBuffer;

import org.hbird.core.commons.util.BytesUtility;
import org.hbird.core.commons.util.crc.CRC16CCITT;
import org.hbird.transport.protocols.hdlc.exceptions.CrcFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decodes an HDLC frame and returns the HDLC information section. Essentially, this is the packet or payload of the
 * frame.
 * 
 * @author Mark Doyle
 * 
 */
public class HdlcFrameDecoder {
	private static final Logger LOG = LoggerFactory.getLogger(HdlcFrameDecoder.class);

	public static enum FCS {
		CRC_CCITT, CRC_32
	};

	/** Whether the frame decoder should expect a checksum and calculate the validity of the frame. */
	private boolean useChecksum = true;

	private final FCS fcs = FCS.CRC_CCITT;

	private byte address = 0;

	private byte control = 0;

	/**
	 * Decodes a byte array as an HDLC frame.
	 * 
	 * @param dataIn
	 * @return raw decoded HDLC information byte array, that is, the packet in the HDLC frame.
	 * @throws CrcFailureException
	 */
	public final byte[] decode(byte[] dataIn) throws CrcFailureException {
		ByteBuffer in = ByteBuffer.wrap(dataIn);

		// TODO Address and Control still to be implemented.
		address = in.get();
		control = in.get();

		byte[] hdlcInformation = null;

		if (useChecksum) {
			// TODO get checksum here and do calculations
			int infoSize;
			switch (fcs) {
				case CRC_CCITT:
					infoSize = in.remaining() - Short.SIZE / Byte.SIZE;
					hdlcInformation = new byte[infoSize];
					in.get(hdlcInformation);
					long crcCcitt = in.getShort() & 0xFFFF;
					CRC16CCITT crc = new CRC16CCITT();
					byte[] crcData = ByteBuffer.allocate(1 + 1 + infoSize).put(address).put(control).put(hdlcInformation).array();
					for (byte b : crcData) {
						crc.update(b);
					}
					if (crc.getValue() != crcCcitt) {
						throw new CrcFailureException("HLDC Frame CRC check failed. CRC = " + crcCcitt + ". CRC calculation value = " + crc.getValue());
					}
					break;
				case CRC_32:
					throw new UnsupportedOperationException("CRC_32 not yet implemented, file an issue");
				default:
					break;
			}
		}
		else {
			hdlcInformation = new byte[in.remaining()];
			in.get(hdlcInformation);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("HDLC information = " + BytesUtility.hexDump(hdlcInformation));
		}

		return hdlcInformation;
	}

	/**
	 * Getter for {@link HdlcFrameDecoder#useChecksum}
	 * 
	 * @return {@link HdlcFrameDecoder#useChecksum}
	 */
	public boolean isUseChecksum() {
		return useChecksum;
	}

	/**
	 * Setter for {@link HdlcFrameDecoder#useChecksum}
	 * 
	 * @param boolean to set {@link HdlcFrameDecoder#useChecksum}
	 */
	public void setUseChecksum(boolean useChecksum) {
		this.useChecksum = useChecksum;
	}
}