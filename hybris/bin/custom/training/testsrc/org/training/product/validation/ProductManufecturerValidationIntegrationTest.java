/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.product.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.validation.exceptions.HybrisConstraintViolation;
import de.hybris.platform.validation.model.constraints.ConstraintGroupModel;
import de.hybris.platform.validation.services.ValidationService;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
@IntegrationTest
public class ProductManufecturerValidationIntegrationTest extends ServicelayerTest
{

	@Resource
	private ModelService modelService;

	@Resource
	private ValidationService validationService;

	@Resource
	private CatalogVersionService catalogVersionService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	/**
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		importCsv("/training/test/interceptors/defaultcatalog.impex", "UTF-8");
		importCsv("/training/test/validation/constraints.impex", "UTF-8");
	}

	@Test
	public void testValidate()
	{
		final ProductModel product = modelService.create(ProductModel.class);

		final CatalogVersionModel defaultStaged = catalogVersionService.getCatalogVersion("Default", "Staged");

		final String productCode = "001";
		product.setCode(productCode);
		product.setCatalogVersion(defaultStaged);
		product.setApprovalStatus(ArticleApprovalStatus.APPROVED);

		final String query = "SELECT {PK} FROM {ConstraintGroup} WHERE {id}='testConstraintGroup'";

		final List<ConstraintGroupModel> constraintGroups = flexibleSearchService.<ConstraintGroupModel> search(query).getResult();

		validationService.setActiveConstraintGroups(constraintGroups);

		validationService.reloadValidationEngine();

		final Set<HybrisConstraintViolation> violations = validationService.validate(product, constraintGroups);

		assertNotNull("No violations.", violations);
		assertFalse("No violations.", violations.isEmpty());
		assertEquals("More than one violations.", 1, violations.size());
		assertEquals("Incorrect violations.", "productManufacturerNameNotNullContstraint",
				violations.iterator().next().getConstraintModel().getId());


	}

}
