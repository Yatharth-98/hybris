/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.product.dao.TrainingProductDao;

/**
 *
 */
@IntegrationTest
public class TrainingProductDaoImplIntegrationTest extends ServicelayerTest
{

	@Resource
	private TrainingProductDao trainingProductDao;

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
	public void testFindByCode()
	{
		final List<ProductModel> products = trainingProductDao.findProductsByCode("p001");

		Assert.assertNotNull("No product found.", products);
		Assert.assertFalse("Emplty list.", products.isEmpty());
		Assert.assertTrue("More than one product for the same code.", products.size() == 1);
		Assert.assertEquals("Incorrect product retrieved.", "p001", products.iterator().next().getCode());
	}

	@Test
	public void testFindByManufacturer()
	{
		final List<ProductModel> products = trainingProductDao.findByManufacturer("Sony");

		Assert.assertNotNull("No product found.", products);
		Assert.assertFalse("Emplty list.", products.isEmpty());
		Assert.assertTrue("Incorrect number of products found.", products.size() == 2);
	}

}
