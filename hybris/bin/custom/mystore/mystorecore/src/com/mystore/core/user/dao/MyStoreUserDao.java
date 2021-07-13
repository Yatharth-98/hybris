/**
 *
 */
package com.mystore.core.user.dao;

import java.util.List;

import com.mystore.core.model.ProductRegistrationModel;


public interface MyStoreUserDao
{

	int countTotalUsers(boolean includeEmployees);

	List<ProductRegistrationModel> dailyRegisteredProduct();


}
