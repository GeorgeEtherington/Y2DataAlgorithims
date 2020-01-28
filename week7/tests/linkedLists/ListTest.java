package linkedLists;
import arrayGenerator.generator.*;
import arrayGenerator.scope.IntegerScope;
import linkedList.list.List;
import linkedList.list.ListAccessError;
import linkedList.list.SingleLinkList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListTest{
IntegerArrayGenerator arrayGenerator = new IntegerArrayGenerator(new IntegerScope(0,20));
Integer[] arrayInteger = arrayGenerator.getArray(20);
SingleLinkList linkedList = new SingleLinkList();
SingleLinkList emptyList = new SingleLinkList();
public SingleLinkList populate (Integer[] array) throws ListAccessError {
   for (int i = 0; i < arrayInteger.length; i++){
      linkedList.add(i, array[i]);
   }
   return linkedList;
}
  @Test
   void testAdd() throws ListAccessError {
   populate(arrayInteger);
      for (int i = 0; i < arrayInteger.length; i++){
       assertEquals(arrayInteger[i], linkedList.get(i));
      }

  }

  @Test
  void testRemove() throws ListAccessError{
  populate(arrayInteger);
  linkedList.remove(5);
   assertEquals(arrayInteger[6],linkedList.get(5));
  }

  @Test
    void testError() throws ListAccessError{
    assertThrows(ListAccessError.class, () ->
            linkedList.add(-1, 12));

    assertThrows(ListAccessError.class, () ->
              emptyList.get(1));

      assertThrows(ListAccessError.class, () ->
              linkedList.get(22));

    assertThrows(ListAccessError.class, () ->
              emptyList.remove(1));
    assertThrows(NullPointerException.class, () ->
            linkedList.add(22, 2));
  }
}
