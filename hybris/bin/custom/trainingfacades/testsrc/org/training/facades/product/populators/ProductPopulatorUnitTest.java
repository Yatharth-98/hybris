/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.product.populators;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.traning.facades.product.ProductData;

/**
 *
 */
@UnitTest
public class ProductPopulatorUnitTest
{

	@InjectMocks
	private Populator<ProductModel, ProductData> basicProductPopulator;

	@Mock
	private ProductModel productModel;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		basicProductPopulator = new BasicProductPopulator();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPopulate()
	{
		Mockito.when(productModel.getCode()).thenReturn("ProductPopulatorUnitTest-001");
		Mockito.when(productModel.getName()).thenReturn("Product Populator Unit Test 001");
		Mockito.when(productModel.getManufacturerName()).thenReturn("Publicis Groupe");

		final ProductData productData = new ProductData();

		basicProductPopulator.populate(productModel, productData);

		Assert.assertEquals("Invalid product id", productModel.getCode(), productData.getId());
		Assert.assertEquals("Invalid product name", productModel.getName(), productData.getName());
		Assert.assertEquals("Invalid product manufacturer", productModel.getManufacturerName(), productData.getManufacturer());
	}

}
