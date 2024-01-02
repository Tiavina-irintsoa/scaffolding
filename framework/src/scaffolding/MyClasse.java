package scaffolding;

import com.google.gson.annotations.SerializedName;

public class MyClasse {
    @SerializedName("classname")
    String className;
    @SerializedName("package")
    String packageName;
    public MyClasse() {
    }
    public MyClasse(String className, String packageName) {
        setClassName(className);
        setPackageName(packageName);
    }
    public String toString(){
        return packageName+"."+className;
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
