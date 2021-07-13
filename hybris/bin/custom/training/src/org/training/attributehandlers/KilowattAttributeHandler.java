/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.training.model.CarModel;


/**
 *
 */
public class KilowattAttributeHandler implements DynamicAttributeHandler<Integer, CarModel>
{

	@Override
	public Integer get(final CarModel car)
	{
		return car.getHp() * 746 / 1000;
	}

	@Override
	public void set(final CarModel arg0, final Integer arg1)
	{
		throw new UnsupportedOperationException("Kilowatt field is read-only");

	}

}
