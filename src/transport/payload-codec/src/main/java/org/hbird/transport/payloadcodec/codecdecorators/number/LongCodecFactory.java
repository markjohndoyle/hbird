package org.hbird.transport.payloadcodec.codecdecorators.number;

import org.hbird.core.spacesystemmodel.encoding.Encoding;
import org.hbird.core.spacesystemmodel.tmtc.Parameter;
import org.hbird.transport.payloadcodec.codecparameters.CodecParameter;
import org.hbird.transport.payloadcodec.codecparameters.number.TwosComplementLongCodecParameter;
import org.hbird.transport.payloadcodec.exceptions.UnexpectedParameterTypeException;
import org.hbird.transport.payloadcodec.exceptions.UnknownParameterEncodingException;
import org.hbird.transport.payloadcodec.exceptions.UnsupportedParameterEncodingException;

public final class LongCodecFactory {

	private LongCodecFactory() {
		// Utility class
	}

	/**
	 * TODO update this doc
	 */
	public static CodecParameter<Long> decorateParameterWithCodec(final Parameter<Long> parameter, final Encoding enc)
			throws UnsupportedParameterEncodingException, UnknownParameterEncodingException, UnexpectedParameterTypeException {

		switch (enc.getBinaryRepresentation()) {
			case onesComplement:
				throw new UnsupportedParameterEncodingException("File a bug report :D");
			case twosComplement:
				if (enc.getSizeInBits() > Long.SIZE) {
					throw new UnexpectedParameterTypeException("Size of this parameter is > 32 which is too big to be a signed long. Size = "
							+ enc.getSizeInBits());
				}
				return new TwosComplementLongCodecParameter(parameter, enc);
			case unsigned:
				if (enc.getSizeInBits() > Long.SIZE) {
					throw new UnexpectedParameterTypeException("Size of this parameter is > 64 which is too big to be an unsigned integer. Size = "
							+ enc.getSizeInBits());
				}
				throw new UnsupportedParameterEncodingException("File a bug report :D");
			case signMagnitude:
				throw new UnsupportedParameterEncodingException("File a bug report :D");
			default:
				throw new UnknownParameterEncodingException("Unknown binary representation", enc.getBinaryRepresentation());
		}

	}
}
