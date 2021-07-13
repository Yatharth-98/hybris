/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.attributehandlers;

import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTest;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.training.model.CarModel;

/**
 *
 */
@IntegrationTest
public class KilowattAttributeHandlerIntegrationTest extends ServicelayerTest
{

	@Resource
	private ProductService productService;

	@Before
	public void setupTest() throws Exception
	{
		createCoreData();
		importCsv("/training/test/interceptors/defaultcatalog.impex", "UTF-8");
		importCsv("/training/test/attributehandlers/kilowattattributehandlerintegrationtest.impex", "UTF-8");
	}


	@Test
	public void testGet()
	{
		final CarModel car = (CarModel) productService.getProductForCode("001");

		assertEquals("Invalid kilowatt value.", Integer.valueOf(746), car.getKw());
	}

	@Test
	public void testGetHPNotAvailable()
	{
		final CarModel car = (CarModel) productService.getProductForCode("002");

		assertEquals("Invalid kilowatt value.", Integer.valueOf(0), car.getKw());
	}

}
