package scaffolding;

public class DynamicClassGenerator {
    public static MyClasse getClass(String sqlType,String language) throws Exception{
        try {
            if(language.compareTo("java")==0){
                return mapSqlTypeToJava(sqlType);
            }
            else if(language.compareTo("csharp")==0){
                return MapSqlTypeToCSharp(sqlType);
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Aucune classe trouvee pour "+sqlType);
        }
    }
    public static MyClasse mapSqlTypeToJava(String sqlType) {
        String classename = "";
        String packagename = "";
        System.out.println(sqlType.toLowerCase());
        switch (sqlType.toLowerCase()) {
            case "varchar":
            case "text":
            case "json":
            case "char":
            case "uuid":
                classename = "String";
                packagename = "java.lang";
                break;
            case "int":
                classename = "Integer";
                packagename = "java.lang";
                break;
            case "bigint":
            case "serial":
                classename = "Long";
                packagename = "java.lang";
                break;
            case "numeric":
            case "decimal":
            case "double":
                classename = "Double";
                packagename = "java.lang";
                break;
            case "real":
                classename = "Float";
                packagename = "java.lang";
                break;
            case "date":
                classename = "Date";
                packagename = "java.sql";
                break;
            case "time":
                classename = "Time";
                packagename = "java.sql";
                break;
            case "timestamp":
                classename = "Timestamp";
                packagename= "java.sql";
                break;
            case "boolean":
                classename = "Boolean";
                packagename = "java.lang";
                break;
            default:
                classename = "Object";
                packagename = "java.lang";   
                break;         
        }
        return new MyClasse(classename,packagename);
    } 
    public static MyClasse MapSqlTypeToCSharp(String sqlType)
    {
        String className = "";
        String namespaceName = "";

        switch (sqlType.toLowerCase())
        {
            case "varchar":
            case "text":
            case "json":
            case "char":
            case "uuid":
                className = "string";
                namespaceName = "System";
                break;
            case "int":
                className = "int";
                namespaceName = "System";
                break;
            case "bigint":
            case "serial":
                className = "long";
                namespaceName = "System";
                break;
            case "numeric":
            case "decimal":
            case "double":
                className = "double";
                namespaceName = "System";
                break;
            case "real":
                className = "float";
                namespaceName = "System";
                break;
            case "date":
                className = "DateTime";
                namespaceName = "System";
                break;
            case "time":
                className = "TimeSpan";
                namespaceName = "System";
                break;
            case "timestamp":
                className = "DateTime";
                namespaceName = "System";
                break;
            case "boolean":
                className = "bool";
                namespaceName = "System";
                break;
            default:
                className = "object";
                namespaceName = "System";
                break;
        }
    return new MyClasse(className, namespaceName);
}

}
