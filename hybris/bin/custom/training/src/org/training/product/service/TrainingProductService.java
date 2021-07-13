/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.service;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;

import java.util.List;


/**
 *
 */
public interface TrainingProductService extends ProductService
{

	List<ProductModel> getProductsByManufacturer(String manufacturerName);

}
