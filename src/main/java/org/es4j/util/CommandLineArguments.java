package org.es4j.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


@SuppressWarnings("serial")
public class CommandLineArguments extends LinkedList<String> implements List<String> {
    
    private static CommandLineArguments instance = null;
    
    public static CommandLineArguments instance() {
        if(instance == null) {
            throw new RuntimeException("The argument array is not set");
        }
        return instance;
    }
    
    public static void setArguments(String[] arguments) {
        if(instance != null) {
            throw new RuntimeException("The instance is already instantiated!");
        }
        instance = new CommandLineArguments(arguments);
    }

    public CommandLineArguments(String[] args) {
        this.addAll(Arrays.asList(args));
    }
    
    public String getFirstValue(String key, String default_) {

        for(String arg : this) {
            if(arg.startsWith(key)) {
                return arg.replaceFirst(key, "").trim();
            }
        }
        return default_;
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return toArray(a);
    }

    @Override
    public boolean add(String e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public final boolean addAll(Collection<? extends String> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String get(int index) {
        return super.get(index);
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return super.subList(fromIndex, toIndex);
    }

}
