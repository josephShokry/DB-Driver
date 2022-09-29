import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class operationsTest {
    @Test
    void contstactorTest(){
        operations ops = new operations("joseph");
        assertEquals(ops.getUserName(),"joseph","worng ans");
    }
    @Test
    void contstactorTest2(){
        operations ops = new operations(null);
        assertEquals(ops.getUserName(),"standard","worng ans");
    }
    operations ops = new operations("joseph");
    @org.junit.jupiter.api.Test
    void addTest1() {
        int res=ops.add(5,6);
        assertEquals(11,res,"wrong ans");
    }
    @Test
    void addTest2() {
        int res=ops.add(4,6);
        assertEquals(10,res,"wrong ans");
    }

    @org.junit.jupiter.api.Test
    void subTest2() {
        int res=ops.sub(6,5);
        assertEquals(1,res,"wrong ans");
    }
}