package scaffolding;

public class MyClasse {
    String className;
    String packageName;
    public MyClasse(String className, String packageName) {
        this.className = className;
        this.packageName = packageName;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
