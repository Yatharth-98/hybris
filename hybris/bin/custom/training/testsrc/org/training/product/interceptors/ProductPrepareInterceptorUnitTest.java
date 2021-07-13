/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 */
@UnitTest
public class ProductPrepareInterceptorUnitTest
{

	@InjectMocks
	private ProductPrepareInterceptor productPrepareInterceptor;


	@Mock
	private ProductModel product;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		productPrepareInterceptor = new ProductPrepareInterceptor();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOnPrepare() throws InterceptorException
	{
		Mockito.when(product.getCode()).thenReturn("001");

		productPrepareInterceptor.onPrepare(product, Mockito.mock(InterceptorContext.class));

		Mockito.verify(product, Mockito.atMost(1)).setName(product.getCode());
	}


	@Test
	public void testOnPrepareIfNameAlreadySet() throws InterceptorException
	{
		Mockito.when(product.getCode()).thenReturn("001");
		Mockito.when(product.getName()).thenReturn("My Product");

		productPrepareInterceptor.onPrepare(product, Mockito.mock(InterceptorContext.class));

		Mockito.verify(product, Mockito.never()).setName(product.getCode());
	}
}
