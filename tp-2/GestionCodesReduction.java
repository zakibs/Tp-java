public class GestionCodesReduction {
    private CodeReduction[] codes;
    private int taille;
    
    public GestionCodesReduction() {
        codes = new CodeReduction[10];
        taille = 0;
    }
    
    public void ajouterCode(CodeReduction code) {
        if (taille >= codes.length) {
            agrandir();
        }
        codes[taille] = code;
        taille++;
    }
    
    private void agrandir() {
        CodeReduction[] nouveauxCodes = new CodeReduction[codes.length + 5];
        System.arraycopy(codes, 0, nouveauxCodes, 0, taille);
        codes = nouveauxCodes;
    }
    
    public CodeReduction trouverCode(String code) {
        for (int i = 0; i < taille; i++) {
            if (codes[i].getCode().equals(code)) {
                return codes[i];
            }
        }
        return null;
    }
    
    public int appliquerCode(String code, int totalBrut) throws KendiFoodException {
        CodeReduction codeReduction = trouverCode(code);
        if (codeReduction == null) {
            throw new KendiFoodException("Code promo inconnu: \"" + code + "\"");
        }
        
        int reduction = (totalBrut * codeReduction.getPourcentage()) / 100;
        return totalBrut - reduction;
    }
}