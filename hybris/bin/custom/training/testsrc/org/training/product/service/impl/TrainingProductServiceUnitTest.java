/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.product.dao.TrainingProductDao;
import org.training.product.service.TrainingProductService;

/**
 *
 */
@UnitTest
public class TrainingProductServiceUnitTest
{

	@InjectMocks
	private TrainingProductService trainingProductService;

	@Mock
	private TrainingProductDao trainingProductDao;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		trainingProductService = new TrainingProductServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetProductsByManufacturer_ManufacturerNameNull()
	{
		trainingProductService.getProductsByManufacturer(null);

	}

	@Test
	public void testGetProductsByManufacturer()
	{
		Mockito.when(trainingProductDao.findByManufacturer("Sony"))
				.thenReturn(Arrays.asList(Mockito.mock(ProductModel.class), Mockito.mock(ProductModel.class)));

		final List<ProductModel> products = trainingProductService.getProductsByManufacturer("Sony");

		Assert.assertTrue("Incorrect products count.", products.size() == 2);

	}

	@Test(expected = UnknownIdentifierException.class)
	public void testGetProductsByManufacturer_NoResults()
	{
		Mockito.when(trainingProductDao.findByManufacturer("Sony"))
				.thenReturn(Collections.emptyList());

		trainingProductService.getProductsByManufacturer("Sony");

	}

}
