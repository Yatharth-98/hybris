/**
 *
 */
package com.mystore.core.cron.jobs;

/**
 * @author 91963
 *
 */
public class ProductRegistration
{
	private String product_name;
	private int serialNo;

	public String getProduct_name()
	{
		return product_name;
	}

	public void setProduct_name(final String product_name)
	{
		this.product_name = product_name;
	}

	public int getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(final int serialNo)
	{
		this.serialNo = serialNo;
	}


}
