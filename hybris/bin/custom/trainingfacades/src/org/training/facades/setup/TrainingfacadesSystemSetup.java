/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.setup;

import static org.training.facades.constants.TrainingfacadesConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.facades.constants.TrainingfacadesConstants;
import org.training.facades.service.TrainingfacadesService;


@SystemSetup(extension = TrainingfacadesConstants.EXTENSIONNAME)
public class TrainingfacadesSystemSetup
{
	private final TrainingfacadesService trainingfacadesService;

	public TrainingfacadesSystemSetup(final TrainingfacadesService trainingfacadesService)
	{
		this.trainingfacadesService = trainingfacadesService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		trainingfacadesService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TrainingfacadesSystemSetup.class.getResourceAsStream("/trainingfacades/sap-hybris-platform.png");
	}
}
