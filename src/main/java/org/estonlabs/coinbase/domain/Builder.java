package org.estonlabs.coinbase.domain;

public interface Builder<I,O> {
    Builder<I,O> init(I i);

    O build();
}
