/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.product;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.facades.product.impl.TrainingProductFacadeImpl;
import org.training.product.service.TrainingProductService;
import org.traning.facades.product.ProductData;

/**
 *
 */
@UnitTest
public class TrainingProductFacadeUnitTest
{

	@InjectMocks
	private TrainingProductFacade trainingProductFacade;

	@Mock
	private TrainingProductService trainingProductService;

	@Mock
	private Converter<ProductModel, ProductData> productConverter;

	@Mock
	private ProductModel product1;

	@Mock
	private ProductModel product2;

	@Mock
	private ProductData productData1;

	@Mock
	private ProductData productData2;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{

		trainingProductFacade = new TrainingProductFacadeImpl();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetProductByCode()
	{

		Mockito.when(trainingProductService.getProductForCode("p001")).thenReturn(product1);
		Mockito.when(productConverter.convert(product1)).thenReturn(productData1);

		final ProductData productDataFromFacade = trainingProductFacade.getProductForCode("p001");

		Assert.assertNotNull("No product found", productDataFromFacade);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetProductByCode_NullCode()
	{
		Mockito.when(trainingProductService.getProductForCode(null)).thenThrow(new IllegalArgumentException("code cannot be null"));
		trainingProductFacade.getProductForCode(null);

	}

	@Test(expected = UnknownIdentifierException.class)
	public void testGetProductByCode_NoResult()
	{

		Mockito.when(trainingProductService.getProductForCode("p001"))
				.thenThrow(new UnknownIdentifierException("No product found"));

		trainingProductFacade.getProductForCode("p001");

	}

	@Test
	public void testGetProductByManufacturer()
	{

		Mockito.when(trainingProductService.getProductsByManufacturer("Sony"))
				.thenReturn(Arrays.asList(product1, product2));
		Mockito.when(productConverter.convert(product1)).thenReturn(productData1);
		Mockito.when(productConverter.convert(product2)).thenReturn(productData2);

		final List<ProductData> productDataListromFacade = trainingProductFacade.getProductsByManufacturer("Sony");

		Assert.assertNotNull("No product found", productDataListromFacade);
		Assert.assertFalse("Empty list", productDataListromFacade.isEmpty());
		Assert.assertTrue("Incorrect products count", productDataListromFacade.size() == 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetProductByManufacturer_NullManufacturerName()
	{
		Mockito.when(trainingProductService.getProductsByManufacturer(null))
				.thenThrow(new IllegalArgumentException("manufacturerName cannot be null"));
		trainingProductFacade.getProductsByManufacturer(null);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testGetProductByManufacturer_NoResults()
	{

		Mockito.when(trainingProductService.getProductsByManufacturer("Sony"))
				.thenThrow(new UnknownIdentifierException("No product found"));

		trainingProductFacade.getProductsByManufacturer("Sony");
	}

}
