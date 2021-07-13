/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.LoadInterceptor;

/**
 *
 */
public class ProductLoadInterceptor implements LoadInterceptor<ProductModel>
{

	@Override
	public void onLoad(final ProductModel model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model.getDescription() == null)
		{
			model.setDescription(model.getName());
		}

	}

}
