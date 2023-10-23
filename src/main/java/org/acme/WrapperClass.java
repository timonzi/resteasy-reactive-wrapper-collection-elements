package org.acme;

public class WrapperClass<E extends Enum<E>> {

    private final E value;

    public WrapperClass(final E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }
}
