/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

/**
 *
 */
public class ProductInitDefaultsInterceptor implements InitDefaultsInterceptor<ProductModel>
{

	private CatalogVersionService catalogVersionService;

	@Override
	public void onInitDefaults(final ProductModel model, final InterceptorContext ctx) throws InterceptorException
	{
		try
		{
		final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion("Default", "Staged");

		model.setCatalogVersion(catalogVersion);
	}
	catch (final UnknownIdentifierException e)
	{
		throw new InterceptorException("Unable to set the detafult value for catalog version.", e);
	}

	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

}
