package com.dragon.toolbox;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class CollectionTest {

    @Test
    public void test(){
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            set.add("set"+i);
        }
        ArrayList<String> list = new ArrayList<>(set);
        System.out.println("list:"+list);
    }

}
