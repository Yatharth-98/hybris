/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.service;

public interface TrainingfacadesService
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);
}
