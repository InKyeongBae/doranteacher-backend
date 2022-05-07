package org.triathlongirls.doranssam.domain.user;

public enum WritingStep {
    Writing,
    Expression;

    public int getValue() {
        return ordinal() + 1;
    }
}
