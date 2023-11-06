package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] arr;
    private Predicate<T> pred;

    public IterableWithPolicyImpl(final T[] arr) {
        this(arr, new Predicate<T>() {
            public boolean test(T arg0) {
                return true;    
            } 
        });
    }

    public IterableWithPolicyImpl(final T[] arr, final Predicate<T> pred) {
        this.pred = pred;
        this.arr = arr;
    }

    public Iterator<T> iterator() {
        return this.new IteratorImpl();
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.pred = filter;
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
            if(IterableWithPolicyImpl.this.pred == this.app[pointer++] && hasNext())
                return this.app[pointer];
            return null;
        }

    } 
}