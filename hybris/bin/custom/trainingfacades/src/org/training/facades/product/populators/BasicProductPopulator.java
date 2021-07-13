/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.facades.product.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.traning.facades.product.ProductData;


/**
 *
 */
public class BasicProductPopulator implements Populator<ProductModel, ProductData>
{

	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{
		target.setId(source.getCode());
		target.setName(source.getName());
		target.setManufacturer(source.getManufacturerName());
	}

}
