/**
 *
 */
package com.mystore.core.cron.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mystore.core.model.ProductRegisteredCronjobModel;
import com.mystore.core.model.ProductRegistrationModel;
import com.mystore.core.user.dao.MyStoreUserDao;


public class ProductRegistered extends AbstractJobPerformable<ProductRegisteredCronjobModel>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRegistered.class);

	private MyStoreUserDao myStoreUserDao;



	@Override
	public PerformResult perform(final ProductRegisteredCronjobModel cronjob)
	{
		try
		{
			final List<ProductRegistrationModel> totalproduct = myStoreUserDao.dailyRegisteredProduct();

			LOGGER.info("Total registeredproduct in system  {} are {}", new Date(), totalproduct);


			final String todayDate = new Date().toLocaleString();
			final String csvFileName = "/" + todayDate.replaceAll("[^A-Za-z0-9]", "") + ".csv";
			final FileWriter writer = new FileWriter(csvFileName);
			writer.write("User,Product Name,Serial Number,Coupon Code,date");
			writer.write("\n");
			for (final ProductRegistrationModel r : totalproduct)
			{
				final String couponCode = r.getCouponCode();
				final String date = r.getDate().toString();
				final String user = r.getUser().getContactEmail();
				final String serialNumber = r.getSerialNumber();
				final String productName = r.getProduct().getName();

				final String toBeSplit = user + "," + productName + "," + serialNumber + "," + couponCode + "," + date;
				final String[] split2 = toBeSplit.split(",");

				writer.write(Arrays.asList(split2).stream().collect(Collectors.joining(",")));
				writer.write("\n"); // newline
			}

			writer.close();
		}
		catch (final FlexibleSearchException e)
		{
			LOGGER.error("Error occured while executing flexible search", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
		}
		catch (final IOException e)
		{
			// XXX Auto-generated catch block
			e.printStackTrace();
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param myStoreUserDao
	 *           the myStoreUserDao to set
	 */
	public void setMyStoreUserDao(final MyStoreUserDao myStoreUserDao)
	{
		this.myStoreUserDao = myStoreUserDao;
	}
}




