package enumeration;

public enum Cargo {
    ESTAGIARIO("Estagiário"),
    ASSISTENTE("Assistente"),
    COORDENADOR("Coordenador"),
    ANALISTA("Analista"),
    GERENTE("Gerente");

    private final String label;

    Cargo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
