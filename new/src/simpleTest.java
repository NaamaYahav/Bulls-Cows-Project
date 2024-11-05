import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
public class simpleTest {
    @Test
    public void checkIfEqual(){
         String a= "HELLO" ;
         String b= "HIPPO";
         boolean flag= Main.isEqual(a,b);
         assertFalse(flag);
    }
}

