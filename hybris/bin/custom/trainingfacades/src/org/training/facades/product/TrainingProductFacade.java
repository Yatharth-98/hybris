/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.product;

import java.util.List;

import org.traning.facades.product.ProductData;


/**
 *
 */
public interface TrainingProductFacade
{

	/**
	 * @return
	 *
	 */
	ProductData getProductForCode(String string);

	/**
	 *
	 */
	List<ProductData> getProductsByManufacturer(String string);

}
