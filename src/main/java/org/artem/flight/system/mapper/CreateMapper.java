package org.artem.flight.system.mapper;

public interface CreateMapper<F, T> {

    T map(F object);
}