package com.coinbase.builder;

/**
 * Adds a validation method to the contract to allow the implementations to validate that
 * all of the required fields have been provided.
 * @param <T>
 */
public abstract class AbstractValidatingBuilder<T>  implements Builder<T> {

    @Override
    public final T build() {
        validate();
        return doBuild();
    }

    protected abstract void validate();

    protected abstract T doBuild();
}
