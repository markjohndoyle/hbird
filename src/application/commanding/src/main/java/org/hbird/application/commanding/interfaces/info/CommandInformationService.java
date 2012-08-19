package org.hbird.application.commanding.interfaces.info;

import java.util.List;

import org.hbird.core.commons.tmtc.ParameterGroup;

public interface CommandInformationService {

	/**
	 * Returns a list of {@link ParameterGroup}s representing all commands in the space system model
	 * @return {@link ParameterGroup} list of all commands
	 */
	List<ParameterGroup> getAllCommands();
}