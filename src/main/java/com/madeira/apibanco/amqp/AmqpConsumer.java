package com.madeira.apibanco.amqp;

public interface AmqpConsumer<T> {
    void consumer(T t);
}
