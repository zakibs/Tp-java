public class CodeReduction {
    private String code;
    private int pourcentage;
    
    public CodeReduction(String code, int pourcentage) throws KendiFoodException {
        setCode(code);
        setPourcentage(pourcentage);
    }
    
    public String getCode() { return code; }
    public int getPourcentage() { return pourcentage; }
    
    public void setCode(String code) throws KendiFoodException {
        if (code == null || code.trim().isEmpty()) {
            throw new KendiFoodException("Le code ne peut pas être vide");
        }
        this.code = code.trim();
    }
    
    public void setPourcentage(int pourcentage) throws KendiFoodException {
        if (pourcentage < 1 || pourcentage > 50) {
            throw new KendiFoodException("Le pourcentage doit être entre 1 et 50");
        }
        this.pourcentage = pourcentage;
    }
}