package br.soc.avaliacao.commons.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public abstract class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static Object[] toArray(Object... objects) {
        return objects;
    }

    public static LocalDate toDate(Date dataNascimento) {
        if (dataNascimento == null) 
            return null;
        
        Instant instant = dataNascimento.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }



}
