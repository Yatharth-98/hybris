/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.product.service.TrainingProductService;

/**
 *
 */
@IntegrationTest
public class TrainingProductServiceIntegrationTest extends ServicelayerTest
{

	@Resource
	private TrainingProductService trainingProductService;

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
	public void testFindByManufacturer()
	{
		final List<ProductModel> products = trainingProductService.getProductsByManufacturer("Sony");

		Assert.assertNotNull("No product found.", products);
		Assert.assertFalse("Emplty list.", products.isEmpty());
		Assert.assertTrue("Incorrect number of products found.", products.size() == 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindByManufacturer_ManufacturerNameNull()
	{
		trainingProductService.getProductsByManufacturer(null);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testFindByManufacturer_NoResults()
	{
		trainingProductService.getProductsByManufacturer("Nonexisting Manufacturer");
	}

}
