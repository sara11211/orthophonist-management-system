package com.models;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class SerializableAtomicLong extends AtomicLong implements Serializable {
    private static final long serialVersionUID = 1L;

    public SerializableAtomicLong(long initialValue) {
        super(initialValue);
    }
}