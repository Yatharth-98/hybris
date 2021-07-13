/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.product.populators;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.traning.facades.product.ProductData;

/**
 *
 */
@IntegrationTest
public class ProductConverterIntegrationTest extends ServicelayerTest
{

	@Resource(name = "trainingProductConverter")
	private Converter<ProductModel, ProductData> productConverter;

	@Resource
	private ModelService modelService;

	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/training/test/interceptors/defaultcatalog.impex", "UTF-8");
	}

	@Test
	public void testConvertWithoutProductData()
	{

		final ProductModel productModel = modelService.create(ProductModel.class);

		productModel.setCode("ProductConverterIntegrationTest-001");
		productModel.setName("Product Converter Integration Test 001");
		productModel.setManufacturerName("Publicis Groupe");

		final ProductData productData = productConverter.convert(productModel);

		Assert.assertNotNull("Product data shouldn't be null.", productData);
		Assert.assertEquals("Invalid product id", productModel.getCode(), productData.getId());
		Assert.assertEquals("Invalid product name", productModel.getName(), productData.getName());
		Assert.assertEquals("Invalid product manufacturer", productModel.getManufacturerName(), productData.getManufacturer());

	}

	@Test
	public void testConvertWithProductData()
	{

		final ProductModel productModel = modelService.create(ProductModel.class);

		productModel.setCode("ProductConverterIntegrationTest-002");
		productModel.setName("Product Converter Integration Test 002");
		productModel.setManufacturerName("Publicis Groupe");

		final ProductData productData = productConverter.convert(productModel, new ProductData());

		Assert.assertNotNull("Product data shouldn't be null.", productData);
		Assert.assertEquals("Invalid product id", productModel.getCode(), productData.getId());
		Assert.assertEquals("Invalid product name", productModel.getName(), productData.getName());
		Assert.assertEquals("Invalid product manufacturer", productModel.getManufacturerName(), productData.getManufacturer());

	}

}
