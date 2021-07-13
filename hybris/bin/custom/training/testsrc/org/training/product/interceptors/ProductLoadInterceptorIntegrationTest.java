/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import static org.junit.Assert.assertEquals;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


/**
 *
 */
@IntegrationTest
public class ProductLoadInterceptorIntegrationTest extends ServicelayerTest
{


	@Resource
	private ModelService modelService;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private ProductService productService;


	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/training/test/interceptors/defaultcatalog.impex", "UTF-8");
	}

	@Test
	public void testOnLoadNoDescription() throws InterceptorException
	{
		final ProductModel product = modelService.create(ProductModel.class);

		final CatalogVersionModel defaultStaged = catalogVersionService.getCatalogVersion("Default", "Staged");

		final String productCode = "001";
		final String name = "My Product";
		product.setCode(productCode);
		product.setName(name);
		product.setCatalogVersion(defaultStaged);
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);

		modelService.save(product);

		final ProductModel productFromDatabase = productService.getProductForCode(defaultStaged, productCode);

		assertEquals("Incorrect description", productFromDatabase.getName(), productFromDatabase.getDescription());
	}


	@Test
	public void testOnLoadWithDescription() throws InterceptorException
	{
		final ProductModel product = modelService.create(ProductModel.class);

		final CatalogVersionModel defaultStaged = catalogVersionService.getCatalogVersion("Default", "Staged");

		final String productCode = "002";
		final String name = "My Product";
		product.setCode(productCode);
		product.setDescription("My Description");
		product.setName(name);
		product.setCatalogVersion(defaultStaged);
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);

		modelService.save(product);

		final ProductModel productFromDatabase = productService.getProductForCode(defaultStaged, productCode);

		assertEquals("Incorrect description", "My Description", productFromDatabase.getDescription());
	}
}
