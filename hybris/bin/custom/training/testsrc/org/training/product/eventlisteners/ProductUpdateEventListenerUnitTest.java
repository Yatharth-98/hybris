/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.eventlisteners;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.training.events.ProductUpdateEvent;

/**
 *
 */
@UnitTest
public class ProductUpdateEventListenerUnitTest
{

	@InjectMocks
	private ProductUpdateEventListener productUpdateEventListener;

	@Mock
	private ProductModel product;

	@Mock
	private ProductUpdateEvent productUpdateEvent;


	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		productUpdateEventListener = new ProductUpdateEventListener();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOnEvent()
	{
		Mockito.when(productUpdateEvent.getProduct()).thenReturn(product);
		Mockito.when(product.getCode()).thenReturn("001");

		productUpdateEventListener.onEvent(productUpdateEvent);

		Mockito.verify(product, Mockito.times(1)).getCode();

	}

}
