/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.training.product.dao.TrainingProductDao;

/**
 *
 */
public class TrainingProductDaoImpl extends DefaultProductDao implements TrainingProductDao
{

	public TrainingProductDaoImpl(final String typecode)
	{
		super(typecode);
	}


	@Override
	public List<ProductModel> findByManufacturer(final String manufacturerName)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(
				"SELECT {PK} FROM {Product} WHERE {manufacturerName}=?manufacturer");
		query.addQueryParameter("manufacturer", manufacturerName);

		final SearchResult<ProductModel> searchResult = getFlexibleSearchService().search(query);

		return searchResult.getResult();
	}

}
