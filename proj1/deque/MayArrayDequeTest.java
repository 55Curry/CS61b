package deque;
import deque.MayArrayDeque;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class MayArrayDequeTest {

    @Test
    void testBasicIntegerOperations() {
        Comparator<Integer> intComparator = Integer::compare;
        MayArrayDeque<Integer> deque = new MayArrayDeque<>(intComparator);

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());

        deque.addFirst(10);
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertEquals(10, deque.get(0));

        deque.addLast(20);
        assertEquals(2, deque.size());
        assertEquals(10, deque.get(0));
        assertEquals(20, deque.get(1));

        deque.addFirst(5);
        assertEquals(3, deque.size());
        assertEquals(5, deque.get(0));
        assertEquals(10, deque.get(1));
        assertEquals(20, deque.get(2));

        assertEquals(5, deque.removeFirst());
        assertEquals(2, deque.size());
        assertEquals(10, deque.get(0));
        assertEquals(20, deque.get(1));

        assertEquals(20, deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals(10, deque.get(0));

        assertEquals(10, deque.removeFirst());
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
    }

    @Test
    void testPrintIntegerDeque() {
        Comparator<Integer> intComparator = Integer::compare;
        MayArrayDeque<Integer> deque = new MayArrayDeque<>(intComparator);
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        // 可以捕获 printDeque 的输出并进行断言，这里为了简洁直接调用查看
        System.out.println("Printing Integer Deque:");
        deque.printDeque(); // 预期输出：2, 1, 3 (每行一个)
    }
    @Test
    void testMaxInteger() {
        Comparator<Integer> intComparator = Integer::compare;
        MayArrayDeque<Integer> deque = new MayArrayDeque<>(intComparator);
        assertNull(deque.max()); // 空 Deque

        deque.addLast(5);
        assertEquals(5, deque.max());

        deque.addFirst(10);
        assertEquals(10, deque.max());

        deque.addLast(2);
        assertEquals(10, deque.max());

        deque.addFirst(15);
        assertEquals(15, deque.max());

        deque.addLast(15); // 包含重复最大值
        assertEquals(15, deque.max());
    }

    @Test
    void testMaxIntegerWithCustomComparator() {
        // 自定义比较器，按数值的相反数比较，所以最小值反而会认为是最大值
        Comparator<Integer> reverseIntComparator = (a, b) -> b.compareTo(a);
        MayArrayDeque<Integer> deque = new MayArrayDeque<>(reverseIntComparator);
        deque.addLast(5);
        assertEquals(5, deque.max()); // 使用构造函数传入的比较器

        deque.addFirst(10);
        assertEquals(5, deque.max());

        deque.max(Integer::compare); // 显式传入比较器，应该返回实际最大值
        assertEquals(10, deque.max(Integer::compare));
    }
    @Test
    void testMaxStringByLength() {
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        MayArrayDeque<String> deque = new MayArrayDeque<>(lengthComparator);
        assertNull(deque.max());

        deque.addLast("apple");
        assertEquals("apple", deque.max());

        deque.addFirst("banana");
        assertEquals("banana", deque.max());

        deque.addLast("kiwi");
        assertEquals("banana", deque.max());

        deque.addFirst("longerstring");
        assertEquals("longerstring", deque.max());
    }

    @Test
    void testMaxStringAlphabetically() {
        Comparator<String> alphabeticalComparator = String::compareTo;
        MayArrayDeque<String> deque = new MayArrayDeque<>(alphabeticalComparator);
        deque.addLast("zebra");
        assertEquals("zebra", deque.max());

        deque.addFirst("apple");
        assertEquals("zebra", deque.max());

        deque.max(String::compareTo); // 显式传入比较器
        assertEquals("zebra", deque.max(String::compareTo));
    }
    @Test
    void testEmptyDequeOperations() {
        Comparator<Integer> intComparator = Integer::compare;
        MayArrayDeque<Integer> deque = new MayArrayDeque<>(intComparator);

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
        assertNull(deque.get(0));
        assertNull(deque.max());
    }
}