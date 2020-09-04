package com.helloxin;


@FunctionalInterface
public interface RecordTemplate {
    <T> T execute(RecordContext ctx) throws Exception;
}
