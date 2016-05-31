/*
 * Ce code est la propriete de l'empereur Alexandre Fortin
 * Each line should be prefixed with  * 
 */
package Domaine;
import java.io.*;
public class ObjectCloner
{
   // so that nobody can accidentally create an ObjectCloner object
   private ObjectCloner(){}
   // returns a deep copy of an object
   static public Object deepCopy(Object oldObj) throws Exception
   {
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;
      try
      {
         ByteArrayOutputStream bos = new ByteArrayOutputStream(); // sets up a ByteArrayOutputStream which is used to create the ObjectOutputStream on line B
         oos = new ObjectOutputStream(bos); // B
         // serialize and pass the object
         oos.writeObject(oldObj);   // C
         oos.flush();               // D
         ByteArrayInputStream bin = 
               new ByteArrayInputStream(bos.toByteArray()); // E
         ois = new ObjectInputStream(bin);                  // F
         // return the new object
         return ois.readObject(); // G
      }
      catch(Exception e)
      {
         System.out.println("Exception in ObjectCloner = " + e);
         throw(e);
      }
      finally
      {
          if(oos != null){
              oos.close();
          }
          if(ois != null){
              ois.close();
          }
        
      }
   }
   
}