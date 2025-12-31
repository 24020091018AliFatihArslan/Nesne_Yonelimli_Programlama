package com.ali.hospital.repo;

import java.util.concurrent.atomic.AtomicLong;

public class IdGen {
    private final AtomicLong seq = new AtomicLong(1);
    public long next(){ return seq.getAndIncrement(); }
}
