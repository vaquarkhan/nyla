package nyla.solutions.global.patterns.search.queryService.finders;

import java.util.Collection;
import java.util.Map;

import nyla.solutions.global.data.DataRow;
import nyla.solutions.global.patterns.iteration.Paging;
import nyla.solutions.global.patterns.iteration.PagingCollection;
import nyla.solutions.global.patterns.search.ReLookup;
import nyla.solutions.global.patterns.search.queryService.QuestCriteria;
import nyla.solutions.global.patterns.search.queryService.QuestFinder;
import nyla.solutions.global.patterns.search.queryService.QuestKey;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Organizer;
import nyla.solutions.global.util.Text;

/**
 * The quest finder reference implement using an ReLookup
 * @author Gregory Green
 *
 */
public class QuestReLookupFinder implements QuestFinder
{	
	/**
	 * 
	 * @param reLookup the reLookup
	 */
	public QuestReLookupFinder(ReLookup<DataRow> reLookup)
	{
		this.reLookup = reLookup;
	}// --------------------------------------------------------
	/**
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Collection<DataRow> call() throws Exception
	{
		return this.getResults();
	}// --------------------------------------------------------
	/**
	 * 
	 * @see nyla.solutions.global.patterns.search.queryService.QuestFinder#setCriteria(nyla.solutions.global.patterns.search.queryService.QuestCriteria)
	 */
	@Override
	public void assignCriteria(QuestCriteria questCriteria, String source)
	{
		this.questCriteria = questCriteria;
		
	}// --------------------------------------------------------
	/**
	 * 
	 * @see nyla.solutions.global.patterns.search.queryService.QuestFinder#getResults()
	 */
	@Override
	public Paging<DataRow> getResults()
	{
		if(questCriteria == null)
			return null;
		
		String queryRegExpTemplate = Config.getProperty(QuestReLookupFinder.class,questCriteria.getQuestName());
		String queryRegExp = queryRegExpTemplate;
		
		QuestKey[] questKeys = this.questCriteria.getQuestKeys();
		
		if(questKeys != null)
		{
			//Plugin in quest keys
			Map<String,String> map = Organizer.toMap(questKeys);
			
			//Get formatted regular expression
			queryRegExp = Text.format(queryRegExpTemplate, map);
		}
			
		
		return new PagingCollection<DataRow>(reLookup.lookupCollection(queryRegExp),
				this.questCriteria.getPageCriteria());
	}// --------------------------------------------------------

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}// -------------------------------------------------------- 
	
	
	/**
	 * @return the source
	 */
	public String getSource()
	{
		return source;
	}// --------------------------------------------------------

	private String source;
	private QuestCriteria questCriteria =null;
	private final ReLookup<DataRow> reLookup;

}