/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
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
public class ProductValidateInterceptorUnitTest
{

	@InjectMocks
	private ProductValidateInterceptor productValidateInterceptor;

	@Mock
	private ProductModel product;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		productValidateInterceptor = new ProductValidateInterceptor();

		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = InterceptorException.class)
	public void testOnValidateForCheckStatus() throws InterceptorException
	{
		Mockito.when(product.getApprovalStatus()).thenReturn(ArticleApprovalStatus.CHECK);

		productValidateInterceptor.onValidate(product, Mockito.mock(InterceptorContext.class));

	}

	@Test(expected = InterceptorException.class)
	public void testOnValidateForUnapprovedStatus() throws InterceptorException
	{
		Mockito.when(product.getApprovalStatus()).thenReturn(ArticleApprovalStatus.UNAPPROVED);

		productValidateInterceptor.onValidate(product, Mockito.mock(InterceptorContext.class));

	}

	@Test(expected = InterceptorException.class)
	public void testOnValidateForNullApprovalStatus() throws InterceptorException
	{
		productValidateInterceptor.onValidate(product, Mockito.mock(InterceptorContext.class));

	}

}
