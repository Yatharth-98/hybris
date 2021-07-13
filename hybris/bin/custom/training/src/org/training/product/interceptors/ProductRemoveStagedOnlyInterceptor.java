/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

/**
 *
 */
public class ProductRemoveStagedOnlyInterceptor implements RemoveInterceptor<ProductModel>
{

	@Override
	public void onRemove(final ProductModel model, final InterceptorContext ctx) throws InterceptorException
	{
		if ("Online".equals(model.getCatalogVersion().getVersion()))
		{
			throw new InterceptorException("Online version cannot be removed.");
		}

	}

}
