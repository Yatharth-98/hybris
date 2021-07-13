/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.service.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;

import org.training.product.dao.TrainingProductDao;
import org.training.product.service.TrainingProductService;

/**
 *
 */
public class TrainingProductServiceImpl extends DefaultProductService implements TrainingProductService
{

	private TrainingProductDao trainingProductDao;

	@Override
	public List<ProductModel> getProductsByManufacturer(final String manufacturerName)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("manufacturerName", manufacturerName);

		final List<ProductModel> products = trainingProductDao.findByManufacturer(manufacturerName);

		ServicesUtil.validateIfAnyResult(products, manufacturerName);

		return products;
	}

	/**
	 * @param trainingProductDao
	 *           the trainingProductDao to set
	 */
	public void setTrainingProductDao(final TrainingProductDao trainingProductDao)
	{
		this.trainingProductDao = trainingProductDao;
	}

}
