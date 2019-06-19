package utils;

import play.i18n.Lang;
import play.templates.JavaExtensions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CookieClipExtensions extends JavaExtensions {

    public static String format(Number number, String pattern, String goupingChar) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Lang.getLocale());
        symbols.setGroupingSeparator(' ');
        return new DecimalFormat(pattern, symbols).format(number);
    }

}
