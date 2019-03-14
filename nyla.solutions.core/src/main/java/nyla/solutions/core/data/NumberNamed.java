package nyla.solutions.core.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nyla.solutions.core.util.Text;


public class NumberNamed extends Named 
implements Comparable<Object>, Mappable<Object,Object>, Serializable, Nameable, Numbered
{

   /**
    * 
    * Constructor for NumberNamed initializes internal
    * 
    * data settings.
    *  
    */
   public NumberNamed()
   {
   }//--------------------------------------------

   public static class NumberComparator

   implements Comparator<Object>, Serializable
   {

      public int compare(Object first, Object second)
      {

         if (first == null)
         {

            return 1;

         }
         else
         {

            NumberNamed vo1 = (NumberNamed) first;

            NumberNamed vo2 = (NumberNamed) second;

            return Integer.compare(vo1.number, vo2.number);
         }

      }

      public NumberComparator()
      {

      }
      
      static final long serialVersionUID = NumberComparator.class.getName()
      .hashCode();

   }// --------------------------------------------


   public NumberNamed(String aName, int aNumber)

   {

      super(aName);

      number = -1;

      setNumber(aNumber);

   }

   public final int getNumber()
   {

      return number;

   }//--------------------------------------------
   public final void setNumber(int aNumber)
   {

      if (number == aNumber)
      {

         return;

      }
      else

      {

         number = aNumber;

         //setDirty();

         return;

      }
   }//--------------------------------------------

   public static Collection<NumberNamed> sortByNumber(Collection<NumberNamed> aNamedVOs)

   {

      List<NumberNamed> list;

      if (aNamedVOs instanceof List)
         list = (List<NumberNamed>) aNamedVOs;
      else
         list = new ArrayList<NumberNamed>(aNamedVOs);

      Collections.sort(list, new NumberComparator());

      return list;

   }//--------------------------------------------

   /**
    * 
    * 
    * @see java.lang.Comparable#compareTo(java.lang.Object)
    */
   public int compareTo(Object object)

   {

      if (object == null || !(object instanceof NumberNamed))
      {

         return -1;

      }
      else if (super.equals(object))
      {
         return 0;
      }
      else
      {

         NumberNamed vo = (NumberNamed) object;

         return Integer.compare(getNumber(), vo.getNumber());
         
      }

   }//--------------------------------------------

   /**
    * 
    * @see nyla.solutions.core.data.Mappable#getKey()
    */
   public Object getKey()

   {

      return Integer.valueOf(number);

   }//--------------------------------------------
   /**
    * 
    * this.setPrimaryKey(Integer.valueOf(aKey.toString()));
    * @param aKey the key to set
    */
   public void setKey(Object aKey)
   {
      if (aKey == null)
         throw new IllegalArgumentException("aKey required in NumberedProperty.setKey");
      
      
      if (!Text.isInteger(aKey.toString()))
         throw new IllegalArgumentException("Integer aKey required in NumberedProperty.setKey key="+aKey);
      
      this.setNumber(Integer.parseInt(aKey.toString()));
   }//--------------------------------------------
   
   /**
    * 
    * @return super.getText()
    * 
    * @see nyla.solutions.core.data.Mappable#getKey()
    *  
    */

   public Object getValue()

   {

      return super.getText();

   }//--------------------------------------------
   
   /**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + number;
		return result;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumberNamed other = (NumberNamed) obj;
		if (number != other.number)
			return false;
		return true;
	}

private int number;
   static final long serialVersionUID = NumberNamed.class.getName().hashCode();
}
