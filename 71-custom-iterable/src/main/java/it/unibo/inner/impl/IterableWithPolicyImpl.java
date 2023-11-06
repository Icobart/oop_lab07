package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] arr;

    public IterableWithPolicyImpl(final T[] arr) {
        this.arr = arr;
    }

    private class IteratorImpl implements Iterator<T>{
        private int pointer = 0;
        private final T[] app;

        public IteratorImpl() {
            this.app = IterableWithPolicyImpl.this.arr;
        }


        public boolean hasNext() {
            return (this.app != null && pointer < app.length);
        }   

        public T next() {
            return this.app[pointer++];
        }

    } 

    public Iterator<T> iterator() {
        return this.new IteratorImpl();
    }

    public void setIterationPolicy(Predicate<T> filter) {
        
    }

}