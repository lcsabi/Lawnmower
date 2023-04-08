package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import util.Stack;

class StackTest {

    Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    @DisplayName("Testing other constructor that specifies a top element.")
    void testCtor() {
        stack = new Stack<>(new Stack.Node<Integer>(10));
        assertEquals(10, stack.pop());
    }

    @Test
    @DisplayName("Test getSize() on an empty stack. Should return 0.")
    void testGetSize0() {
        assertEquals(0, stack.getSize());
    }

    @Test
    @DisplayName("Test getSize() on a stack that has one element. Should return 1.")
    void testGetSize1() {
        stack.push(1);
        assertEquals(1, stack.getSize());
    }

    @Test
    @DisplayName("Test pushing 10 and then pop() on a stack. Should return 1 and 10.")
    void testPushAndPop() {
        stack.push(10);
        assertAll(
                () -> assertEquals(1, stack.getSize()),
                () -> assertEquals(10, stack.pop())
        );
    }

    @Test
    @DisplayName("Test pop() on an empty stack. Should return null.")
    void testPopEmpty() {
        assertNull(stack.pop());
    }

    @Test
    @DisplayName("Testing toString() on a stack. Should return an opening and closing bracket without a" +
            "whitespace between them.")
    void testToStringEmpty() {
        assertEquals("[ ]", stack.toString());
    }

    @Test
    @DisplayName("Test toString() on a stack. Should return [ 3 2 1 ].")
    void testToString() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals("[ 3 2 1 ]", stack.toString());
    }
}
