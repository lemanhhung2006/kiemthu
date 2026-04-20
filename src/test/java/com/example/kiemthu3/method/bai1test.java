package com.example.kiemthu3.method;

import org.junit.jupiter.api.*;

public class bai1test {
    static bai1 bai1;
    @BeforeAll
    static void setUpBeforeAll(){
        System.out.println("set up before all");
        bai1 =  new bai1();
    }
//    @AfterAll
//    static void tearDownAfterAll(){
//    System.out.println("tear Down After All");
//    }
//    @BeforeEach
//    void setUp(){
//        System.out.println("set Up Before Each");
//    }
//    @AfterEach
//    void tearDown(){
//        System.out.println("tear Down After Each");
//    }
    @Test
    void test_add_positive() {
        Assertions.assertEquals(3, bai1.add(1, 2));
    }

    @Test
    void test_add_positive_negative() {
        Assertions.assertEquals(1, bai1.add(3, -2));
    }

    @Test
    @DisplayName("assertNull")
    void assertNull(){
        Assertions.assertNull(null);
    }

    @Test
    @DisplayName("assertNotNull")
            void assertNotNull(){
        Assertions.assertNotNull(1);
    }
    @Test
    @DisplayName("assertSame")
    void assertSame(){
        Assertions.assertSame("hi", "hi");
    }

    @Test
    @DisplayName("assertNotSame")
    void assertNotSame(){
        Assertions.assertNotSame("hi","hello");
    }

    @Test
    @DisplayName("assertTrue")
    void assertTrue(){
        Assertions.assertTrue(3>1);
    }
}
