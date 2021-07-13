/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

/**
 *
 */
public class ProductPrepareInterceptor implements PrepareInterceptor<ProductModel>
{

	@Override
	public void onPrepare(final ProductModel model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model.getName() == null)
		{
			model.setName(model.getCode());
		}

	}

}
