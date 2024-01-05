package scaffolding;

import com.google.gson.annotations.SerializedName;

import util.StringUtil;

public class TemplateSettings {
    
    @SerializedName("package")
    TemplateTag packageTags;
    @SerializedName("import")
    TemplateTag importTags;
    @SerializedName("classDeclaration")
    TemplateTag declarationTag;
    @SerializedName("fields")
    TemplateTag fieldsTags;
    @SerializedName("methods")
    TemplateTag methodsTags;
    @SerializedName("endClass")
    TemplateTag endTags;
    @SerializedName("classAnnotation")
    TemplateTag classAnnotations;
    @SerializedName("object")
    TemplateTag object;
    @SerializedName("imports")
    TemplateTag imports;
    @SerializedName("entete")
    TemplateTag entete;

    public TemplateSettings(){

    }
    
    public String getPackageRegex(){
        return StringUtil.regex(packageTags.getStart(), packageTags.getEnd());
    }
    public String getImportRegex(){
        return StringUtil.regex(importTags.getStart(), importTags.getEnd());
    }
    public String getDeclarationRegex(){
        return StringUtil.regex(declarationTag.getStart(), declarationTag.getEnd());
    }
    public String getFieldsRegex(){
        return StringUtil.regex(fieldsTags.getStart(), fieldsTags.getEnd());
    }
    public String getMethodsRegex(){
        return StringUtil.regex(methodsTags.getStart(), methodsTags.getEnd());
    }
    public String getEndClassRegex(){
        return StringUtil.regex(endTags.getStart(), endTags.getEnd());
    }
    public String getClassAnnotationRegex(){
        return StringUtil.regex(classAnnotations.getStart(), classAnnotations.getEnd());
    }
    public String getObjectRegex(){
        return StringUtil.regex(object.getStart(), object.getEnd());
    }
    public String getImportsRegex(){
        return StringUtil.regex(imports.getStart(), imports.getEnd());
    }
    public String getEnteteRegex(){
        return StringUtil.regex(entete.getStart(), entete.getEnd());
    }

    public TemplateTag getPackageTags() {
        return packageTags;
    }
    public void setPackageTags(TemplateTag packageTags) {
        this.packageTags = packageTags;
    }
    public TemplateTag getImportTags() {
        return importTags;
    }

    public void setImportTags(TemplateTag importTags) {
        this.importTags = importTags;
    }
    public TemplateTag getFieldsTags() {
        return fieldsTags;
    }
    public void setFieldsTags(TemplateTag fieldsTags) {
        this.fieldsTags = fieldsTags;
    }
    public TemplateTag getMethodsTags() {
        return methodsTags;
    }
    public void setMethodsTags(TemplateTag methodsTags) {
        this.methodsTags = methodsTags;
    }
    public TemplateTag getDeclarationTag() {
        return declarationTag;
    }
    public void setDeclarationTag(TemplateTag declarationTag) {
        this.declarationTag = declarationTag;
    }
    public TemplateTag getEndTags() {
        return endTags;
    }
    public void setEndTags(TemplateTag endTags) {
        this.endTags = endTags;
    }
    public TemplateTag getClassAnnotations() {
        return classAnnotations;
    }
    public void setClassAnnotations(TemplateTag classAnnotations) {
        this.classAnnotations = classAnnotations;
    }
    public TemplateTag getObject() {
        return object;
    }
    public void setObject(TemplateTag object) {
        this.object = object;
    }
    public TemplateTag getImports() {
        return imports;
    }
    public void setImports(TemplateTag imports) {
        this.imports = imports;
    }
    public TemplateTag getEntete() {
        return entete;
    }
    public void setEntete(TemplateTag entete) {
        this.entete = entete;
    }
}
