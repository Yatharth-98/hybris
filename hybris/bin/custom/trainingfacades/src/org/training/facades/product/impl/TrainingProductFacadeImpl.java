/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.product.impl;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.training.facades.product.TrainingProductFacade;
import org.training.product.service.TrainingProductService;
import org.traning.facades.product.ProductData;


/**
 *
 */
public class TrainingProductFacadeImpl implements TrainingProductFacade
{

	private TrainingProductService trainingProductService;

	private Converter<ProductModel, ProductData> productConverter;

	@Override
	public ProductData getProductForCode(final String code)
	{
		final ProductModel productModel = trainingProductService.getProductForCode(code);

		return productConverter.convert(productModel);
	}

	@Override
	public List<ProductData> getProductsByManufacturer(final String manufacturer)
	{
		final List<ProductModel> products = trainingProductService.getProductsByManufacturer(manufacturer);

		return Converters.convertAll(products, productConverter);
	}


	/**
	 * @param trainingProductService
	 *           the trainingProductService to set
	 */
	public void setTrainingProductService(final TrainingProductService trainingProductService)
	{
		this.trainingProductService = trainingProductService;
	}

	/**
	 * @param productConverter
	 *           the productConverter to set
	 */
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}
}
