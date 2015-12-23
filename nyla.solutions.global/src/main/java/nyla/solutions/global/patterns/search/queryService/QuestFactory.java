package nyla.solutions.global.patterns.search.queryService;

import java.util.Comparator;

import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.patterns.expression.BooleanExpression;
import nyla.solutions.global.patterns.search.queryService.QuestMgr;
import nyla.solutions.global.patterns.search.queryService.QuestService;
import nyla.solutions.global.patterns.servicefactory.ConfigServiceFactory;
import nyla.solutions.global.patterns.workthread.ExecutorBoss;
import nyla.solutions.global.util.Config;

/**
 * Quest factory useding 
 * @author Gregory Green
 *
 */
public class QuestFactory
{
	private QuestFactory()
	{}
	
	/**
	 * 
	 * @return singleton executor boss (thread pool)
	 */
	public static ExecutorBoss createExecutorBoss()
	{
		synchronized(QuestFactory.class)
		{
			if(executorBoss == null)
				executorBoss = new ExecutorBoss(threadCount);
		}
		return executorBoss;
	}// --------------------------------------------------------
	/**
	 *
	 * @return singleton instance of the factory
	 */
	public static QuestFactory getInstance()
	{
		return questFactory;
	}// --------------------------------------------------------
	
	/**
	 * 
	 * @param dataSource data source
	 * @return quest finder instance
	 */
	public QuestFinder createFinder(QuestCriteria criteria, String dataSource)
	{
		try
		{
			QuestFinder finder =  ConfigServiceFactory.getConfigServiceFactoryInstance().create(dataSource);
			finder.assignCriteria(criteria,dataSource);
			
			return finder;
		}
		catch(ConfigException e)
		{
			throw new ConfigException("Connect create new instance "+QuestFinder.class.getName()+" "+e.getMessage(),e);
		}
	}// --------------------------------------------------------
	/**
	 * 
	 * @param name the instance to compare
	 * @return the comparator by name
	 */
	public <T> Comparator<T> createComparator(String name)
	{
		Comparator<T> comparator = ConfigServiceFactory.getConfigServiceFactoryInstance().create(name);
		
		return comparator;
	}// --------------------------------------------------------
	/**
	 * 
	 * @param name the boolean expression name (can  be the full classpath name)
	 * @return the created instance
	 */
	public <T> BooleanExpression<T> createBooleanExpression(String name)
	{
		BooleanExpression<T> expression = ConfigServiceFactory.getConfigServiceFactoryInstance().create(name);
		return expression;
	}// --------------------------------------------------------
	/**
	 * 
	 * @return new QuestMgr();
	 */
	public QuestService createQuestService()
	{
		return new QuestMgr();
	}
	private static final QuestFactory questFactory = new QuestFactory();
	private static ExecutorBoss executorBoss = null;
	private static int threadCount = Config.getPropertyInteger(QuestFactory.class,"threadCount");
}
