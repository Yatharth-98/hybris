/**
 *
 */
package com.mystore.core.user.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.List;

import com.mystore.core.model.ProductRegistrationModel;
import com.mystore.core.user.dao.MyStoreUserDao;


public class MyStoreUserDaoImpl implements MyStoreUserDao
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public int countTotalUsers(final boolean includeEmployees)
	{
		final StringBuilder builder = new StringBuilder("SELECT count(0) FROM {");

		if (includeEmployees)
		{
			builder.append(UserModel._TYPECODE);
		}
		else
		{
			builder.append(CustomerModel._TYPECODE);
		}

		builder.append("}");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(builder.toString());

		query.setResultClassList(Arrays.asList(Integer.class));

		final SearchResult<Integer> searchResult = flexibleSearchService.<Integer> search(query);

		final List<Integer> countsList = searchResult.getResult();

		return countsList.get(0);
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public List<ProductRegistrationModel> dailyRegisteredProduct()
	{
		// XXX Auto-generated method stub
		final StringBuilder builder = new StringBuilder("SELECT {pk} FROM {ProductRegistration}");



		final FlexibleSearchQuery query = new FlexibleSearchQuery(builder);

		query.setResultClassList(Arrays.asList(ProductRegistrationModel.class));

		final SearchResult<ProductRegistrationModel> searchResult = flexibleSearchService.<ProductRegistrationModel> search(query);

		final List<ProductRegistrationModel> List = searchResult.getResult();
		return List;


	}


	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */



}
