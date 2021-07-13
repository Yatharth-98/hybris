/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.product;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.traning.facades.product.ProductData;

/**
 *
 */
@IntegrationTest
public class TrainingProductFacadeIntegrationTest extends ServicelayerTest
{

	@Resource
	private TrainingProductFacade trainingProductFacade;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/training/test/interceptors/defaultcatalog.impex", "UTF-8");
		importCsv("/training/test/dao/products.impex", "UTF-8");
	}

	@Test
	public void testGetProductByCode()
	{

		final ProductData productDataFromFacade = trainingProductFacade.getProductForCode("p001");

		Assert.assertNotNull("No product found", productDataFromFacade);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetProductByCode_NullCode()
	{

		trainingProductFacade.getProductForCode(null);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testGetProductByCode_NonExistingId()
	{

		trainingProductFacade.getProductForCode("NonExistingId");
	}

	@Test
	public void testGetProductsByManufacturer()
	{

		final List<ProductData> productDataListFromFacade = trainingProductFacade.getProductsByManufacturer("Sony");

		Assert.assertNotNull("No product found", productDataListFromFacade);
		Assert.assertFalse("Empty list", productDataListFromFacade.isEmpty());
		Assert.assertTrue("Incorrect count", productDataListFromFacade.size() == 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetProductByCode_NullManufacturerName()
	{

		trainingProductFacade.getProductsByManufacturer(null);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testGetProductByCode_NonExistingManufacturer()
	{

		trainingProductFacade.getProductsByManufacturer("NonExistingManufacturer");
	}

}
