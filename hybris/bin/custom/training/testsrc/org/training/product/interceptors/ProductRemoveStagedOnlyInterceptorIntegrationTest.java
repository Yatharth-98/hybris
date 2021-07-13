/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.interceptors;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.ModelRemovalException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


/**
 *
 */
@IntegrationTest
public class ProductRemoveStagedOnlyInterceptorIntegrationTest extends ServicelayerTest
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

	@Test(expected = ModelRemovalException.class)
	public void testOnRemove()
	{
		final ProductModel product = modelService.create(ProductModel.class);

		final CatalogVersionModel defaultStaged = catalogVersionService.getCatalogVersion("Default", "Online");

		final String productCode = "001";
		product.setCode(productCode);
		product.setCatalogVersion(defaultStaged);
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);

		modelService.save(product);

		final ProductModel productFromDatabase = productService.getProductForCode(defaultStaged, productCode);

		modelService.remove(productFromDatabase);
	}

	@Test(expected = UnknownIdentifierException.class)
	public void testOnRemoveStagedVersion()
	{
		final ProductModel product = modelService.create(ProductModel.class);

		final CatalogVersionModel defaultStaged = catalogVersionService.getCatalogVersion("Default", "Staged");

		final String productCode = "001";
		product.setCode(productCode);
		product.setCatalogVersion(defaultStaged);
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);

		modelService.save(product);

		final ProductModel productFromDatabase = productService.getProductForCode(defaultStaged, productCode);

		modelService.remove(productFromDatabase);

		productService.getProductForCode(defaultStaged, productCode);
	}

}
