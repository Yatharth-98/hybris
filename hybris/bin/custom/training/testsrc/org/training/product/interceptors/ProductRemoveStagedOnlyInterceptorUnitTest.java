/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
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
public class ProductRemoveStagedOnlyInterceptorUnitTest
{
	@InjectMocks
	private ProductRemoveStagedOnlyInterceptor productRemoveStagedOnlyInterceptor;

	@Mock
	private ProductModel product;

	@Mock
	private CatalogVersionModel onlineCatalogVersion;

	@Before
	public void setUp()
	{

		productRemoveStagedOnlyInterceptor = new ProductRemoveStagedOnlyInterceptor();

		MockitoAnnotations.initMocks(this);
		Mockito.when(product.getCatalogVersion()).thenReturn(onlineCatalogVersion);
	}

	@Test(expected = InterceptorException.class)
	public void testOnRemove() throws InterceptorException
	{

		Mockito.when(onlineCatalogVersion.getVersion()).thenReturn("Online");

		productRemoveStagedOnlyInterceptor.onRemove(product, Mockito.mock(InterceptorContext.class));

	}


}
