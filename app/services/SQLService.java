package services;

import play.Logger;
import play.db.DB;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SQLService {

    private static final String LOG_PREFIX = "SQLService |";

    public static <T> List<T> select(Class<T> clazz, String requete) throws Exception {
        Logger.debug("%s select [%s / %s]", LOG_PREFIX, clazz, requete);

        Connection conn = DB.getConnection();
        ResultSet rs = conn.createStatement().executeQuery(requete);

        List<T> result = new ArrayList<T>();
        while (rs.next()) {
            T item = clazz.newInstance();

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String sqlFieldName = rsmd.getColumnLabel(i);

                try {
                    Field field = clazz.getDeclaredField(sqlFieldName);

                    switch (field.getType().getCanonicalName()) {
                        case "java.lang.Integer":
                            field.set(item, rs.getInt(sqlFieldName));
                            break;
                        case "java.lang.Long":
                            field.set(item, rs.getLong(sqlFieldName));
                            break;
                        case "java.lang.Double":
                            field.set(item, rs.getDouble(sqlFieldName));
                            break;
                        case "java.lang.Float":
                            field.set(item, rs.getFloat(sqlFieldName));
                            break;
                        case "java.lang.String":
                            field.set(item, rs.getString(sqlFieldName));
                            break;
                        case "java.util.Date":
                            field.set(item, rs.getDate(sqlFieldName));
                            break;
                        default:
                            if (field.getType().isEnum()) {
                                Class fieldEnum = Class.forName(field.getType().getCanonicalName());
                                field.set(item, Enum.valueOf(fieldEnum, rs.getString(sqlFieldName)));
                            }
                            break;
                    }
                } catch (NoSuchFieldException nsfe) {
                    System.out.println("field didn't exists");
                }
            }
            result.add(item);
        }
        return result;
    }
}
