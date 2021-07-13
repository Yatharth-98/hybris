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
public class ProductLoadInterceptorUnitTest
{

	@InjectMocks
	private ProductLoadInterceptor productLoadInterceptor;

	@Mock
	private ProductModel product;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		productLoadInterceptor = new ProductLoadInterceptor();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOnLoadEmptyDescription() throws InterceptorException
	{
		Mockito.when(product.getDescription()).thenReturn(null);
		Mockito.when(product.getName()).thenReturn("My Product");

		productLoadInterceptor.onLoad(product, Mockito.mock(InterceptorContext.class));

		Mockito.verify(product, Mockito.times(1)).setDescription(product.getName());
	}

	@Test
	public void testOnValidateNonEmptyDescription() throws InterceptorException
	{
		Mockito.when(product.getDescription()).thenReturn("My Description");
		Mockito.when(product.getName()).thenReturn("My Product");

		productLoadInterceptor.onLoad(product, Mockito.mock(InterceptorContext.class));

		Mockito.verify(product, Mockito.never()).setDescription(product.getName());
	}


}
