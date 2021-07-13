/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
@IntegrationTest
public class ProductInitDefaultsInterceptorIntegrationTest extends ServicelayerTest
{

	@Resource
	private ModelService modelService;


	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/training/test/interceptors/defaultcatalog.impex", "UTF-8");
	}

	@Test
	public void testOnInitDefaults()
	{
		final ProductModel product = modelService.create(ProductModel.class);


		assertNotNull("Catalog version cannot be empty.", product.getCatalogVersion());
	}

}
