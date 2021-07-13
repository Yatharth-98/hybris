/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.training.events.ProductUpdateEvent;


/**
 *
 */
public class ProductValidateInterceptor implements ValidateInterceptor<ProductModel>
{

	private EventService eventService;

	@Override
	public void onValidate(final ProductModel model, final InterceptorContext ctx) throws InterceptorException
	{
		if (model.getApprovalStatus() != ArticleApprovalStatus.APPROVED)
		{
			throw new InterceptorException("Product must be approved before saving it.");
		}

		final ProductUpdateEvent productUpdateEvent = new ProductUpdateEvent();
		productUpdateEvent.setProduct(model);

		eventService.publishEvent(productUpdateEvent);
	}

	/**
	 * @param eventService
	 *           the eventService to set
	 */
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

}
