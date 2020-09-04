package com.helloxin;

@FunctionalInterface
public interface RecordCallback {
    Object execute() throws Exception;
}
