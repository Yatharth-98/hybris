/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.eventlisteners;

import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.events.ProductUpdateEvent;


/**
 *
 */
public class ProductUpdateEventListener extends AbstractEventListener<ProductUpdateEvent>
{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductUpdateEventListener.class);

	@Override
	protected void onEvent(final ProductUpdateEvent event)
	{
		LOGGER.info("Product with code ' {} ' has been modified.", event.getProduct().getCode());

	}

}
