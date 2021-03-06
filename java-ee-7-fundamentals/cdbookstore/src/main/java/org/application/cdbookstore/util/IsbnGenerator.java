package org.application.cdbookstore.util;

import javax.inject.Inject;
import java.util.logging.Logger;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private Logger logger;

    @Inject
    @ThirteenDigits
    private String prefix;

    @Inject
    @ThirteenDigits
    private int postfix;

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        return prefix + Math.random() + postfix;
    }
}