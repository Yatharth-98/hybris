/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.dao;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;

import java.util.List;


/**
 *
 */
public interface TrainingProductDao extends ProductDao
{

	List<ProductModel> findByManufacturer(String manufacturerName);
}
