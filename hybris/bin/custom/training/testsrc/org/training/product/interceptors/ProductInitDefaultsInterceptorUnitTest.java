/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 */
@UnitTest
public class ProductInitDefaultsInterceptorUnitTest
{

	@InjectMocks
	private ProductInitDefaultsInterceptor productInitDefaultsInterceptor;

	@Mock
	private CatalogVersionService catalogVersionService;

	@Mock
	private CatalogVersionModel defaultCatalogStaged;

	@Before
	public void setUp()
	{

		productInitDefaultsInterceptor = new ProductInitDefaultsInterceptor();

		MockitoAnnotations.initMocks(this);


	}

	@Test
	public void testOnInitDefaults() throws InterceptorException
	{
		when(catalogVersionService.getCatalogVersion("Default", "Staged")).thenReturn(defaultCatalogStaged);

		final ProductModel product = new ProductModel();

		productInitDefaultsInterceptor.onInitDefaults(product, mock(InterceptorContext.class));

		assertEquals("Invalid catalogVersion", defaultCatalogStaged, product.getCatalogVersion());
	}

	@Test(expected = InterceptorException.class)
	public void testOnInitDefaultsCatalogVersionNotFound() throws InterceptorException
	{
		when(catalogVersionService.getCatalogVersion("Default", "Staged")).thenThrow(
				new UnknownIdentifierException("CatalogVersion with catalogId ' Default ' and version 'Staged' not found!"));

		final ProductModel product = new ProductModel();

		productInitDefaultsInterceptor.onInitDefaults(product, mock(InterceptorContext.class));

	}

}
