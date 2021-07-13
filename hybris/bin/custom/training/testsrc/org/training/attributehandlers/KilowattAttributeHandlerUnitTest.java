/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.attributehandlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.junit.Test;
import org.mockito.Mockito;
import org.training.model.CarModel;

/**
 *
 */
@UnitTest
public class KilowattAttributeHandlerUnitTest
{

	private final DynamicAttributeHandler<Integer, CarModel> kilowattAttributeHandler = new KilowattAttributeHandler();



	@Test
	public void testGet()
	{
		final CarModel car = Mockito.mock(CarModel.class);
		when(car.getHp()).thenReturn(Integer.valueOf(1000));

		assertEquals("Invalid kilowatt value.", Integer.valueOf(746), kilowattAttributeHandler.get(car));
	}

	@Test
	public void testGetHPNotAvailable()
	{
		final CarModel car = Mockito.mock(CarModel.class);

		assertEquals("Invalid kilowatt value.", Integer.valueOf(0), kilowattAttributeHandler.get(car));
	}

}
