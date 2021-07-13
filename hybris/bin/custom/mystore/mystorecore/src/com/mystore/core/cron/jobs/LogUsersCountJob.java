/**
 *
 */
package com.mystore.core.cron.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mystore.core.model.LogUsersCountCronJobModel;
import com.mystore.core.user.dao.MyStoreUserDao;


public class LogUsersCountJob extends AbstractJobPerformable<LogUsersCountCronJobModel>
{

	private static final Logger LOGGER = LoggerFactory.getLogger(LogUsersCountJob.class);

	private MyStoreUserDao myStoreUserDao;

	@Override
	public PerformResult perform(final LogUsersCountCronJobModel cronjob)
	{
		try
		{
			final int totalUsers = myStoreUserDao.countTotalUsers(cronjob.getIncludeEmployees());

			LOGGER.info("Total users in the system at {} are {}", new Date(), totalUsers);
		}
		catch (final FlexibleSearchException e)
		{
			LOGGER.error("Error occured while executing flexible search", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
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
