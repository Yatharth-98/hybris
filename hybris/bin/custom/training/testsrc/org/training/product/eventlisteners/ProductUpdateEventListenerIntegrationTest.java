/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.eventlisteners;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
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
public class ProductUpdateEventListenerIntegrationTest extends ServicelayerTest
{

	@Resource
	private ModelService modelService;

	@Resource
	private CatalogVersionService catalogVersionService;


	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/training/test/interceptors/defaultcatalog.impex", "UTF-8");
	}

	@Test
	public void testOnEvent()
	{
		final ProductModel product = modelService.create(ProductModel.class);

		final CatalogVersionModel defaultStaged = catalogVersionService.getCatalogVersion("Default", "Staged");

		final String productCode = "testOnEvent-001";
		product.setCode(productCode);
		product.setCatalogVersion(defaultStaged);
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);

		modelService.save(product);

		// Nothing to verify - check the logs. There should the log statement.
	}

}
