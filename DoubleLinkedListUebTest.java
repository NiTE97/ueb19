package com.company;

import org.junit.Test;


import static org.junit.Assert.*;

public class DoubleLinkedListUebTest {

    @Test
    public void sizeTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        int actual = liste.size();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void isEmptyTestFalse() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        assertFalse(liste.isEmpty());
    }

    @Test
    public void isEmptyTestTrue() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>();
        assertTrue(liste.isEmpty());
    }

    @Test
    public void containsTestTrue() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        assertTrue(liste.contains("Node1"));
    }

    @Test
    public void containsTestFalse() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        assertFalse(liste.contains("Node3"));
    }

    @Test
    public void toArrayTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        Object[] actual = new Object[2];
        actual = liste.toArray(actual);
        assertEquals(actual[1], "Node2");
    }

    @Test
    public void toArrayTest2() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        Object[] actual = new Object[2];
        actual = liste.toArray(actual);
        assertEquals(actual[0], "Node1");
    }

    @Test
    public void toArrayTestTsZuKlein() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        Object[] actual = new Object[1];
        actual = liste.toArray(actual);
        assertEquals(actual[0], "Node1");
    }

    @Test
    public void removeTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        String rem = "Node2";
        liste.remove(rem);
        assertFalse(liste.contains("Node2"));
    }

    @Test
    public void removeTest2() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        String rem = "Node1";
        liste.remove(rem);
        assertFalse(liste.contains("Node1"));
    }

    @Test
    public void removeTest3() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        String rem = "Node1";
        assertTrue(liste.remove(rem));
    }

    @Test
    public void removeTest4() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        String rem = "Node2";
        liste.remove(rem);
        assertEquals("Node3", liste.get(1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeTestIndexOOB() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.remove(5);
        assertEquals("Node3", liste.get(1));
    }

    @Test
    public void getTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        String actual = liste.get(1);
        assertEquals("Node2", actual);
    }

    @Test
    public void getTest2() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        String actual = liste.get(0);
        assertEquals("Node1", actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getTestIndexOOB() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        String actual = liste.get(5);
        assertEquals("Node1", actual);
    }
    @Test
    public void clearTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.clear();
        String actual = liste.get(0);
        assertNull(actual);
    }

    @Test
    public void clearTest2() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>();
        liste.clear();
        String actual = liste.get(0);
        assertNull(actual);
    }

    @Test
    public void clearTest3() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        liste.clear();
        assertTrue(liste.isEmpty());
    }

    @Test
    public void setTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        liste.set(2, "NewNode");
        assertEquals("NewNode", liste.get(2));
    }

    @Test
    public void setTest2() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        liste.set(3, "NewNode");
        assertEquals("NewNode", liste.get(3));
    }

    @Test
    public void setTest3() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        String actual = liste.set(3, "NewNode");
        String expected = "Node4";
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setTestIndexOOB() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        String actual = liste.set(10, "NewNode");
        String expected = "Node4";
        assertEquals(expected, actual);
    }

    @Test
    public void addTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        liste.add(2, "NewNode");
        String actual = liste.get(2);
        String expected = "NewNode";
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addTest2() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        liste.add(5, "NewNode");
        String actual = liste.get(2);
        String expected = "NewNode";
        assertEquals(expected, actual);
    }

    @Test
    public void indexOfTest1() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        int actual = liste.indexOf("Node2");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void indexOfTest2() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        int actual = liste.indexOf("Node4");
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void indexOfTest3() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        int actual = liste.indexOf("Node1");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void indexOfTestNichtGefunden() {
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        int actual = liste.indexOf("Node5");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void iteratorTest1(){
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        DoubleLinkedListUeb<String>.CustomListIterator iter = (DoubleLinkedListUeb<String>.CustomListIterator) liste.listIterator();
        String actual = iter.next();
        String expected = "Node2";
        assertEquals(expected, actual);
    }

    @Test
    public void iteratorTest2(){
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        DoubleLinkedListUeb<String>.CustomListIterator iter = (DoubleLinkedListUeb<String>.CustomListIterator) liste.listIterator(2);
        String actual = iter.next();
        String expected = "Node4";
        assertEquals(expected, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void iteratorTestZuWeit(){
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        DoubleLinkedListUeb<String>.CustomListIterator iter = (DoubleLinkedListUeb<String>.CustomListIterator) liste.listIterator(3);
        String actual = iter.next();
        String expected = "Node2";
        assertEquals(expected, actual);
    }

    @Test
    public void iteratorTest3(){
        DoubleLinkedListUeb<String> liste = new DoubleLinkedListUeb<>("Node1");
        liste.add("Node2");
        liste.add("Node3");
        liste.add("Node4");
        DoubleLinkedListUeb<String>.CustomIterator iter = (DoubleLinkedListUeb<String>.CustomIterator) liste.iterator();
        String actual = iter.next();
        String expected = "Node2";
        assertEquals(expected, actual);
    }
}