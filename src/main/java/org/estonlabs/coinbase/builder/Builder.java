package org.estonlabs.coinbase.builder;

/**
 * Standard builder interface, used for creating various request objects.
 * @param <O>
 */
public interface Builder<O> {
    O build();
}
