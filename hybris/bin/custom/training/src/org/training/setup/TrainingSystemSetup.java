/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.TrainingConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.ClasspathImpExResource;

import java.io.InputStream;

import org.training.constants.TrainingConstants;
import org.training.service.TrainingService;


@SystemSetup(extension = TrainingConstants.EXTENSIONNAME)
public class TrainingSystemSetup
{
	private final TrainingService trainingService;

	private final ImportService importService;



	public TrainingSystemSetup(final TrainingService trainingService, final ImportService importService)
	{
		this.trainingService = trainingService;
		this.importService = importService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		trainingService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return TrainingSystemSetup.class.getResourceAsStream("/training/sap-hybris-platform.png");
	}

	@SystemSetup(process = SystemSetup.Process.ALL, type = SystemSetup.Type.PROJECT)
	public void createProjectData()
	{
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/catalog.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/classificationsystem.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/classificationclasses.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/classificationattributevalues.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/classificationattributes.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/classattributeassignments.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/categories.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/categoriesmedia.impex");
		importImpEx("/training/import/coredata/productCatalogs/trainingCatalog/categoriesrelation.impex");

		importImpEx("/training/import/sampledata/productCatalogs/trainingCatalog/product.impex");
		importImpEx("/training/import/sampledata/productCatalogs/trainingCatalog/productcategories.impex");
		importImpEx("/training/import/sampledata/productCatalogs/trainingCatalog/productclassification.impex");
		importImpEx("/training/import/sampledata/productCatalogs/trainingCatalog/productmedia.impex");
	}

	/**
	 *
	 */
	private void importImpEx(final String path)
	{
		importService.importData(
				new ClasspathImpExResource(path, "UTF-8"));
	}

}
