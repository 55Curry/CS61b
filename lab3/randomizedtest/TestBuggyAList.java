package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
    BuggyAList<Integer> n = new BuggyAList<>();
    n.addLast(10);
    n.addLast(20);
    n.addLast(30);
    n.removeLast();
    n.removeLast();
    assertEquals(1, n.size());
    assertEquals(10, (int) n.getLast());
    assertEquals(10, (int) n.get(0));
  }

  @Test
  public void randomTest() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    int N = 500;
    for (int i = 0; i < N; i++) {
      int random = StdRandom.uniform(0, 3);
      if (random == 0) {
        list.add(random);
        System.out.println("addLast:" + random);
      } else if (random == 1) {
        int size = list.size();
        System.out.println("size:" + size);
      }else if(random == 2){
        if(list.size() > 0){
          list.getLast();
          System.out.println("getLast:" + list.getLast());
          list.removeLast();
        }
      }
    }
  }
  @Test
  public void randomTest2(){
    ArrayList<Integer> list = new ArrayList<>();
    BuggyAList<Integer> n = new BuggyAList<>();
    int N = 5000;
    for (int i = 0; i < N; i++) {
      int random = StdRandom.uniform(0, 3);
      if (random == 0){
        list.add(random);
        System.out.println("addLast(list):" + random);
        n.addLast(random);
        System.out.println("addLast(n):" + n.getLast());
      } else if (random == 1) {
        int size1 = list.size();
        System.out.println("size(list):" + size1);
        int size2 =  n.size();
        System.out.println("size(n):" + size2);
      }else if(random == 2){
        if(list.size() > 0){
          list.getLast();
          System.out.println("getLast(list):" + list.getLast());
          list.removeLast();
          n.getLast();
          System.out.println("getLast(n):" + n.getLast());
          n.removeLast();
        }
      }
    }
  }
}