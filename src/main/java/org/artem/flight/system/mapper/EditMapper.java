package org.artem.flight.system.mapper;

public interface EditMapper<F, T> {

    T map(F fromObject, T toObject);

}